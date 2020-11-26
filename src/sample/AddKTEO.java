package sample;

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
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Thid class is the controller for AddΚΤΕΟ which add a new ΚΤΕΟ in the db
 *
 * @author peroze
 * @version 1.0 Alpha
 */
public class AddKTEO implements Initializable {


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
    private TextField AddWarn;

    @FXML
    private Button AddWarn_Btn;

    @FXML
    private TextField Workshop;

    @FXML
    private TableView<StringsForTables> Table;

    @FXML
    private TableColumn<StringsForTables, String> Parts;

    @FXML
    private Label Km_Label;

    @FXML
    private Label Lisc_Label;

    @FXML
    private Label Model_Label;

    @FXML
    private Label Date_Label;

    @FXML
    private Label Locat_Label;

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
        Lisc_Plate.setItems(Cars);
        sql.Disconnect();

    }

    /**
     * This method is called when the AddWarn button is pressed and it adds a warning which have been given in KTEO
     *
     * @param event The event
     */
    @FXML
    void AddWarn_Btn_Pr(ActionEvent event) {
        Oblist.add(new StringsForTables(AddWarn.getText()));
        Parts.setCellValueFactory(new PropertyValueFactory<>("string"));
        AddWarn.clear();
        Table.setItems(Oblist);
    }

    /**
     * This button adds the new KTEO in the db
     *
     * @param event The event
     */
    @FXML
    void Ok_Button_Pr(ActionEvent event) {
        try {
            int flag2 = 1;// This is used to know which field threw an excpetion
            boolean flag = false;
            Lisc_Plate.setStyle(null);
            Lisc_Label.setVisible(false);
            Kilometers.setStyle(null);
            Km_Label.setVisible(false);
            Date.setStyle(null);
            Date_Label.setVisible(false);
            Model_Label.setStyle(null);
            Locat_Label.setVisible(false);
            if (Lisc_Plate.getValue() == null) {
                Lisc_Label.setVisible(true);
                flag = true;
                Lisc_Plate.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            }
            if (Workshop.getText().equals("")) {
                Locat_Label.setVisible(true);
                flag = true;
                Workshop.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            }
            if (Date.getValue() == null) {
                Date_Label.setVisible(true); //Change Style
                flag = true;

            }
            if (Kilometers.getText().equals("")) {
                Km_Label.setText("Τα Χιλιόμετρα είναι κενά");
                Km_Label.setVisible(true);
                flag = true;
                Kilometers.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            } else {
                Integer.valueOf(Kilometers.getText());
            }
            if (Price.getText().equals("")) {
                Model_Label.setText("Η τιμή είναι κενή");
                Model_Label.setVisible(true);
                flag = true;
                Price.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            } else {
                Integer.valueOf(Kilometers.getText());
            }
            if (flag == true) {
                return;
            }
            Sql sql = new Sql();
            String Warnings = null;
            if (!Oblist.isEmpty()) {
                Warnings = Oblist.get(0).getString();
                for (int i = 1; i < Oblist.size(); i++) {
                    Warnings = Warnings + "|" + Oblist.get(i).getString();
                }
            }
            ResultSet rs = sql.Query_Specific_NextKteo(Lisc_Plate.getValue().toString());
            String nextKteo = rs.getString("KTEOIn");
            int nextK = 2;
            switch (nextKteo) {
                case "1 Έτος":
                    nextK = 1;
                    break;
                case "2 Έτοι":
                    nextK = 2;
                    break;
                case "Οχι ΚΤΕΟ":
                    return;
            }
            rs = sql.Query_Specific_NextGas(Lisc_Plate.getValue().toString());
            String nextGas = rs.getString("GasIn");
            int nextG = 1;

            switch (nextGas) {
                case "6 Μήνες":
                    nextG = 6;
                    break;
                case "1 Έτος":
                    nextG = 1;
                    break;
                case "Οχι Κάρτα":
                    return;
            }
            ModelKTEO toAdd = new ModelKTEO(Lisc_Plate.getValue().toString(), Price.getText(), Kilometers.getText(), Date.getValue().toString(), Warnings, Date.getValue().plusYears(nextK).toString(), Workshop.getText());
            int i;
            if (nextG == 6) {
                i = sql.InsertKTEO(toAdd, Date.getValue().plusMonths(6).toString());
            } else {
                i = sql.InsertKTEO(toAdd, Date.getValue().plusYears(1).toString());
            }
            if (i == 1) {
                rs = sql.Query_Specific_Trucks(Lisc_Plate.getValue().toString());
                int km = rs.getInt("Kilometers");
                if (km < Integer.valueOf(Kilometers.getText())) {
                    System.out.println("Mpike");
                    ModelTruck repl = new ModelTruck(rs.getString("id"), rs.getString("LiscPlate"), rs.getString("Manufactor"), rs.getString("Model"), rs.getString("First_Date"), rs.getString("Plaisio"), rs.getString("Type"), rs.getString("Location"), Kilometers.getText(), rs.getString("Data"), rs.getString("ServiceInKm"), rs.getString("KTEOIn"), rs.getString("GasIn"));
                    sql.InsertCar(repl, 5, true);
                }
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
                alert.setContentText("Το KTEO δεν κατατάφερε να ενταχθεί, δοκιμάστε ξανά");
                alert.showAndWait();
                Kilometers.clear();
                Lisc_Plate.setValue(new Object());
                AddWarn.clear();
                Price.clear();
                Workshop.clear();
                Date.setValue(null);
                Oblist = FXCollections.observableArrayList();
                Table.setItems(Oblist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            Km_Label.setText("Τα Χιλιόμετρα πρέπει να είναι ακέραιος");
            Km_Label.setVisible(true);
            Kilometers.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            // Add for Price Too
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
