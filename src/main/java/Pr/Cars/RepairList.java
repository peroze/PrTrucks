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

public class RepairList implements Initializable {


    RotateTransition rotate;

    @FXML
    private Label Filter;

    @FXML
    private Button Delete_Button;

    @FXML
    private TableView<ModelRepair> Truck_Table;

    @FXML
    private TableColumn<ModelRepair, String> LisancePlate_Column;

    @FXML
    private TableColumn<ModelRepair, String> Type_Column;

    @FXML
    private TableColumn<ModelRepair, String> Date_Column;

    @FXML
    private TableColumn<ModelRepair, String> Workshop_Column;

    @FXML
    private TableColumn<ModelRepair, String> KM_Column;

    @FXML
    private ImageView Import_Button;

    @FXML
    private TextField Search_Bar;

    @FXML
    private Label Search_icon;



    private ObservableList<ModelRepair> Oblist;

    private boolean edit;


    @FXML
    void Import_Button_Pressed(MouseEvent event) {
        Stage primaryStage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("AddRepair.fxml"));
        try {
            Parent root = fxmlloader.load();
            if (edit == true) {
                edit = false;
                fxmlloader.<AddRepair>getController().edit(Truck_Table.getSelectionModel().getSelectedItem());
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

    @FXML
    void Delete_Button_Pressed(ActionEvent event) {
        try{
        Sql sql = new Sql();
        ModelRepair temp = Truck_Table.getSelectionModel().getSelectedItem();
        if (!(temp == null)) {
            int tempKm = Integer.valueOf(temp.getKilometers());
            String tempLisc=temp.getLiscPlate();
            int km = sql.Query_Specific_NextServiceKmCurrentKm(sql.GetIdFromLisx(temp.getLiscPlate())).getInt("Kilometers");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Επιβαιβέωση");
            alert.setHeaderText("Διαγραφή στοιχείου");
            alert.setContentText("Είσαι σύγουρος ότι θέλεις να διαγράψεις την Επισκευή με  id=" + Integer.valueOf(temp.getId()));          // Διαγραφή
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
                int k = sql.DeleteRepair(Integer.valueOf(temp.getId()));
                if (k == 0) {
                    throw new SQLException();
                }
               if (k == 1) {
                    if (fl == true) {
                        ResultSet rs1 = sql.Query_Specific_MaxKmAllMinusTrucks(sql.GetIdFromLisx(tempLisc));
                        int newKM=rs1.getInt("MAX(KM)");
                        ResultSet rs = sql.Query_Specific_Trucks(tempLisc);
                        ModelTruck repl = new ModelTruck(rs.getString("id"), rs.getString("LiscPlate"), rs.getString("Manufactor"), rs.getString("Model"), rs.getString("First_Date"), rs.getString("Plaisio"), rs.getString("Type"), rs.getString("Location"), String.valueOf(newKM), rs.getString("Data"), rs.getString("ServiceInKm"), rs.getString("KTEOIn"), rs.getString("GasIn"));
                        int i=sql.InsertCar(repl,-1,true);
                        if(i==0){
                          throw new SQLException();
                        }
                    }
                }
            }
            sql.Disconnect();
            RenewTable(null);
        }
        }catch (SQLException e){
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Αποτυχία");
            alert2.setContentText("Η διαγραφή απέτυχε, προσπαθύστε ξανά");
            alert2.showAndWait();
        }catch (NumberFormatException e){
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Αποτυχία");
            alert2.setContentText("Η διαγραφή απέτυχε, προσπαθύστε ξανά");
            alert2.showAndWait();
        }

    }

    @FXML
    void Select_Filter_pressed(MouseEvent event){
        if(Filter.getText().equals("Φιλτρα")) {
            List<String> choices = new ArrayList<>();
            Sql sql = new Sql();
            ResultSet rs = sql.Query_All_Lisc();

            try {
                while (rs.next()) {
                    choices.add(rs.getString("LiscPlate"));
                }
            } catch (SQLException e) {
                sql.Disconnect();
                e.printStackTrace();
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>("Πινακίδα", choices);
            dialog.setTitle("Φιλτράρισμα Πινακίδας");
            dialog.setHeaderText("Επέλεξε ένα αυτοκήνιτο");
            dialog.setContentText("Όχημα");
            Optional<String> result = dialog.showAndWait();
            String temp = result.toString().replace("[", "");
            temp = temp.replace("]", "");
            temp = temp.replace("Optional", "");
            if(temp.equals(".empty")||temp.equals("Πινακίδα")){
                sql.Disconnect();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Sql db = new Sql();
        ResultSet rs = db.Query_General_Repair();
        Oblist = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Oblist.add(new ModelRepair(db.GetLisxxFromId(rs.getString("id")),rs.getString("Price"), rs.getString("Kilometers"), rs.getString("Date"), rs.getString("Discreption"), rs.getString("Workshop"), rs.getString("Changes"),rs.getString("Receipt_Number"),rs.getString("WorkPrice"), rs.getString("Repair_Id") ));
            }
        } catch (SQLException e) {
            db.Disconnect();
            e.printStackTrace();
        }
        Truck_Table.setRowFactory((tv-> {
            TableRow<ModelRepair> row=new TableRow<>();
            row.setOnMouseClicked(event->{
                if (event.getClickCount()==2&& (!row.isEmpty())){
                    DoubleClickTable();
                }
            });
            return row;
        }));
        LisancePlate_Column.setCellValueFactory(new PropertyValueFactory<>("LiscPlate"));
        Type_Column.setCellValueFactory(new PropertyValueFactory<>("Discreption"));
        Workshop_Column.setCellValueFactory(new PropertyValueFactory<>("Workshop"));
        Date_Column.setCellValueFactory(new PropertyValueFactory<>("Date"));
        KM_Column.setCellValueFactory(new PropertyValueFactory<>("Kilometers"));
        FilteredList<ModelRepair> Filter = new FilteredList<>(Oblist, b -> true);
        Search_Bar.textProperty().addListener((observable, oldValue, newValue) -> {
            Filter.setPredicate(ModelRepair -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String LowerCase = newValue.toLowerCase();
                if (ModelRepair.getId().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelRepair.getLiscPlate().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelRepair.getDate().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelRepair.getKilometers().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelRepair.getPrice().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                }else if (ModelRepair.getDiscreption().toLowerCase().indexOf(LowerCase) != -1) {
                        return true;
                } else {
                    if (!(ModelRepair.getWorkshop() == null)) {
                        if (ModelRepair.getWorkshop().toLowerCase().indexOf(LowerCase) != -1) {
                            return true;
                        } else return false;
                    } else return false;
                }
            });
        });
        SortedList<ModelRepair> sorted = new SortedList<>(Filter);
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

    void DoubleClickTable() {
        ModelRepair temp = Truck_Table.getSelectionModel().getSelectedItem();
        Stage primaryStage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ViewRepair.fxml"));
        try {
            Parent root = fxmlloader.load();
            fxmlloader.<ViewRepair>getController().setService(temp);
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

    public void SearchByLisc(String Lisc){
        Sql  sql=new Sql();
        ResultSet rs=sql.Query_Specific_With_Lisc(Lisc,"Repairs");
        RenewTable(rs);
        sql.Disconnect();
    }

    public void RenewTable(ResultSet rs1) {
        Sql db = new Sql();
        ResultSet rs=null;
        if (rs1==null){
             rs = db.Query_General_Repair();
        }
        else{
            rs=rs1;
        }
        Oblist = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Oblist.add(new ModelRepair(db.GetLisxxFromId(rs.getString("id")),rs.getString("Price"), rs.getString("Kilometers"), rs.getString("Date"), rs.getString("Discreption"), rs.getString("Workshop"), rs.getString("Changes"),rs.getString("Receipt_Number"),rs.getString("WorkPrice"), rs.getString("Repair_Id")) );
            }
        } catch (SQLException e) {
            db.Disconnect();
            e.printStackTrace();
        }
        Truck_Table.setRowFactory((tv-> {
            TableRow<ModelRepair> row=new TableRow<>();
            row.setOnMouseClicked(event->{
                if (event.getClickCount()==2&& (!row.isEmpty())){
                    DoubleClickTable();
                }
            });
            return row;
        }));
        LisancePlate_Column.setCellValueFactory(new PropertyValueFactory<>("LiscPlate"));
        Type_Column.setCellValueFactory(new PropertyValueFactory<>("Discreption"));
        Workshop_Column.setCellValueFactory(new PropertyValueFactory<>("Workshop"));
        Date_Column.setCellValueFactory(new PropertyValueFactory<>("Date"));
        KM_Column.setCellValueFactory(new PropertyValueFactory<>("Kilometers"));
        FilteredList<ModelRepair> Filter = new FilteredList<>(Oblist, b -> true);

        Search_Bar.textProperty().addListener((observable, oldValue, newValue) -> {
            Filter.setPredicate(ModelRepair -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String LowerCase = newValue.toLowerCase();
                if (ModelRepair.getId().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelRepair.getLiscPlate().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelRepair.getDate().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelRepair.getKilometers().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelRepair.getPrice().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                }else if (ModelRepair.getDiscreption().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else {
                    if (!(ModelRepair.getWorkshop() == null)) {
                        if (ModelRepair.getWorkshop().toLowerCase().indexOf(LowerCase) != -1) {
                            return true;
                        } else return false;
                    } else return false;
                }
            });
        });
        SortedList<ModelRepair> sorted = new SortedList<>(Filter);
        sorted.comparatorProperty().bind(Truck_Table.comparatorProperty());
        Truck_Table.setItems(sorted);
        db.Disconnect();
    }

}

