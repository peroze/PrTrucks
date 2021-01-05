package Pr.Cars;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;


/**
 * This class is the controller of  ServiceList.fxml which shows on the screen all Services that is vehicle have done
 * @author peroze
 * @version 1.0 Alpha
 */
public class ServiceList implements Initializable {


    RotateTransition rotate;

    @FXML
    private Label History;

    @FXML
    private Label Filter;
    @FXML
    private Button Delete_Button;

    @FXML
    private TableView<ModelService> Truck_Table;

    @FXML
    private TableColumn<ModelService, String> LisancePlate_Column;

    @FXML
    private TableColumn<ModelService, String> Type_Column;

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

    public boolean edit;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Sql db = new Sql();
        edit=false;
        ResultSet rs = db.Query_Group_Service();
        Oblist = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Oblist.add(new ModelService(db.GetLisxxFromId(rs.getString("id")), rs.getString("MAX(Date)"), rs.getString("Kilometers"), rs.getString("Type"), rs.getString("Changes"), rs.getString("Workshop"), rs.getString("Next_Date"), rs.getString("Next_Kilometers"), rs.getString("Price"), rs.getString("Service_id"),rs.getString("Receipt_Number")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Truck_Table.setRowFactory((tv -> {
            TableRow<ModelService> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    DoubleClickTable();
                }
            });
            return row;
        }));
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
        ContextMenu Cont=new ContextMenu();
        MenuItem view=new MenuItem("Άνοιγμα");
        view.setOnAction(this::View);
        MenuItem Del=new MenuItem("Διαγραφή");
        Del.setOnAction(this::Delete_Button_Pressed);
        MenuItem Edits = new MenuItem("Επεξεργασία");
        Edits.setOnAction(this::Ed);
        Cont.getItems().add(view);
        Cont.getItems().add(Del);
        Cont.getItems().add(Edits);

        Truck_Table.setContextMenu(Cont);
        db.Disconnect();
    }


    /**
     * This method is used to open the all the details of the selected Service when the user double clicks on it
     */
    void DoubleClickTable() {
        ModelService temp = Truck_Table.getSelectionModel().getSelectedItem();
        Stage primaryStage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ViewService.fxml"));
        try {
            Parent root = fxmlloader.load();
            fxmlloader.<ViewService>getController().setService(temp);
            primaryStage.setTitle("PrTrucks");
            primaryStage.setScene(new Scene(root, 708, 646));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void View(ActionEvent event) {
        DoubleClickTable();
    }

    @FXML
    void Ed(ActionEvent event) {
        edit = true;
        Import_Button_Pressed(null);
    }


    /**
     * This method inserts a new Service in the list when the insert button is pressed
     * @param event The event
     */
    @FXML
    void Import_Button_Pressed(MouseEvent event) {
        Stage primaryStage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("AddService.fxml"));
        try {
            Parent root = fxmlloader.load();
            if (edit == true) {
                edit = false;
                fxmlloader.<AddService>getController().edit(Truck_Table.getSelectionModel().getSelectedItem());
            }
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
            //fxmlloader.<Drawer>getController().setParent(this);

            //Border_Pane.setLeft(root2);
            //new FadeIn(root).play();
            //new FadeIn(root2).play();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * This method is used to Filter the data shown on the table.
     * @param event The event
     */
    @FXML
    void Select_History_pressed(MouseEvent event) {
        if (History.getText().equals("Ιστορικό")) {
            Filter.setText("Φίλτρα");
            Filter.setStyle(null);
            ObservableList<String> choices = FXCollections.observableArrayList();
            ObservableList<String> Locations = FXCollections.observableArrayList();
            ObservableList<String> Types = FXCollections.observableArrayList();
            Sql sql = new Sql();
            ResultSet rs = sql.Query_All_Lisc();
            ResultSet rs1=sql.Query_General_Types();
            ResultSet rs2=sql.Query_General_Locations();
            try {
                while (rs.next()) {
                    choices.add(rs.getString("LiscPlate"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                while (rs1.next()) {
                    Types.add(rs1.getString("Type"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                while (rs2.next()) {
                    Locations.add(rs2.getString("City"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>("Πινακίδα",choices);
            dialog.setTitle("Φιλτράρισμα ");
            dialog.setHeaderText("Επέλεξτε από τις παρακάτω κατηγορίες");
            dialog.setContentText("Όχημα: ");
            Optional<String> result = dialog.showAndWait();
            String temp = result.toString().replace("[", "");
            temp = temp.replace("]", "");
            temp = temp.replace("Optional", "");
            if (temp.equals(".empty") || temp.equals("Πινακίδα")) {
                return;
            }
            SearchByLisc(temp);
            History.setText("Επαναφορά");
            History.setStyle("-fx-text-fill: RED");
            sql.Disconnect();
            return;
        }
        History.setText("Ιστορικό");
        History.setStyle(null);
        RenewTable(null);
    }

    @FXML
    public void Select_Filter_pressed(MouseEvent event){
        if(Filter.getText().equals("Φίλτρα")) {
            History.setText("Ιστορικό");
            History.setStyle(null);
            Dialog<ArrayList<String>> dialog = new ChoiceDialog<>();
            ObservableList<String> Locations = FXCollections.observableArrayList();
            ObservableList<String> Types = FXCollections.observableArrayList();
            Sql sql = new Sql();
            ResultSet rs = sql.Query_All_Lisc();
            ResultSet rs1 = sql.Query_General_Types();
            ResultSet rs2 = sql.Query_General_Locations();
            try {
                while (rs1.next()) {
                    Types.add(rs1.getString("Type"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                while (rs2.next()) {
                    Locations.add(rs2.getString("City"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            Label labLoc = new Label("Τοποθεσία");
            ComboBox cb1 = new ComboBox<>();
            cb1.setPromptText("Τοποθεσία");
            cb1.setItems(Locations);
            Label labType = new Label("Τυπος Οχήματος");
            ComboBox cb2 = new ComboBox<>();
            cb2.setPromptText("Τύπος Οχήματος");
            cb2.setItems(Types);
            grid.add(labLoc, 0, 0);
            grid.add(cb1, 1, 0);
            grid.add(labType, 0, 1);
            grid.add(cb2, 1, 1);
            dialog.getDialogPane().setContent(grid);
            dialog.setHeaderText("Επιλογή Φίλτρων");
            FontIcon ico=new FontIcon();
            ico.setIconLiteral("fas-bong");
            ico.setIconSize(30);
            dialog.setGraphic(ico);
            Node button = dialog.getDialogPane().lookupButton(ButtonType.OK);
            Node button1=dialog.getDialogPane().lookupButton(ButtonType.CANCEL);
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    ArrayList<String> reuturns = new ArrayList<>();
                    String TypeAf;
                    String LocAf;
                    int counter = 0;
                    if (cb1.getValue() == null) {
                        TypeAf = null;
                        counter = counter + 1;
                    } else {
                        TypeAf = cb1.getValue().toString();
                    }
                    if (cb2.getValue() == null) {
                        LocAf = null;
                        counter = counter + 1;
                    } else {
                        LocAf = cb2.getValue().toString();
                    }
                    if (counter == 2) {
                        return null;
                    }
                    reuturns.add(TypeAf);
                    reuturns.add(LocAf);
                    return reuturns;
                }
                return null;
            });
            try {
                Optional<ArrayList<String>> result = dialog.showAndWait();

                ResultSet rs3 = sql.Query_Specific_TableByLocationType("Service", result.get().get(1), result.get().get(0));
                RenewTable(rs3);
                Filter.setText("Κατάργησή Φίλτρων");
                Filter.setStyle("-fx-text-fill: RED");
                sql.Disconnect();
            }catch (java.util.NoSuchElementException e){
                return;
            }
        }
        else {
            RenewTable(null);
            Filter.setText("Φίλτρα");
            Filter.setStyle(null);
        }

    }


    /**
     * This method is used to delete a particular Service
     * @param event The Event
     */
    @FXML
    void Delete_Button_Pressed(ActionEvent event) {
        ModelService temp = Truck_Table.getSelectionModel().getSelectedItem();
        Sql sql = new Sql();
        try {
            if (!(temp == null)) {
                int tempKm = Integer.valueOf(temp.getKilometers());
                String tempLisc = temp.getLiscPlate();
                int km = sql.Query_Specific_NextServiceKmCurrentKm(sql.GetIdFromLisx(temp.getLiscPlate())).getInt("Kilometers");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Επιβαιβέωση");
                alert.setHeaderText("Διαγραφή στοιχείου");
                alert.setContentText("Είσαι σύγουρος ότι θέλεις να διαγράψεις το Service με  id=" + Integer.valueOf(temp.getId()));          // Διαγραφή
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    boolean fl = false;
                    if (tempKm == km) {
                        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                        alert1.setTitle("Επιβαιβέωση");
                        alert1.setHeaderText("Εγγράφη με τσ περισσότερα Χιλιομέτρα");
                        alert1.setContentText("Αυτή η εγγράφη  είναι η κατάχώρηση με τα περισότερα χιλίομετρα για το Όχημα.\nΘέλεις να αλλάχθούν και αυτά με τα αμέσως λιγότερα χιλιόμετρα ή να παραμείνουν όπως είναι ");          // Διαγραφή
                        Optional<ButtonType> result1 = alert1.showAndWait();
                        if (result1.get() == ButtonType.OK) {
                            fl = true;
                        }
                    }
                    int k = sql.DeleteService(Integer.valueOf(temp.getId()));
                    if (k == 0) {
                        throw new SQLException( );
                    }
                    if (k == 1) {
                        if (fl == true) {
                            ResultSet rs1 = sql.Query_Specific_MaxKmAllMinusTrucks(sql.GetIdFromLisx(tempLisc));
                            int newKM = rs1.getInt("MAX(KM)");
                            ResultSet rs = sql.Query_Specific_Trucks(tempLisc);
                            ModelTruck repl = new ModelTruck(rs.getString("id"), rs.getString("LiscPlate"), rs.getString("Manufactor"), rs.getString("Model"), rs.getString("First_Date"), rs.getString("Plaisio"), rs.getString("Type"), rs.getString("Location"), String.valueOf(newKM), rs.getString("Data"), rs.getString("ServiceInKm"), rs.getString("KTEOIn"), rs.getString("GasIn"));
                            int i = sql.InsertCar(repl, -1, true);
                            if (i == 0) {
                                throw new SQLException();
                            }
                        }
                    }
                    sql.Disconnect();
                    RenewTable(null);
                }
            }
        }catch (SQLException e){
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Αποτυχία");
            alert2.setContentText("Η διαγραφή απέτυχε, προσπαθύστε ξανά");
            alert2.showAndWait();
        }

    }


    /**
     * This method performs a spin animation of the import button when the users hover over it
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
     * This method is used to preview the data of a spcific car (Only one) on the table
     * @param Lisc The liscence of the car
     */
    public void SearchByLisc(String Lisc) {
        Sql sql = new Sql();
        ResultSet rs = sql.Query_Specific_With_Lisc(Lisc, "Service");
        RenewTable(rs);
        sql.Disconnect();
    }


    /**
     * This method renews the visible table (its the same as initialize())
     *
     * @param rs1 This is used to limit the data to a specific
     */
    public void RenewTable(ResultSet rs1) {
        ResultSet rs = null;
        Sql db = new Sql();
        if (rs1 == null) {
            Oblist = FXCollections.observableArrayList();
            rs = db.Query_Group_Service();
        } else {
            rs = rs1;
        }
        Oblist = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                if (rs1 == null) {
                    Oblist.add(new ModelService(db.GetLisxxFromId(rs.getString("id")), rs.getString("MAX(Date)"), rs.getString("Kilometers"), rs.getString("Type"), rs.getString("Changes"), rs.getString("Workshop"), rs.getString("Next_Date"), rs.getString("Next_Kilometers"), rs.getString("Price"), rs.getString("Service_id"),rs.getString("Receipt_Number")));
                } else {
                    Oblist.add(new ModelService(db.GetLisxxFromId(rs.getString("id")), rs.getString("Date"), rs.getString("Kilometers"), rs.getString("Type"), rs.getString("Changes"), rs.getString("Workshop"), rs.getString("Next_Date"), rs.getString("Next_Kilometers"), rs.getString("Price"), rs.getString("Service_id"),rs.getString("Receipt_Number")));
                }
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
        db.Disconnect();
    }

}

