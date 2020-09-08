package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ServiceList implements Initializable {
    @FXML
    private Button Import_Button;

    @FXML
    private TableView<ModelService> Truck_Table;

    @FXML
    private TableColumn<ModelService,String> LisancePlate_Column;

    @FXML
    private TableColumn<ModelService,String> ServiceType_Column;

    @FXML
    private TableColumn<ModelService,String> Date_Column;

    @FXML
    private TableColumn<ModelService,String> Kilometers_Column;

    private ObservableList<ModelService> Oblist;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Sql db=new Sql();
        ResultSet rs=db.Query_General_Service();
        Oblist= FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Oblist.add(new ModelService(rs.getString("LiscPlate"),rs.getString("Kilometers"),rs.getString("Date"),rs.getString("Type")));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        LisancePlate_Column.setCellValueFactory(new PropertyValueFactory<>("LiscPlate"));
        Kilometers_Column.setCellValueFactory(new PropertyValueFactory<>("Kilometers"));
        Date_Column.setCellValueFactory(new PropertyValueFactory<>("Date"));
        ServiceType_Column.setCellValueFactory(new PropertyValueFactory<>("Type"));
        Truck_Table.setItems(Oblist);


    }

    @FXML
    void Import_Button_Pressed(ActionEvent event) {

    }

}
