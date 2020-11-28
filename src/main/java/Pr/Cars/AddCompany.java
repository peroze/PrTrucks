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
import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * Thid class is the controller for AddΚΤΕΟ which add a new ΚΤΕΟ in the db
 *
 * @author peroze
 * @version 1.0 Alpha
 */
public class AddCompany implements Initializable {


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
    private TextField Phone;

    @FXML
    private Button Ok_Button;

    @FXML
    private TextField Name;

    @FXML
    private Label Phone_Label;

    @FXML
    private Label Name_Label;

    private boolean edit;
    private ModelCompany toEdit;


    private int flag2;// This is used to know which field threw an excpetion


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Pane.requestFocus();
            }
        });
        edit=false;
    }


    /**
     * This button adds the new KTEO in the db
     *
     * @param event The event
     */
    @FXML
    void Ok_Button_Pr(ActionEvent event) {
            boolean flag = false;
            Phone.setStyle(null);
            Phone_Label.setVisible(false);
            Name_Label.setVisible(false);
            Name.setStyle(null);
            if (Name.getText().equals("")) {
                Name_Label.setVisible(true);
                flag = true;
                Name.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            }
            if (Phone.getText().equals("")) {
                Phone_Label.setVisible(true);
                flag = true;
                Phone.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            }
            if (flag == true) {
                return;
            }
            Sql sql = new Sql();
            int i;
            if(edit==false) {
                ModelCompany toAdd = new ModelCompany(Name.getText(), Phone.getText());
                i = sql.InstertCompany(toAdd, false);
            }
            else{
                toEdit.setPhone(Phone.getText());
                toEdit.setName(Name.getText());
                i = sql.InstertCompany(toEdit, true);
            }
            if (i == 1) {
                sql.Disconnect();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Εισαγωγή Επιτυχής");
                //alert.setHeaderText("DB Creation Complete");
                alert.setContentText("H Εταιρία εισήχθει με επιτυχία στην Βαση");
                alert.showAndWait();
                Stage stage = (Stage) Ok_Button.getScene().getWindow();
                sql.Disconnect();
                stage.close();
            } else {
                sql.Disconnect();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Εισαγωγή Απέτυχε");
                alert.setContentText("Η Εταιρία δεν κατατάφερε να ενταχθεί, δοκιμάστε ξανά");
                alert.showAndWait();
                Phone.clear();
                Name.clear();
            }
    }

    public void edit(ModelCompany a){
        toEdit=a;
        edit=true;
        Name.setText(a.getName());
        Phone.setText(a.getPhone());

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
