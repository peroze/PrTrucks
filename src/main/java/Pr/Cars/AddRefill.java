package Pr.Cars;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * This Class is the Corntoller for AddRefill.fxml which adds a new Refill in the db
 *
 * @author peroze
 * @version 1.0 Alpha
 */
public class AddRefill implements Initializable {


    private double x_Offset = 0;
    private double y_Offset = 0;

    @FXML
    private AnchorPane Pane;

    @FXML
    private HBox Top_Bar;

    @FXML
    private FontIcon Minimize_Button;

    @FXML
    private FontIcon X_Button;


    @FXML
    private ComboBox Lisc_Plate;

    @FXML
    private TextField Kilometers;

    @FXML
    private TextField Amount;

    @FXML
    private DatePicker Date;

    @FXML
    private TextField Driver;

    @FXML
    private TextField Location;

    @FXML
    private Button Ok_Button;

    @FXML
    private Label Amount_Label;

    @FXML
    private Label Km_Label;

    @FXML
    private Label Lisc_Label;

    @FXML
    private Label Date_Label;

    @FXML
    private Label Route_Label;

    @FXML
    private Label Driver_Label;

    private ArrayList<Label> Labels;

    private ArrayList<TextField> TFields;

    ArrayList<String> Texts;

    Dictionary dict;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Texts = new ArrayList<>();
        TFields = new ArrayList<>();
        Labels = new ArrayList<>();
        dict = new Hashtable();
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
        Labels.add(Lisc_Label);
        dict.put(Lisc_Plate, Lisc_Label);
        Labels.add(Date_Label);
        dict.put(Date, Date_Label);
        Labels.add(Km_Label);
        TFields.add(Kilometers);
        dict.put(Kilometers, Km_Label);
        Labels.add(Amount_Label);
        TFields.add(Amount);
        dict.put(Amount, Amount_Label);
        Labels.add(Driver_Label);
        TFields.add(Driver);
        dict.put(Driver, Driver_Label);
        Labels.add(Route_Label);
        TFields.add(Location);
        dict.put(Location, Route_Label);
        for (int i = 0; i < Labels.size(); i++) {
            Texts.add(Labels.get(i).getText());
        }
        for (int i = 0; i < TFields.size(); i++) {
            TFields.get(i).setOnMouseClicked(this::setFocusTFIelds);
        }
        Lisc_Plate.setOnMouseClicked(this::setFocusTFIelds);
        Date.setOnMouseClicked(this::setFocusTFIelds);

    }

    public void setFocusTFIelds(MouseEvent e) {
        ResetHideLabels();
        ((Label) dict.get(e.getSource())).setStyle("-fx-text-fill:  #8B74BD");
        ((Label) dict.get(e.getSource())).setVisible(true);
    }

    public void ResetHideLabels() {
        for (int i = 0; i < Labels.size(); i++) {
            Labels.get(i).setText(Texts.get(i));
            Labels.get(i).setStyle("-fx-text-fill:#FA8072");
            if (i == 0 && Lisc_Plate.getValue() == null) {
                Labels.get(i).setVisible(false);
            } else if (i == 1 && Date.getValue() == null) {
                Labels.get(i).setVisible(false);
            } else if (i != 0 && i != 1) {
                if(TFields.get(i-2).getText()==null){
                    Labels.get(i).setVisible(false);
                }
                else if (TFields.get(i - 2).getText().equals("")) {
                    Labels.get(i).setVisible(false);
                } else {
                    Labels.get(i).setVisible(true);
                }
            } else {
                Labels.get(i).setVisible(true);
            }

        }
    }

    public void ResetCssTFields() {
        for (int i = 0; i < TFields.size(); i++) {
            TFields.get(i).setStyle(null);
        }
        Date.setStyle(null);
        Lisc_Plate.setStyle(null);
    }


    /**
     * This class is used when the Ok button is pressed and it ads a new car in data Base
     *
     * @param event The event
     */
    @FXML
    void Ok_Button_Pr(ActionEvent event) {
        try {
            int flag2 = 1;// This is used to know which field threw an excpetion
            boolean flag = false;
            ResetCssTFields();
            ResetHideLabels();
            if (Lisc_Plate.getValue() == null) {
                Lisc_Label.setText("H Πινακίδα είναι κενή");
                Lisc_Label.setStyle("-fx-text-fill: RED");
                Lisc_Label.setVisible(true);
                flag = true;
                Lisc_Plate.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            }
            if (Date.getValue() == null) {
                Date_Label.setText("H Ημερομηνία είναι κενή");
                Date_Label.setStyle("-fx-text-fill: RED");
                Date_Label.setVisible(true); //Change Style
                flag = true;

            }
            if (Kilometers.getText().equals("")) {
                Km_Label.setStyle("-fx-text-fill: RED");
                Km_Label.setText("Τα Χιλιόμετρα είναι κενά");
                Km_Label.setVisible(true);
                flag = true;
                Kilometers.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            } else {
                try {
                    Integer.valueOf(Kilometers.getText());
                } catch (NumberFormatException e) {
                    Km_Label.setText("Τα Χιλιόμετρα πρέπει να είναι ακέραιος");
                    Km_Label.setVisible(true);
                    Km_Label.setStyle("-fx-text-fill: RED");
                    Kilometers.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                }
            }
            if (Amount.getText().equals("")) {
                Amount_Label.setStyle("-fx-text-fill: RED");
                Amount_Label.setText("Η ποσότητα είναι κενή");
                Amount_Label.setVisible(true);
                flag = true;
                Amount.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            } else {
                try {
                    Integer.valueOf(Amount.getText());
                } catch (NumberFormatException e) {
                    Amount_Label.setText("Τα Χιλιόμετρα πρέπει να είναι ακέραιος");
                    Amount_Label.setVisible(true);
                    Amount_Label.setStyle("-fx-text-fill: RED");
                    Amount.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                }
            }
            if (flag == true) {
                return;
            }
            Sql sql = new Sql();
            ResultSet rs2 = sql.Query_Specific_LastRefill(Lisc_Plate.getValue().toString());
            ModelRefill toAdd;
            if (rs2.next()) {
                int km_old = rs2.getInt("Kilometers");
                int km_new = Integer.valueOf(Kilometers.getText());
                if (km_old > km_new) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Επιβαιβέωση");
                    alert.setHeaderText("Προηδοποιηση Χιλιομέτρων");
                    alert.setContentText("Τα χιλιόμετρα είναι λιγοτέρα από αυτά του προηγούμενου ανεφοδιασμού. Είσαι σίγουρος ότι θέλεις να προχωρήσεις?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (!(result.get() == ButtonType.OK)) {
                        Km_Label.setText("Τα χιλίομετρα είναι λιγότερα από τα προήγουμενα");
                        Km_Label.setVisible(true);
                        sql.Disconnect();
                        Kilometers.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                        return;
                    }
                }
                Double cons = Double.valueOf(Amount.getText()) / Double.valueOf(km_new - km_old) * 100.0;
                FileManagment Fs = new FileManagment();
                Fs.setFile("FuelPrice.txt", "Price");
                Double pr = Fs.ReadFuelPrice();
                if (pr != -5) {
                    double cost = Double.valueOf(Amount.getText()) * pr;
                    toAdd = new ModelRefill(Lisc_Plate.getValue().toString(), Kilometers.getText(), Date.getValue().toString(), Amount.getText(), Driver.getText(), Location.getText(), String.valueOf(cons), String.valueOf(cost));
                } else {
                    toAdd = new ModelRefill(Lisc_Plate.getValue().toString(), Kilometers.getText(), Date.getValue().toString(), Amount.getText(), Driver.getText(), Location.getText());
                }
            } else {
                toAdd = new ModelRefill(Lisc_Plate.getValue().toString(), Kilometers.getText(), Date.getValue().toString(), Amount.getText(), Driver.getText(), Location.getText());
            }
            int i = sql.InstertRefill(toAdd);
            if (i == 1) {
                ResultSet rs = sql.Query_Specific_Trucks(Lisc_Plate.getValue().toString());
                int km = rs.getInt("Kilometers");
                if (km < Integer.valueOf(Kilometers.getText())) {
                    ModelTruck repl = new ModelTruck(rs.getString("id"), rs.getString("LiscPlate"), rs.getString("Manufactor"), rs.getString("Model"), rs.getString("First_Date"), rs.getString("Plaisio"), rs.getString("Type"), rs.getString("Location"), Kilometers.getText(), rs.getString("Data"), rs.getString("ServiceInKm"), rs.getString("KTEOIn"), rs.getString("GasIn"));
                    sql.InsertCar(repl, 5, true);
                }
                sql.Disconnect();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Εισαγωγή Επιτυχής");
                //alert.setHeaderText("DB Creation Complete");
                alert.setContentText("Ο ανεφοδιασμός εισήχθει με επιτυχία στην Βαση");
                alert.showAndWait();
                Stage stage = (Stage) Ok_Button.getScene().getWindow();
                sql.Disconnect();
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Εισαγωγή Απέτυχε");
                alert.setContentText("Ο ανευφοδίασμος δεν ενταχθεί, δοκιμάστε ξανά");
                alert.showAndWait();
            }
    } catch(
    SQLException e)

    {
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
        Stage stage = (Stage) ((FontIcon) event.getSource()).getScene().getWindow();
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
        Stage stage = (Stage) ((FontIcon) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public ArrayList<String> GetNewEntry(ArrayList<String> Entry) {
        return Entry;
    }

}
