package sample;

import animatefx.animation.FadeIn;
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
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import javax.swing.text.TabableView;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TrucksList implements Initializable {


    RotateTransition rotate;

    @FXML
    private TableView<ModelTruck> Truck_Table;

    @FXML
    private TableColumn<ModelTruck, String> id_Column;

    @FXML
    private TableColumn<ModelTruck, String> LisancePlate_Column;

    @FXML
    private TableColumn<ModelTruck, String> Manufactor_Column;

    @FXML
    private TableColumn<ModelTruck, String> Model_Column;

    @FXML
    private TableColumn<ModelTruck, String> Service_Column;

    @FXML
    private TableColumn<ModelTruck, String> Location_Column;

    @FXML
    private TableColumn<ModelTruck, String> Type_Column;

    @FXML
    private TableColumn<ModelTruck, String> Plaisio_Column;

    @FXML
    private ImageView Import_Button;

    @FXML
    private TextField Search_Bar;

    private ObservableList<ModelTruck> Oblist;


    @FXML
    void Import_Button_Pressed(MouseEvent event) {
        Stage primaryStage= new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("AddCar.fxml"));
        try {
            Parent root=fxmlloader.load();
            int max=Integer.valueOf(Oblist.get(Oblist.size()-1).getId());
            fxmlloader.<AddCar>getController().setMax_i(max);
            primaryStage.setTitle("PrTrucks");
            primaryStage.setScene(new Scene(root, 600, 400));
            //primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
            //fxmlloader.<Drawer>getController().setParent(this);

            //Border_Pane.setLeft(root2);
            //new FadeIn(root).play();
            //new FadeIn(root2).play();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Sql db = new Sql();
        ResultSet rs = db.Query_General_Trucks();
        Oblist = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Oblist.add(new ModelTruck(rs.getString("id"), rs.getString("LiscPlate"), rs.getString("Manufactor"), rs.getString("Model"), rs.getString("First_Date"),rs.getString("Plaisio"),rs.getString("Type"),rs.getString("Location"),rs.getString("Kilometers")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        id_Column.setCellValueFactory(new PropertyValueFactory<>("id"));
        LisancePlate_Column.setCellValueFactory(new PropertyValueFactory<>("LiscPlate"));
        Manufactor_Column.setCellValueFactory(new PropertyValueFactory<>("Manufactor"));
        Model_Column.setCellValueFactory(new PropertyValueFactory<>("Model"));
        Service_Column.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Location_Column.setCellValueFactory(new PropertyValueFactory<>("Location"));
        Plaisio_Column.setCellValueFactory(new PropertyValueFactory<>("Plaisio"));
        Type_Column.setCellValueFactory(new PropertyValueFactory<>("Type"));
        FilteredList<ModelTruck> Filter =new FilteredList<>(Oblist,b->true);

        Search_Bar.textProperty().addListener((observable,oldValue,newValue) ->{
            Filter.setPredicate(ModelTruck->{
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                String LowerCase=newValue.toLowerCase();
                if (ModelTruck.getId().toLowerCase().indexOf(LowerCase)!=-1){
                    return true;
                }
                else if (ModelTruck.getLiscPlate().toLowerCase().indexOf(LowerCase)!=-1){
                    return true;
                }
                else if (ModelTruck.getLocation().toLowerCase().indexOf(LowerCase)!=-1){
                    return true;
                }
                else if (ModelTruck.getManufactor().toLowerCase().indexOf(LowerCase)!=-1){
                    return true;
                }
                else if (ModelTruck.getModel().toLowerCase().indexOf(LowerCase)!=-1){
                    return true;
                }
                else if (ModelTruck.getType().toLowerCase().indexOf(LowerCase)!=-1){
                    return true;
                }

                else if (ModelTruck.getKilometers().toLowerCase().indexOf(LowerCase)!=-1){
                    return true;
                }
                else {
                    if(!(ModelTruck.getPlaisio()==null)) {
                        if (ModelTruck.getPlaisio().toLowerCase().indexOf(LowerCase) != -1) {
                            return true;
                        }
                    }
                    if (!(ModelTruck.getDate()==null)){
                        if (ModelTruck.getDate().toLowerCase().indexOf(LowerCase)!=-1){
                            return true;
                        }
                        else return false;
                    }
                    else return false;
                }
            });
        } );
        SortedList<ModelTruck> sorted=new SortedList<>(Filter);
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

}

