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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * This Class is the Corntoller for AddCar.fxml which adds a new Car in the db
 *
 * @author peroze
 * @version 1.0 Alpha
 */
public class AddCar implements Initializable {


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
    private TextField manufactor;


    @FXML
    private TextField Model;


    @FXML
    private TextField Lisc_Plate;


    @FXML
    private TextField Kilometers;


    @FXML
    private TextField Plaisio;


    @FXML
    private DatePicker Date;


    @FXML
    private ComboBox<String> Type;


    @FXML
    private ComboBox<String> Location;


    @FXML
    private TextField Char_Text;


    @FXML
    private TextField Code_Text;


    @FXML
    private TableView<StringsForTables> Table;


    @FXML
    private TableColumn<StringsForTables, String> Data_Col;


    @FXML
    private TableColumn<StringsForTables, String> Code_Col;


    @FXML
    private Button Char_Button;


    @FXML
    private Button Ok_Button;

    @FXML
    private ComboBox KTEO;

    @FXML
    private ComboBox Gas;

    @FXML
    private TextField Service;

    private boolean edit=false;

    private ModelTruck toEdit;

    private int max_i;


    private ObservableList<StringsForTables> oblist;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        toEdit=null;
        oblist = FXCollections.observableArrayList();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Pane.requestFocus();
            }
        });
        ObservableList<String> Types = FXCollections.observableArrayList();
        Types.add("ΙΧ");
        Types.add("ΦΙΧ");
        Types.add("Μηχάνιματα");
        Type.setItems(Types);
        ObservableList<String> Locations = FXCollections.observableArrayList();
        Locations.add("Θεσσαλονίκη");
        Locations.add("ΒΙΠΕ");
        Locations.add("Αθήνα");
        Locations.add("Λαμία");
        Location.setItems(Locations);
        ObservableList<String> Kteo=FXCollections.observableArrayList();
        Kteo.add("1 Έτος");
        Kteo.add("2 Έτοι");
        Kteo.add("Όχι Κτεο");
        ObservableList<String> Gasc=FXCollections.observableArrayList();
        Gasc.add("6 Μήνες");
        Gasc.add("1 Έτος");
        Gasc.add("Όχι Κάρτα");
        KTEO.setItems(Kteo);
        Gas.setItems(Gasc);
        Data_Col.setCellValueFactory(new PropertyValueFactory<>("string"));
        Code_Col.setCellValueFactory(new PropertyValueFactory<>("string2"));
        Table.setRowFactory((tv -> {
            TableRow<StringsForTables> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    DoubleClickTable();
                }
            });
            return row;
        }));
    }

    /**
     * This methods deletes an entry from the table and it is called when a row is double clicked
     */
    private void DoubleClickTable() {
        StringsForTables temp = Table.getSelectionModel().getSelectedItem();
        int i = 0;
        boolean check = false;
        while (i < oblist.size()) {
            if (temp.equals(oblist.get(i))) {
                oblist.remove(i);
                check = true;
                break;
            }
        }
    }



    public int getMax_i() {
        return max_i;
    }

    public void setMax_i(int max_i) {
        this.max_i = max_i;
    }

    /**
     * This class is used when the Ok button is pressed and it ads a new car in data Base
     *
     * @param event The event
     */
    @FXML
    void Ok_Button_Pr(ActionEvent event) {
        Sql sql = new Sql();
        String Data;
        Data = oblist.get(0).getString() + "~" + oblist.get(0).getString2();
        for (int i = 1; i < oblist.size(); i++) {
            Data = Data + "|" + oblist.get(i).getString() + "~" + oblist.get(i).getString2();
        }
        int i;
        if(edit==false) {
            ModelTruck toAdd = new ModelTruck("-1", Lisc_Plate.getText(), manufactor.getText(), Model.getText(), Date.getValue().toString(), Plaisio.getText(), Type.getValue().toString(), Location.getValue().toString(), Kilometers.getText(), Data,Service.getText(),Gas.getValue().toString(),KTEO.getValue().toString());
            i = sql.InsertCar(toAdd, max_i,edit);
        }
        else{
            sql.DeleteCar(Integer.valueOf(toEdit.getId()));
            toEdit.setLiscPlate(Lisc_Plate.getText());
            toEdit.setManufactor(manufactor.getText());
            toEdit.setModel(toEdit.getModel());
            toEdit.setDate(Date.getValue().toString());
            toEdit.setPlaisio(Plaisio.getText());
            toEdit.setType(Type.getValue().toString());
            toEdit.setLocation(Location.getValue().toString());
            toEdit.setKilometers(Kilometers.getText());
            toEdit.setData(Data);
            toEdit.setKteoIn(KTEO.getValue().toString());
            toEdit.setGasIn(Gas.getValue().toString());
            toEdit.setServiceInkm(Service.getText());
            i=sql.InsertCar(toEdit,max_i,edit);

        }
        if (i == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Εισαγωγή Επιτυχής");
            //alert.setHeaderText("DB Creation Complete");
            alert.setContentText("Το Αυτοκίνητο εισήχθει με επιτυχία στην Βαση");
            alert.showAndWait();
            Stage stage = (Stage) Ok_Button.getScene().getWindow();
            sql.Disconnect();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Εισαγωγή Απέτυχε");
            alert.setContentText("Το αυτοκίνητο δεν κατατάφερε να ενταχθεί, δοκιμάστε ξανά");
            alert.showAndWait();
        }
    }

    @FXML
    void Char_Button_Pr(ActionEvent event) {
        String temp1 = Char_Text.getText();
        String temp2 = Code_Text.getText();
        oblist.add(new StringsForTables(temp1, temp2));
        Char_Text.setText("");
        Code_Text.setText("");
        Table.setItems(oblist);

    }

    public void edit(ModelTruck s){
        edit=true;
        Model.setText(s.getModel());
        manufactor.setText(s.getManufactor());
        Plaisio.setText(s.getPlaisio());
        LocalDate dat;
        dat = LocalDate.parse(s.getDate());
        Date.setValue(dat);
        Lisc_Plate.setText(s.getLiscPlate());
        Kilometers.setText(s.getKilometers());
        Location.setValue(s.getLocation());
        Type.setValue(s.getType());
        Service.setText(s.getServiceInkm());
        KTEO.setValue(s.getKteoIn());
        Gas.setValue(s.getGasIn());
        if(s.getData()!=null) {
            String[] Ch = s.getData().split(Pattern.quote("|"));
            String[][] Dat = new String[Ch.length][2];
            for (int i = 0; i < Ch.length; i++) {
                Dat[i] = Ch[i].split(Pattern.quote("~"));
            }
            for (int i = 0; i < Ch.length; i++) {
                oblist.add(new StringsForTables(Dat[i][0], Dat[i][1]));
            }
            Table.setItems(oblist);
        }
        toEdit=s;
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

    public ArrayList<String> GetNewEntry(ArrayList<String> Entry) {
        return Entry;
    }

}
