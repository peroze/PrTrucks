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
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class is the controller for TrucksList.fxml
 *
 * @author peroze
 * @version 1.0 Alpha
 */
public class FastList implements Initializable {


    @FXML
    private TableView<ModelFast> Truck_Table;

    @FXML
    private TableColumn<ModelFast, String> LisancePlate_Column;

    @FXML
    private TableColumn<ModelFast, String> Manufactor_Column;

    @FXML
    private TableColumn<ModelFast, String> Km_Column;

    @FXML
    private TableColumn<ModelFast, String> Location_Column;

    @FXML
    private TableColumn<ModelFast, String> ServiceDate_Column;

    @FXML
    private TableColumn<ModelFast, String> ServiceKm_Column;

    @FXML
    private TableColumn<ModelFast, String> Kteo_Column;

    @FXML
    private TableColumn<ModelFast, String> Gas_Column;

    @FXML
    private TableColumn<ModelFast, String> Speed_Column;

    @FXML
    private TableColumn<ModelFast, String> Fire_Column;

    private ObservableList<ModelFast> Oblist;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Sql db = new Sql();
        ResultSet rs = db.Query_General_Fast();
        Oblist = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Oblist.add(new ModelFast( rs.getString("LiscPlate"), rs.getString("Manufactor"), rs.getString("Kilometers"), rs.getString("Location"),rs.getString("Next_Date"),rs.getString("MAX(Service.Next_Kilometers)"),rs.getString("DateNext"),rs.getString("NextDate"), rs.getString("FireExtiguiser"),rs.getString("SpeedWriter")));
            }
        } catch (SQLException e) {
            db.Disconnect();
            e.printStackTrace();
        }
        LisancePlate_Column.setCellValueFactory(new PropertyValueFactory<>("LiscPlate"));
        Manufactor_Column.setCellValueFactory(new PropertyValueFactory<>("Manufactor"));
        Location_Column.setCellValueFactory(new PropertyValueFactory<>("Location"));
        Speed_Column.setCellValueFactory(new PropertyValueFactory<>("Speed"));
        Fire_Column.setCellValueFactory(new PropertyValueFactory<>("Fire"));
        Kteo_Column.setCellValueFactory(new PropertyValueFactory<>("KTEO"));
        Gas_Column.setCellValueFactory(new PropertyValueFactory<>("EmmisionCard"));
        Km_Column.setCellValueFactory(new PropertyValueFactory<>("Kilometers"));
        ServiceDate_Column.setCellValueFactory(new PropertyValueFactory<>("ServiceDate"));
        ServiceKm_Column.setCellValueFactory(new PropertyValueFactory<>("ServiceKm"));
        Truck_Table.setItems(Oblist);
        db.Disconnect();
    }




}

