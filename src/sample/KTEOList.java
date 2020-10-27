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

public class KTEOList implements Initializable {


    RotateTransition rotate;

    @FXML
    private TableView<ModelKTEO> Truck_Table;


    @FXML
    private TableColumn<ModelKTEO, String> LisancePlate_Column;

    @FXML
    private TableColumn<ModelKTEO, String> Date_Column;

    @FXML
    private TableColumn<ModelKTEO, String> Kilometers_Column;

    @FXML
    private TableColumn<ModelKTEO, String> Company_Column;

    @FXML
    private TableColumn<ModelKTEO, String> Next_Column;

    @FXML
    private TableColumn<ModelKTEO, String> Price_Column;

    @FXML
    private TableColumn<ModelKTEO, String> Warnings_Column;

    @FXML
    private ImageView Import_Button;

    @FXML
    private Button Delete_Button;

    @FXML
    private TextField Search_Bar;

    private ObservableList<ModelKTEO> Oblist;


    @FXML
    void Import_Button_Pressed(MouseEvent event) {
        /*Stage primaryStage= new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("AddCar.fxml"));
        try {
            Parent root=fxmlloader.load();
            int max=Integer.valueOf(Oblist.get(Oblist.size()-1).getId());
            fxmlloader.<AddCar>getController().setMax_i(max);
            AddCar addcar =fxmlloader.getController();
            primaryStage.setOnHidden(e->{
                RenewTable();
            });
            primaryStage.setOnCloseRequest(e->{
                RenewTable();
            });
            primaryStage.setTitle("PrTrucks");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
            //fxmlloader.<Drawer>getController().setParent(this);

            //Border_Pane.setLeft(root2);
            //new FadeIn(root).play();
            //new FadeIn(root2).play();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }

    @FXML
    void Delete_Button_Pressed(ActionEvent event) {
        ModelKTEO temp=Truck_Table.getSelectionModel().getSelectedItem();
        if (!(temp==null)) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Επιβαιβέωση");
            alert.setHeaderText("Διαγραφή στοιχείου");
            alert.setContentText("Είσαι σύγουρος ότι θέλεις να διαγράψεις το ΚΤΕΟ με πινακίδα " + temp.getLiscPlate() + ".");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Sql sql = new Sql();
                int k =sql.DeleteΚΤΕΟ(temp.getLiscPlate());
                if(k==0){
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
        ResultSet rs = db.Query_General_KTEO();
        Oblist = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Oblist.add(new ModelKTEO(db.GetLisxxFromId(rs.getString("id")), rs.getString("Price"),rs.getString("Kilometers"), rs.getString("Date"),rs.getString("Warnings"),rs.getString("DateNext"), rs.getString("Company")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LisancePlate_Column.setCellValueFactory(new PropertyValueFactory<>("LiscPlate"));
        Company_Column.setCellValueFactory(new PropertyValueFactory<>("Company"));
        Price_Column.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Warnings_Column.setCellValueFactory(new PropertyValueFactory<>("Warnings"));
        Date_Column.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Kilometers_Column.setCellValueFactory(new PropertyValueFactory<>("Kilometers"));
        Next_Column.setCellValueFactory(new PropertyValueFactory<>("Next"));
        FilteredList<ModelKTEO> Filter =new FilteredList<>(Oblist,b->true);

        Search_Bar.textProperty().addListener((observable,oldValue,newValue) ->{
            Filter.setPredicate(ModelKTEO->{
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                String LowerCase=newValue.toLowerCase();
                if (ModelKTEO.getLiscPlate().toLowerCase().indexOf(LowerCase)!=-1){
                    return true;
                }
                else if (ModelKTEO.getCompany().toLowerCase().indexOf(LowerCase)!=-1){
                    return true;
                }
                else if (ModelKTEO.getDate().toLowerCase().indexOf(LowerCase)!=-1){
                    return true;
                }
                else if (ModelKTEO.getKilometers().toLowerCase().indexOf(LowerCase)!=-1){
                    return true;
                }
                else if (ModelKTEO.getNext().toLowerCase().indexOf(LowerCase)!=-1){
                    return true;
                }

                else if (ModelKTEO.getPrice().toLowerCase().indexOf(LowerCase)!=-1){
                    return true;
                }
                else {
                    if(!(ModelKTEO.getWarnings()==null)) {
                        if (ModelKTEO.getWarnings().toLowerCase().indexOf(LowerCase) != -1) {
                            return true;
                        }
                    }
                    else return false;
                }
                return false;
            });
        } );
        SortedList<ModelKTEO> sorted=new SortedList<>(Filter);
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

    public void RenewTable(){

        Sql db = new Sql();
        ResultSet rs = db.Query_General_KTEO();
        Oblist = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Oblist.add(new ModelKTEO(db.GetLisxxFromId(rs.getString("id")), rs.getString("Price"),rs.getString("Kilometers"), rs.getString("Date"),rs.getString("Warnings"),rs.getString("Next"), rs.getString("Company")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LisancePlate_Column.setCellValueFactory(new PropertyValueFactory<>("LiscPlate"));
        Company_Column.setCellValueFactory(new PropertyValueFactory<>("Company"));
        Price_Column.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Warnings_Column.setCellValueFactory(new PropertyValueFactory<>("Warnings"));
        Date_Column.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Kilometers_Column.setCellValueFactory(new PropertyValueFactory<>("Kilometers"));
        Next_Column.setCellValueFactory(new PropertyValueFactory<>("Next"));
        FilteredList<ModelKTEO> Filter =new FilteredList<>(Oblist,b->true);

        Search_Bar.textProperty().addListener((observable,oldValue,newValue) ->{
            Filter.setPredicate(ModelKTEO->{
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                String LowerCase=newValue.toLowerCase();
                if (ModelKTEO.getLiscPlate().toLowerCase().indexOf(LowerCase)!=-1){
                    return true;
                }
                else if (ModelKTEO.getCompany().toLowerCase().indexOf(LowerCase)!=-1){
                    return true;
                }
                else if (ModelKTEO.getDate().toLowerCase().indexOf(LowerCase)!=-1){
                    return true;
                }
                else if (ModelKTEO.getKilometers().toLowerCase().indexOf(LowerCase)!=-1){
                    return true;
                }
                else if (ModelKTEO.getNext().toLowerCase().indexOf(LowerCase)!=-1){
                    return true;
                }

                else if (ModelKTEO.getPrice().toLowerCase().indexOf(LowerCase)!=-1){
                    return true;
                }
                else {
                    if(!(ModelKTEO.getWarnings()==null)) {
                        if (ModelKTEO.getWarnings().toLowerCase().indexOf(LowerCase) != -1) {
                            return true;
                        }
                    }
                    else return false;
                }
                return false;
            });
        } );
        SortedList<ModelKTEO> sorted=new SortedList<>(Filter);
        sorted.comparatorProperty().bind(Truck_Table.comparatorProperty());
        Truck_Table.setItems(sorted);
    }

}

