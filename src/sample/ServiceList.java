package sample;

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
import java.util.Optional;
import java.util.ResourceBundle;

public class ServiceList implements Initializable {


    RotateTransition rotate;

    @FXML
    private Button Delete_Button;

    @FXML
    private TableView<ModelService> Truck_Table;

    @FXML
    private TableColumn<ModelService, String> LisancePlate_Column;

    @FXML
    private TableColumn<ModelService, String> Type_Column;

    @FXML
    private TableColumn<ModelService, String> Service_Id_Column;

    @FXML
    private TableColumn<ModelService, String> Date_Column;

    @FXML
    private TableColumn<ModelService, String> Workshop_Column;

    @FXML
    private TableColumn<ModelService, String> KM_Column;

    @FXML
    private TableColumn<ModelService, String> NextD_Column;

    @FXML
    private TableColumn<ModelService, String> NextK_Column;

    @FXML
    private ImageView Import_Button;

    @FXML
    private TextField Search_Bar;

    @FXML
    private Label Search_icon;

    private ObservableList<ModelService> Oblist;


    @FXML
    void Import_Button_Pressed(MouseEvent event) {
        Stage primaryStage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("AddService.fxml"));
        try {
            Parent root = fxmlloader.load();
            primaryStage.setOnHidden(e -> {
                RenewTable();
            });
            primaryStage.setOnCloseRequest(e -> {
                RenewTable();
            });
            primaryStage.setTitle("PrTrucks");
            primaryStage.setScene(new Scene(root, 600, 580));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
            //fxmlloader.<Drawer>getController().setParent(this);

            //Border_Pane.setLeft(root2);
            //new FadeIn(root).play();
            //new FadeIn(root2).play();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void Delete_Button_Pressed(ActionEvent event) {
        ModelService temp = Truck_Table.getSelectionModel().getSelectedItem();
        if (!(temp == null)) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Επιβαιβέωση");
            alert.setHeaderText("Διαγραφή στοιχείου");
            alert.setContentText("Είσαι σύγουρος ότι θέλεις να διαγράψεις το Service με  id="+Integer.valueOf(temp.getId()));          // Διαγραφή
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Sql sql = new Sql();
                int k = sql.DeleteService(Integer.valueOf(temp.getId()));
                if (k == 0) {
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Αποτυχία");
                    alert2.setContentText("Η διαγραφή απέτυχε, προσπαθύστε ξανά");
                    alert2.showAndWait();
                }
                RenewTable();
            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Sql db = new Sql();
        ResultSet rs = db.Query_General_Service();
        Oblist = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Oblist.add(new ModelService(db.GetLisxxFromId(rs.getString("id")), rs.getString("Date"), rs.getString("Kilometers"), rs.getString("Type"), rs.getString("Changes"), rs.getString("Workshop"), rs.getString("Next_Date"), rs.getString("Next_Kilometers"), rs.getString("Price"), rs.getString("Service_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Service_Id_Column.setCellValueFactory(new PropertyValueFactory<>("id"));
        LisancePlate_Column.setCellValueFactory(new PropertyValueFactory<>("LiscPlate"));
        Type_Column.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Workshop_Column.setCellValueFactory(new PropertyValueFactory<>("Workshop"));
        Date_Column.setCellValueFactory(new PropertyValueFactory<>("Date"));
        NextD_Column.setCellValueFactory(new PropertyValueFactory<>("NextDate"));
        NextK_Column.setCellValueFactory(new PropertyValueFactory<>("NextKilometers"));
        KM_Column.setCellValueFactory(new PropertyValueFactory<>("Kilometers"));
        FilteredList<ModelService> Filter = new FilteredList<>(Oblist, b -> true);

        Search_Bar.textProperty().addListener((observable, oldValue, newValue) -> {
            Filter.setPredicate(ModelService -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String LowerCase = newValue.toLowerCase();
                if (ModelService.getId().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelService.getLiscPlate().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelService.getDate().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelService.getKilometers().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelService.getNextDate().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelService.getNextKilometers().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelService.getPrice().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else {
                    if (!(ModelService.getWorkshop() == null)) {
                        if (ModelService.getWorkshop().toLowerCase().indexOf(LowerCase) != -1) {
                            return true;
                        } else return false;
                    } else return false;
                }
            });
        });
        SortedList<ModelService> sorted = new SortedList<>(Filter);
        sorted.comparatorProperty().bind(Truck_Table.comparatorProperty());
        Truck_Table.setItems(sorted);
    }

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

    public void RenewTable() {
        Oblist = FXCollections.observableArrayList();
        Sql db = new Sql();
        ResultSet rs = db.Query_General_Service();
        Oblist = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Oblist.add(new ModelService(db.GetLisxxFromId(rs.getString("id")), rs.getString("Date"), rs.getString("Kilometers"), rs.getString("Type"), rs.getString("Changes"), rs.getString("Workshop"), rs.getString("Next_Date"), rs.getString("Next_Kilometers"), rs.getString("Price"), rs.getString("Service_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        FilteredList<ModelService> Filter = new FilteredList<>(Oblist, b -> true);
        Search_Bar.textProperty().addListener((observable, oldValue, newValue) -> {
            Filter.setPredicate(ModelService -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String LowerCase = newValue.toLowerCase();
                if (ModelService.getId().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelService.getLiscPlate().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelService.getDate().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelService.getKilometers().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelService.getNextDate().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelService.getNextKilometers().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelService.getPrice().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else {
                    if (!(ModelService.getWorkshop() == null)) {
                        if (ModelService.getWorkshop().toLowerCase().indexOf(LowerCase) != -1) {
                            return true;
                        } else return false;
                    } else return false;
                }
            });
        });
        SortedList<ModelService> sorted = new SortedList<>(Filter);
        sorted.comparatorProperty().bind(Truck_Table.comparatorProperty());
        Truck_Table.setItems(sorted);
    }

}

