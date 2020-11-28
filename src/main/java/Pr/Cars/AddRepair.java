package Pr.Cars;

import javafx.application.Platform;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


/**
 * Thid class is the controller for AddRepair which add a new Repair in the db
 *
 * @author peroze
 * @version 1.0 Alpha
 */
public class AddRepair implements Initializable {


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

    private boolean edit = false;

    private ModelRepair toEdit;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toEdit=null;
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
        Lisc_Plate.setItems(Cars);
        Parts.setCellValueFactory(new PropertyValueFactory<>("string"));
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
     * This button adds the new repair in the db
     *
     * @param event The event
     */
    @FXML
    void Ok_Button_Pr(ActionEvent event) {
        Sql sql = new Sql();
        String Changes;
        Changes = Oblist.get(0).getString();
        for (int i = 1; i < Oblist.size(); i++) {
            Changes = Changes + "|" + Oblist.get(i).getString();
        }
        int i;
        if(edit==false) {
            ModelRepair toAdd = new ModelRepair(Lisc_Plate.getValue().toString(), Price.getText(), Kilometers.getText(), Date.getValue().toString(), Discreption.getText(), Workshop.getText(), Changes);
            i = sql.InsertRepair(toAdd,false);
        }
        else{
            toEdit.setLiscPlate(Lisc_Plate.getValue().toString());
            toEdit.setDate(Date.getValue().toString());
            toEdit.setPrice(Price.getText());
            toEdit.setWorkshop(Workshop.getText());
            toEdit.setKilometers(Kilometers.getText());
            toEdit.setDiscreption(Discreption.getText());
            toEdit.setChanges(Changes);
            i=sql.InsertRepair(toEdit,true);
        }
        if (i == 1) {

            try {
                ResultSet rs=sql.Query_Specific_Trucks(Lisc_Plate.getValue().toString());
                int km = rs.getInt("Kilometers");
                if(km<Integer.valueOf(Kilometers.getText())){
                    ModelTruck repl=new ModelTruck(rs.getString("id"), rs.getString("LiscPlate"), rs.getString("Manufactor"), rs.getString("Model"), rs.getString("First_Date"), rs.getString("Plaisio"), rs.getString("Type"), rs.getString("Location"), Kilometers.getText(), rs.getString("Data"), rs.getString("ServiceInKm"), rs.getString("KTEOIn"), rs.getString("GasIn"));
                    sql.InsertCar(repl,5,true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Εισαγωγή Επιτυχής");
            //alert.setHeaderText("DB Creation Complete");
            alert.setContentText("Η επισκευή εισήχθει με επιτυχία στην Βαση");
            alert.showAndWait();
            Stage stage = (Stage) Ok_Button.getScene().getWindow();
            sql.Disconnect();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Εισαγωγή Απέτυχε");
            alert.setContentText("Η επισκευή δεν κατατάφερε να ενταχθεί, δοκιμάστε ξανά");
            alert.showAndWait();
            Kilometers.clear();
            Lisc_Plate.setValue(null);
            Discreption.clear();
            AddPart.clear();
            Price.clear();
            Oblist = FXCollections.observableArrayList();
            Table.setItems(Oblist);
        }
    }

    public void edit(ModelRepair s) {
        edit = true;
        Workshop.setText(s.getWorkshop());
        Price.setText(s.getPrice());
        Discreption.setText(s.getDiscreption());
        LocalDate dat;
        dat = LocalDate.parse(s.getDate());
        Date.setValue(dat);
        Lisc_Plate.setValue(s.getLiscPlate());
        Kilometers.setText(s.getKilometers());
        if (s.getChanges() != null) {
            String[] Ch = s.getChanges().split(Pattern.quote("|"));
            for (int i = 0; i < Ch.length; i++) {
                Oblist.add(new StringsForTables(Ch[i]));
            }
            Table.setItems(Oblist);
        }
        toEdit = s;
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


    @FXML
    void All_Hover(MouseEvent event) {

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
     * Τhis method is used to return the new Entry
     *
     * @param Entry The new entry
     * @return The new Entry
     */
    public ArrayList<String> GetNewEntry(ArrayList<String> Entry) {
        return Entry;
    }

}
