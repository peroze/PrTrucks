package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TrucksList implements Initializable {

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
    private Button Import_Button;

    private ObservableList<ModelTruck> Oblist;


    @FXML
    void Import_Button_Pressed(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Sql db=new Sql();
        ResultSet rs=db.Query_General_Trucks();
        Oblist= FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Oblist.add(new ModelTruck(rs.getString("id"),rs.getString("LiscPlate"),rs.getString("Manufactor"),rs.getString("Model"),rs.getString("Date")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        id_Column.setCellValueFactory(new PropertyValueFactory<>("id"));
        LisancePlate_Column.setCellValueFactory(new PropertyValueFactory<>("LiscPlate"));
        Manufactor_Column.setCellValueFactory(new PropertyValueFactory<>("Manufactor"));
        Model_Column.setCellValueFactory(new PropertyValueFactory<>("Model"));
        Service_Column.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Truck_Table.setItems(Oblist);


    }
}

