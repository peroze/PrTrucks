package sample;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    private ImageView Import_Button;

    private ObservableList<ModelTruck> Oblist;


    @FXML
    void Import_Button_Pressed(MouseEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Sql db = new Sql();
        ResultSet rs = db.Query_General_Trucks();
        Oblist = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Oblist.add(new ModelTruck(rs.getString("id"), rs.getString("LiscPlate"), rs.getString("Manufactor"), rs.getString("Model"), rs.getString("Date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        id_Column.setCellValueFactory(new PropertyValueFactory<>("id"));
        LisancePlate_Column.setCellValueFactory(new PropertyValueFactory<>("LiscPlate"));
        Manufactor_Column.setCellValueFactory(new PropertyValueFactory<>("Manufactor"));
        Model_Column.setCellValueFactory(new PropertyValueFactory<>("Model"));
        Service_Column.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Truck_Table.setItems(Oblist);


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

