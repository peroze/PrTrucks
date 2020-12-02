package Pr.Cars;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class is the controller for TrucksList.fxml
 *
 * @author peroze
 * @version 1.0 Alpha
 */
public class CompanyList implements Initializable {


    RotateTransition rotate;

    @FXML
    private Button Delete_Button;

    @FXML
    private TableView<ModelCompany> Truck_Table;

    @FXML
    private TableColumn<ModelCompany, String> Id_Column;

    @FXML
    private TableColumn<ModelCompany, String> Company_Col;

    @FXML
    private TableColumn<ModelCompany, String> Phone_Col;

    @FXML
    private ContextMenu Cont;

    @FXML
    private ImageView Import_Button;

    @FXML
    private TextField Search_Bar;

    @FXML
    private Label Search_icon;



    private boolean edit;
    private ObservableList<ModelCompany> Oblist;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Sql db = new Sql();
        ResultSet rs = db.Query_General_Company();
        Oblist = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Oblist.add(new ModelCompany( rs.getString("id"),rs.getString("Name"), rs.getString("Phone"),rs.getString("Prices")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Id_Column.setCellValueFactory(new PropertyValueFactory<>("id"));
        Phone_Col.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        Company_Col.setCellValueFactory(new PropertyValueFactory<>("Name"));

        FilteredList<ModelCompany> Filter = new FilteredList<>(Oblist, b -> true);

        Search_Bar.textProperty().addListener((observable, oldValue, newValue) -> {
            Filter.setPredicate(ModelCompany -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String LowerCase = newValue.toLowerCase();
                if (ModelCompany.getId().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelCompany.getName().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelCompany.getPhone().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else return false;
            });
        });
        SortedList<ModelCompany> sorted = new SortedList<>(Filter);
        sorted.comparatorProperty().bind(Truck_Table.comparatorProperty());
        Truck_Table.setItems(sorted);
        Truck_Table.setRowFactory((tv -> {
            TableRow<ModelCompany> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    DoubleClickTable();
                }
            });
            return row;
        }));
        MenuItem Del = new MenuItem();
        Del.setText("Διαγραφή");
        Del.setOnAction(this::Delete_Button_Pressed);

        MenuItem Edits = new MenuItem();
        Edits.setText("Επεξεργασία");
        Edits.setOnAction(this::Ed);
        Cont.getItems().get(0).setOnAction(this::View);
        Cont.getItems().add(Edits);
        Cont.getItems().add(Del);
        db.Disconnect();
    }

    @FXML
    void View(ActionEvent event) {
        DoubleClickTable();
    }

    /**
     * This method is used to open the all the details of the selected Service when the user double clicks on it
     */
    void DoubleClickTable() {
        ModelCompany temp = Truck_Table.getSelectionModel().getSelectedItem();
        Stage primaryStage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ViewCompany.fxml"));
        try {
            Parent root = fxmlloader.load();
            fxmlloader.<ViewCompany>getController().setCompany(temp);
            primaryStage.setTitle("PrTrucks");
            primaryStage.setScene(new Scene(root, 596, 646));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void Ed(ActionEvent event) {
        edit = true;
        Import_Button_Pressed(null);
    }




    /**
     * This method inserts a new Truck in the list when the insert button is pressed
     *
     * @param event The event
     */
    @FXML
    void Import_Button_Pressed(MouseEvent event) {
        Stage primaryStage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("AddCompany.fxml"));
        try {
            Parent root = fxmlloader.load();
            int max;
            if(Oblist.isEmpty()){
                max=0;
            }
            else {
                max = Integer.valueOf(Oblist.get(Oblist.size() - 1).getId());
            }
            if (edit == true) {
                edit = false;
                fxmlloader.<AddCompany>getController().edit(Truck_Table.getSelectionModel().getSelectedItem());
            }
            AddCompany addcar = fxmlloader.getController();
            primaryStage.setOnHidden(e -> {
                RenewTable();
            });
            primaryStage.setOnCloseRequest(e -> {
                RenewTable();
            });
            primaryStage.setTitle("PrTrucks");
            primaryStage.setScene(new Scene(root, 600, 596));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method Deletes a car when the Delete button is pressed
     *
     * @param event The event
     */
    @FXML
    void Delete_Button_Pressed(ActionEvent event) {
        ModelCompany temp = Truck_Table.getSelectionModel().getSelectedItem();
        if (!(temp == null)) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Επιβαιβέωση");
            alert.setHeaderText("Διαγραφή στοιχείου");
            alert.setContentText("Είσαι σύγουρος ότι θέλεις να διαγράψεις την Εταιρία με όνομα " + temp.getName() + ".");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Sql sql = new Sql();
                int k = sql.DeleteCompanies(Integer.valueOf(temp.getId()));
                if (k == 0) {
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Αποτυχία");
                    alert2.setContentText("Η διαγραφή απέτυχε, προσπαθύστε ξανά");
                    alert2.showAndWait();
                }
                sql.Disconnect();
                RenewTable();
            }

        }

    }

    /**
     * This method performs a spin animation of the import button when the users hover over it
     *
     * @param event The event
     */
    @FXML
    void Import_Button_Hover(MouseEvent event) {

        rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360);
        rotate.setCycleCount(1);
        rotate.setDuration(Duration.millis(1000));
        rotate.setAutoReverse(true);
        rotate.setNode(Import_Button);
        rotate.play();
        rotate.statusProperty().addListener(new ChangeListener<Animation.Status>() {
            @Override
            public void changed(ObservableValue<? extends Animation.Status> observable, Animation.Status oldValue, Animation.Status newValue) {

                if (newValue == Animation.Status.STOPPED) {


                    if (Import_Button.getRotate() != 0 || Import_Button.getRotate() != 360) {
                        RotateTransition transition = new RotateTransition(Duration.seconds(1), Import_Button);
                        transition.setFromAngle(Import_Button.getRotate());
                        transition.setToAngle(0);
                        transition.setCycleCount(1);
                        transition.setAutoReverse(true);
                        transition.play();
                    }
                }

            }

        });

    }

    /**
     * This method renews the visible table (its the same as initialize())
     */
    public void RenewTable() {
        Sql db = new Sql();
        ResultSet rs = db.Query_General_Company();
        Oblist = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Oblist.add(new ModelCompany( rs.getString("id"),rs.getString("Name"), rs.getString("Phone"),rs.getString("Prices")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        FilteredList<ModelCompany> Filter = new FilteredList<>(Oblist, b -> true);

        Search_Bar.textProperty().addListener((observable, oldValue, newValue) -> {
            Filter.setPredicate(ModelCompany -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String LowerCase = newValue.toLowerCase();
                if (ModelCompany.getId().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelCompany.getName().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelCompany.getPhone().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else return false;
            });
        });
        SortedList<ModelCompany> sorted = new SortedList<>(Filter);
        sorted.comparatorProperty().bind(Truck_Table.comparatorProperty());
        Truck_Table.setItems(sorted);
        db.Disconnect();
    }

}

