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
public class ExternalCarsList implements Initializable {


    RotateTransition rotate;

    @FXML
    private Button Delete_Button;

    @FXML
    private Label Filter;

    @FXML
    private TableView<ModelExternalCars> Truck_Table;

    @FXML
    private TableColumn<ModelExternalCars, String> LisancePlate_Column;

    @FXML
    private TableColumn<ModelExternalCars, String> Company_Col;

    @FXML
    private TableColumn<ModelExternalCars, String> Width_Col;

    @FXML
    private TableColumn<ModelExternalCars, String> Height_Column;

    @FXML
    private TableColumn<ModelExternalCars, String> Lenght_Col;

    @FXML
    private TableColumn<ModelExternalCars, String> Drver_Col;

    @FXML
    private TableColumn<ModelExternalCars, String> Phone_Col;

    @FXML
    private TableColumn<ModelExternalCars, String> Manufactor_Column;

    @FXML
    private TableColumn<ModelExternalCars, String> Model_Column;

    @FXML
    private ContextMenu Cont;

    @FXML
    private ImageView Import_Button;

    @FXML
    private TextField Search_Bar;

    @FXML
    private Label Search_icon;



    private boolean edit;
    private ObservableList<ModelExternalCars> Oblist;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Sql db = new Sql();
        ResultSet rs = db.Query_General_External_Trucks();
        Oblist = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Oblist.add(new ModelExternalCars( rs.getString("id"),rs.getString("LiscPlate"), rs.getString("Manufactor"), rs.getString("Model"), db.GetCompFromId(rs.getString("Company_id")), rs.getString("Width"), rs.getString("Lenght"), rs.getString("Height"), rs.getString("Driver"), rs.getString("Phone")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LisancePlate_Column.setCellValueFactory(new PropertyValueFactory<>("LiscPlate"));
        Manufactor_Column.setCellValueFactory(new PropertyValueFactory<>("Manufactor"));
        Model_Column.setCellValueFactory(new PropertyValueFactory<>("Model"));
        Phone_Col.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        Company_Col.setCellValueFactory(new PropertyValueFactory<>("Company"));
        Width_Col.setCellValueFactory(new PropertyValueFactory<>("Width"));
        Lenght_Col.setCellValueFactory(new PropertyValueFactory<>("Lenght"));
        Height_Column.setCellValueFactory(new PropertyValueFactory<>("Height"));
        Drver_Col.setCellValueFactory(new PropertyValueFactory<>("Driver"));

        FilteredList<ModelExternalCars> Filter = new FilteredList<>(Oblist, b -> true);

        Search_Bar.textProperty().addListener((observable, oldValue, newValue) -> {
            Filter.setPredicate(ModelExternalCars -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String LowerCase = newValue.toLowerCase();
                if (ModelExternalCars.getId().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelExternalCars.getLiscPlate().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelExternalCars.getCompany().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelExternalCars.getManufactor().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelExternalCars.getModel().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelExternalCars.getDriver().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else return false;
            });
        });
        SortedList<ModelExternalCars> sorted = new SortedList<>(Filter);
        sorted.comparatorProperty().bind(Truck_Table.comparatorProperty());
        Truck_Table.setItems(sorted);
        MenuItem Del = new MenuItem();
        Del.setText("Διαγραφή");
        Del.setOnAction(this::Delete_Button_Pressed);

        MenuItem Edits = new MenuItem();
        Edits.setText("Επεξεργασία");
        Edits.setOnAction(this::Ed);
        Cont.getItems().add(Edits);
        Cont.getItems().add(Del);
        db.Disconnect();
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
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("AddExternalCar.fxml"));
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
                fxmlloader.<AddExternalCar>getController().edit(Truck_Table.getSelectionModel().getSelectedItem());
            }
            AddExternalCar addcar = fxmlloader.getController();
            primaryStage.setOnHidden(e -> {
                RenewTable(null);
            });
            primaryStage.setOnCloseRequest(e -> {
                RenewTable(null);
            });
            primaryStage.setTitle("PrTrucks");
            primaryStage.setScene(new Scene(root, 600, 741));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void Select_Filter_pressed(MouseEvent event){
        if(Filter.getText().equals("Φιλτρα")) {
            List<String> choices = new ArrayList<>();
            Sql sql = new Sql();
            ResultSet rs = sql.Query_All_Companies();

            try {
                while (rs.next()) {
                    choices.add(rs.getString("Name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            choices.add("Ιδιότης");
            ChoiceDialog<String> dialog = new ChoiceDialog<>("Πινακίδα", choices);
            dialog.setTitle("Φιλτράρισμα Πινακίδας");
            dialog.setHeaderText("Επέλεξε μια Εταιρία ");
            dialog.setContentText("Εταιρία");
            Optional<String> result = dialog.showAndWait();
            String temp = result.toString().replace("[", "");
            temp = temp.replace("]", "");
            temp = temp.replace("Optional", "");
            if(temp.equals(".empty")||temp.equals("Πινακίδα")){
                return;
            };
            SearchByLisc(temp);
            sql.Disconnect();
            Filter.setText("Κατάργηση Φίλτρου");
            return;
        }

        Filter.setText("Φιλτρα");
        RenewTable(null);
    }

    public void SearchByLisc(String Lisc){
        Sql  sql=new Sql();
        ResultSet rs=sql.Query_Specific_ExternalByLisc(Lisc);
        RenewTable(rs);
        sql.Disconnect();
    }

    /**
     * This method Deletes a car when the Delete button is pressed
     *
     * @param event The event
     */
    @FXML
    void Delete_Button_Pressed(ActionEvent event) {
        ModelExternalCars temp = Truck_Table.getSelectionModel().getSelectedItem();
        if (!(temp == null)) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Επιβαιβέωση");
            alert.setHeaderText("Διαγραφή στοιχείου");
            alert.setContentText("Είσαι σύγουρος ότι θέλεις να διαγράψεις το όχημα με πινακίδα " + temp.getLiscPlate() + ".");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Sql sql = new Sql();
                int k = sql.DeleteExternal_Truck(Integer.valueOf(temp.getId()));
                if (k == 0) {
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Αποτυχία");
                    alert2.setContentText("Η διαγραφή απέτυχε, προσπαθύστε ξανά");
                    alert2.showAndWait();
                }
                sql.Disconnect();
                RenewTable(null);
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
    public void RenewTable(ResultSet rs) {
        Sql db = new Sql();
        if(rs==null) {
            rs = db.Query_General_External_Trucks();
        }
        Oblist = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Oblist.add(new ModelExternalCars( rs.getString("id"),rs.getString("LiscPlate"), rs.getString("Manufactor"), rs.getString("Model"), db.GetCompFromId(rs.getString("Company_id")), rs.getString("Width"), rs.getString("Lenght"), rs.getString("Height"), rs.getString("Driver"), rs.getString("Phone")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        FilteredList<ModelExternalCars> Filter = new FilteredList<>(Oblist, b -> true);

        Search_Bar.textProperty().addListener((observable, oldValue, newValue) -> {
            Filter.setPredicate(ModelExternalCars -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String LowerCase = newValue.toLowerCase();
                if (ModelExternalCars.getId().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelExternalCars.getLiscPlate().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelExternalCars.getCompany().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelExternalCars.getManufactor().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelExternalCars.getModel().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelExternalCars.getDriver().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else return false;
            });
        });
        SortedList<ModelExternalCars> sorted = new SortedList<>(Filter);
        sorted.comparatorProperty().bind(Truck_Table.comparatorProperty());
        Truck_Table.setItems(sorted);
        db.Disconnect();
    }

}

