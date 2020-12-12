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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class InternalPhoneList implements Initializable {


    RotateTransition rotate;

    @FXML
    private Label Filter;

    @FXML
    private Button Delete_Button;

    @FXML
    private TableView<ModelInternalPhoneList> Truck_Table;

    @FXML
    private TableColumn<ModelInternalPhoneList, String> Id_Column;

    @FXML
    private TableColumn<ModelInternalPhoneList, String> Name_Column;

    @FXML
    private TableColumn<ModelInternalPhoneList, String> Phone_Column;

    @FXML
    private TableColumn<ModelInternalPhoneList, String> Email_Column;

    @FXML
    private TableColumn<ModelInternalPhoneList, String> Posistion_Column;

    @FXML
    private ImageView Import_Button;

    @FXML
    private TextField Search_Bar;

    @FXML
    private Label Search_icon;

    private ObservableList<ModelInternalPhoneList> Oblist;

    private boolean edit;


    @FXML
    void Import_Button_Pressed(MouseEvent event) {
       /* Stage primaryStage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("AddRepair.fxml"));
        try {
            Parent root = fxmlloader.load();
            if (edit == true) {
                edit = false;
              //  fxmlloader.<AddRepair>getController().edit(Truck_Table.getSelectionModel().getSelectedItem());
            }
            primaryStage.setOnHidden(e -> {
                RenewTable(null);
            });
            primaryStage.setOnCloseRequest(e -> {
                RenewTable(null);
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
        }*/

    }

    @FXML
    void Delete_Button_Pressed(ActionEvent event) {
        try{
        Sql sql = new Sql();
        ModelInternalPhoneList temp = Truck_Table.getSelectionModel().getSelectedItem();
        if (!(temp == null)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Επιβαιβέωση");
            alert.setHeaderText("Διαγραφή στοιχείου");
            alert.setContentText("Είσαι σύγουρος ότι θέλεις να διαγράψεις τον Συνεγράτη με όνομα:" + temp.getName());          // Διαγραφή
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                int k = sql.DeleteInternalPhone(Integer.valueOf(temp.getId()));
                if (k == 0) {
                    throw new SQLException();
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
            ResultSet rs = sql.Query_General_Posistion();

            try {
                while (rs.next()) {
                    choices.add(rs.getString("Posistion"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ChoiceDialog<String> dialog = new ChoiceDialog<>("Θέση", choices);
            dialog.setTitle("Φιλτράρισμα Θέσης Εργασίας");
            dialog.setHeaderText("Επέλεξε μια Θέση");
            dialog.setContentText("Θέση");
            Optional<String> result = dialog.showAndWait();
            String temp = result.toString().replace("[", "");
            temp = temp.replace("]", "");
            temp = temp.replace("Optional", "");
            if(temp.equals(".empty")||temp.equals("Θέση")){
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
        ResultSet rs = db.Query_General_Internal_Phone_List();
        Oblist = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Oblist.add(new ModelInternalPhoneList(rs.getString("id"),rs.getString("Name"),rs.getString("Phone"),rs.getString("Email"),rs.getString("Posistion")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Truck_Table.setRowFactory((tv-> {
            TableRow<ModelInternalPhoneList> row=new TableRow<>();
            row.setOnMouseClicked(event->{
                if (event.getClickCount()==2&& (!row.isEmpty())){
                    DoubleClickTable();
                }
            });
            return row;
        }));
        Id_Column.setCellValueFactory(new PropertyValueFactory<>("Id"));
        Name_Column.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Phone_Column.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        Email_Column.setCellValueFactory(new PropertyValueFactory<>("Email"));
        Posistion_Column.setCellValueFactory(new PropertyValueFactory<>("Posistion"));
        FilteredList<ModelInternalPhoneList> Filter = new FilteredList<>(Oblist, b -> true);

        Search_Bar.textProperty().addListener((observable, oldValue, newValue) -> {
            Filter.setPredicate(ModelInternalPhoneList -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String LowerCase = newValue.toLowerCase();
                if (ModelInternalPhoneList.getId().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelInternalPhoneList.getName().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelInternalPhoneList.getPhone().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                }else if (ModelInternalPhoneList.getPosistion().toLowerCase().indexOf(LowerCase) != -1) {
                        return true;
                } else {
                    if (!(ModelInternalPhoneList.getEmail() == null)) {
                        if (ModelInternalPhoneList.getEmail().toLowerCase().indexOf(LowerCase) != -1) {
                            return true;
                        } else return false;
                    }
                    else return false;
                }
            });
        });
        SortedList<ModelInternalPhoneList> sorted = new SortedList<>(Filter);
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
        ModelInternalPhoneList temp = Truck_Table.getSelectionModel().getSelectedItem();
        Stage primaryStage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ViewRepair.fxml"));
       /* try {
            Parent root = fxmlloader.load();
            fxmlloader.<ViewRepair>getController().setService(temp);
            primaryStage.setTitle("PrTrucks");
            primaryStage.setScene(new Scene(root, 708, 646));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
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

    public void SearchByLisc(String Disc){
        Sql  sql=new Sql();
        ResultSet rs=sql.Query_Specific_Internal_Phone_List(Disc);
        sql.Disconnect();
        RenewTable(rs);
    }

    public void RenewTable(ResultSet rs1) {
        Sql db = new Sql();
        ResultSet rs=null;
        if (rs1==null){
             rs = db.Query_General_Internal_Phone_List();
        }
        else{
            rs=rs1;
        }
        Oblist = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Oblist.add(new ModelInternalPhoneList(rs.getString("id"),rs.getString("Name"),rs.getString("Phone"),rs.getString("Email"),rs.getString("Posistion")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Truck_Table.setRowFactory((tv-> {
            TableRow<ModelInternalPhoneList> row=new TableRow<>();
            row.setOnMouseClicked(event->{
                if (event.getClickCount()==2&& (!row.isEmpty())){
                    DoubleClickTable();
                }
            });
            return row;
        }));
        FilteredList<ModelInternalPhoneList> Filter = new FilteredList<>(Oblist, b -> true);
        Search_Bar.textProperty().addListener((observable, oldValue, newValue) -> {
            Filter.setPredicate(ModelInternalPhoneList -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String LowerCase = newValue.toLowerCase();
                if (ModelInternalPhoneList.getId().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelInternalPhoneList.getName().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelInternalPhoneList.getPhone().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                }else if (ModelInternalPhoneList.getPosistion().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else {
                    if (!(ModelInternalPhoneList.getEmail() == null)) {
                        if (ModelInternalPhoneList.getEmail().toLowerCase().indexOf(LowerCase) != -1) {
                            return true;
                        } else return false;
                    }
                    else return false;
                }
            });
        });
        SortedList<ModelInternalPhoneList> sorted = new SortedList<>(Filter);
        sorted.comparatorProperty().bind(Truck_Table.comparatorProperty());
        Truck_Table.setItems(sorted);
        db.Disconnect();
    }

}

