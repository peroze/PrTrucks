package sample;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;


/**
 * Thid class is the controller for AddService which add a new Service in the db
 *
 * @author peroze
 * @version 1.0 Alpha
 */
public class AddService implements Initializable {


    private double x_Offset = 0;
    private double y_Offset = 0;

    @FXML
    private AnchorPane Pane;

    @FXML
    private HBox Top_Bar;

    @FXML
    private ImageView Minimize_Button;

    @FXML
    private ImageView X_Button;

    @FXML
    private TextField Discreption;

    @FXML
    private TextField Price;

    @FXML
    private ComboBox Lisc_Plate;

    @FXML
    private TextField Kilometers;

    @FXML
    private DatePicker Date;

    @FXML
    private Button Ok_Button;

    @FXML
    private TextField AddPart;

    @FXML
    private Button AddPart_Btn;

    @FXML
    private TextField Workshop;

    @FXML
    private TableView<StringsForTables> Table;

    @FXML
    private TableColumn<StringsForTables, String> Parts;

    private ObservableList<StringsForTables> Oblist;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Oblist = FXCollections.observableArrayList();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Pane.requestFocus();
            }
        });
        ObservableList<String> Cars = FXCollections.observableArrayList();
        Sql sql = new Sql();
        ResultSet rs = sql.Query_All_Lisc();
        try {
            while (rs.next()) {
                Cars.add(rs.getString("LiscPlate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Parts.setCellValueFactory(new PropertyValueFactory<>("string"));
        Lisc_Plate.setItems(Cars);
        Table.setRowFactory((tv -> {
            TableRow<StringsForTables> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    DoubleClickTable();
                }
            });
            return row;
        }));
        sql.Disconnect();

    }

    /**
     * This methods deletes an entry from the table and it is called when a row is double clicked
     */
    private void DoubleClickTable() {
        StringsForTables temp = Table.getSelectionModel().getSelectedItem();
        int i = 0;
        boolean check = false;
        while (i < Oblist.size()) {
            if (temp.equals(Oblist.get(i))) {
                Oblist.remove(i);
                check = true;
                break;
            }
        }
    }

    /**
     * This method is called when the AddPart button is pressed and it adds the part which have been replaced in the car int the changes list
     *
     * @param event The event
     */
    @FXML
    void AddPart_Btn_Pr(ActionEvent event) {
        Oblist.add(new StringsForTables(AddPart.getText()));
        Parts.setCellValueFactory(new PropertyValueFactory<>("string"));
        AddPart.clear();
        Table.setItems(Oblist);
    }

    /**
     * This button adds the new Service in the db
     *
     * @param event The event
     */
    @FXML
    void Ok_Button_Pr(ActionEvent event) {
        try {
            Sql sql = new Sql();
            String Changes;
            Changes = Oblist.get(0).getString();
            for (int i = 1; i < Oblist.size(); i++) {
                Changes = Changes + "|" + Oblist.get(i).getString();
            }
            ResultSet rs = sql.Query_Specific_NextServiceKm(Lisc_Plate.getValue().toString());
            int Nextkm = rs.getInt("ServiceInKm");
            ModelService toAdd = new ModelService(Lisc_Plate.getValue().toString(), Date.getValue().toString(), Kilometers.getText(), Discreption.getText(), Changes, Workshop.getText(), Date.getValue().plusYears(1).toString(), String.valueOf(Integer.valueOf(Kilometers.getText()) + Nextkm), Price.getText());
            int i = sql.InsertService(toAdd);
            if (i == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Εισαγωγή Επιτυχής");
                //alert.setHeaderText("DB Creation Complete");
                alert.setContentText("Το Service εισήχθει με επιτυχία στην Βαση");
                alert.showAndWait();
                Stage stage = (Stage) Ok_Button.getScene().getWindow();
                sql.Disconnect();
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Εισαγωγή Απέτυχε");
                alert.setContentText("Το Service δεν κατατάφερε να ενταχθεί, δοκιμάστε ξανά");
                alert.showAndWait();
                Kilometers.clear();
                Lisc_Plate.setValue(new Object());
                Discreption.clear();
                AddPart.clear();
                Price.clear();
                Oblist = FXCollections.observableArrayList();
                Table.setItems(Oblist);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * This Closes the program
     *
     * @param event The event when an action id made
     */
    @FXML
    void X_Button_Pressed(MouseEvent event) {
        Stage stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        stage.close();
    }


    /**
     * This is used to make the window draggable
     *
     * @param event This it the given event
     */
    @FXML
    void Top_Bar_Dragged(MouseEvent event) {
        Pane.getScene().getWindow().setX(event.getScreenX() - x_Offset);
        Pane.getScene().getWindow().setY(event.getScreenY() - y_Offset);
    }


    /**
     * This is used to make the window draggable
     *
     * @param event This it the given event
     */
    @FXML
    void Top_Bar_Pressed(MouseEvent event) {
        x_Offset = event.getSceneX();
        y_Offset = event.getSceneY();
    }


    /**
     * This method is used to minimize the Window
     *
     * @param event This it the given event
     */
    @FXML
    void Minimize_Button_Pressed(MouseEvent event) {
        Stage stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     * This method returns the new Entry
     *
     * @param Entry The new Entry
     * @return The new Entry
     */
    public ArrayList<String> GetNewEntry(ArrayList<String> Entry) {
        return Entry;
    }

}
