package sample;

import javafx.application.Platform;
import javafx.beans.Observable;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    private Button Ok_Button;


    private int max_i;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
    }


    public int getMax_i() {
        return max_i;
    }

    public void setMax_i(int max_i) {
        this.max_i = max_i;
    }

    @FXML
    void Ok_Button_Pr(ActionEvent event) {
        Sql sql = new Sql();
        ModelTruck toAdd = new ModelTruck("-1", Lisc_Plate.getText(), manufactor.getText(), Model.getText(), Date.getValue().toString(), Plaisio.getText(), Type.getValue().toString(), Location.getValue().toString(), Kilometers.getText());
        int i=sql.InsertCar(toAdd, max_i);
        if(i==1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Εισαγωγή Επιτυχής");
            //alert.setHeaderText("DB Creation Complete");
            alert.setContentText("Το Αυτοκίνητο εισήχθει με επιτυχία στην Βαση");
            alert.showAndWait();
            Stage stage = (Stage) Ok_Button.getScene().getWindow();
            stage.close();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Εισαγωγή Απέτυχε");
            alert.setContentText("Το αυτοκίνητο δεν κατατάφερε να ενταχθεί, δοκιμάστε ξανά");
            alert.showAndWait();
        }
    }

    /**
     * This Closes the program
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
     * @param event This it the given event
     */
    @FXML
    void Top_Bar_Dragged(MouseEvent event) {
        Pane.getScene().getWindow().setX(event.getScreenX() - x_Offset);
        Pane.getScene().getWindow().setY(event.getScreenY() - y_Offset);
    }


    /**
     * This is used to make the window draggable
     * @param event This it the given event
     */
    @FXML
    void Top_Bar_Pressed(MouseEvent event) {
        x_Offset=event.getSceneX();
        y_Offset=event.getSceneY();
    }


    /**
     * This method is used to minimize the Window
     * @param event This it the given event
     */
    @FXML
    void Minimize_Button_Pressed(MouseEvent event) {
        Stage stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    public ArrayList<String> GetNewEntry(ArrayList<String> Entry){
        return Entry;
    }

}
