package sample;

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

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    private ImageView Minimize_Button;

    @FXML
    private ImageView X_Button;


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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
     * This class is used when the Ok button is pressed and it ads a new car in data Base
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
            Amount_Label.setStyle(null);
            Amount_Label.setVisible(false);
            if (Lisc_Plate.getValue() == null) {
                Lisc_Label.setVisible(true);
                flag = true;
                Lisc_Plate.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
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
            if (Amount.getText().equals("")) {
                Amount_Label.setText("Η ποσότητα είναι κενή");
                Amount_Label.setVisible(true);
                flag = true;
                Amount.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            } else {
                Integer.valueOf(Kilometers.getText());
            }
            if (flag == true) {
                return;
            }
            Sql sql = new Sql();
            ModelRefill toAdd = new ModelRefill(Lisc_Plate.getValue().toString(), Kilometers.getText(), Date.getValue().toString(), Amount.getText(), Driver.getText(), Location.getText());
            int i = sql.InstertRefill(toAdd);
            if (i == 1) {
                ResultSet rs=sql.Query_Specific_Trucks(Lisc_Plate.getValue().toString());
                int km=rs.getInt("Kilometers");
                if(km<Integer.valueOf(Kilometers.getText())){
                    ModelTruck repl=new ModelTruck(rs.getString("id"), rs.getString("LiscPlate"), rs.getString("Manufactor"), rs.getString("Model"), rs.getString("First_Date"), rs.getString("Plaisio"), rs.getString("Type"), rs.getString("Location"), Kilometers.getText(), rs.getString("Data"), rs.getString("ServiceInKm"), rs.getString("KTEOIn"), rs.getString("GasIn"));
                    sql.InsertCar(repl,5,true);
                }
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
                alert.setContentText("Η κάρτα δεν κατατάφερε να ενταχθεί, δοκιμάστε ξανά");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Km_Label.setText("Τα Χιλιόμετρα πρέπει να είναι ακέραιος");
            Km_Label.setVisible(true);
            Kilometers.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
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

    public ArrayList<String> GetNewEntry(ArrayList<String> Entry) {
        return Entry;
    }

}
