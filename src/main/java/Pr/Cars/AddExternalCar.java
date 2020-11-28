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
 * This Class is the Corntoller for AddCar.fxml which adds a new Car in the db
 *
 * @author peroze
 * @version 1.0 Alpha
 */
public class AddExternalCar implements Initializable {


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
    private Button Ok_Button;

    @FXML
    private ComboBox Company;

    @FXML
    private TextField Phone;

    @FXML
    private TextField Width;

    @FXML
    private TextField Driver;

    @FXML
    private Label Manu_Label;

    @FXML
    private Label Width_label;

    @FXML
    private Label Height_Label;

    @FXML
    private Label Lenght_Label;

    @FXML
    private Label Lisc_Label;

    @FXML
    private Label Model_Label;


    @FXML
    private Label Driver_Label;

    @FXML
    private Label Phone_Label1;

    @FXML
    private TextField Height;

    @FXML
    private TextField Lenght;


    private boolean edit = false;

    private ModelExternalCars toEdit;




    private ObservableList<StringsForTables> oblist;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       try {
           toEdit = null;
           Sql db = new Sql();
           Platform.runLater(new Runnable() {
               @Override
               public void run() {
                   Pane.requestFocus();
               }
           });
           ObservableList<String> Types = FXCollections.observableArrayList();
           ResultSet rs = db.Query_All_Companies();
           while (rs.next()) {
               Types.add(rs.getString("Name"));
           }
           Company.setItems(Types);
       } catch (SQLException e) {
           e.printStackTrace();
       }
    }



    /**
     * This class is used when the Ok button is pressed and it ads a new car in data Base
     *
     * @param event The event
     */
    @FXML
    void Ok_Button_Pr(ActionEvent event) {
            boolean flag = false;
            Driver.setStyle(null);
            Lisc_Plate.setStyle(null);
            manufactor.setStyle(null);
            Model.setStyle(null);
            Phone.setStyle(null);
            Width.setStyle(null);
            Lenght.setStyle(null);
            Height.setStyle(null);
            Manu_Label.setVisible(false);
            Lisc_Label.setVisible(false);
            Model_Label.setVisible(false);
            Driver_Label.setVisible(false);
            Phone_Label1.setVisible(false);
            Height_Label.setVisible(false);
            Width_label.setVisible(false);
            Lenght_Label.setVisible(false);
            if (Lisc_Plate.getText().equals("")) {
                Lisc_Label.setVisible(true);
                flag = true;
                Lisc_Plate.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            }
            if (manufactor.getText().equals("")) {
                Manu_Label.setVisible(true);
                flag = true;
                manufactor.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            }
            if (Model.getText().equals("")) {
                Model_Label.setVisible(true);
                flag = true;
                Model.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            }
            if (Driver.getText().equals("")) {
                Driver_Label.setVisible(true);
                flag = true;
                Driver.setStyle(" -fx-background-color: transparent;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            }
            if (Phone.getText().equals("")) {
                Phone_Label1.setVisible(true);
                flag = true;
                Phone.setStyle(" -fx-background-color: transparent;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            }
            if (flag == true) {
                return;
            }
            try{
                Integer.valueOf(Width.getText());

            }
            catch (NumberFormatException e){
                Width_label.setVisible(true);
                Width.setStyle(" -fx-background-color: transparent;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                flag = true;
            }
            try{
                Integer.valueOf(Height.getText());
            }
            catch (NumberFormatException e){
                Width_label.setVisible(true);
                Height.setStyle(" -fx-background-color: transparent;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                flag = true;
            }
            try{
                Integer.valueOf(Lenght.getText());
            }
            catch (NumberFormatException e){
                Width_label.setVisible(true);
                Lenght.setStyle(" -fx-background-color: transparent;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                flag = true;
            }
            Sql sql = new Sql();
            int i;
            if (edit == false) {
                ModelExternalCars toAdd = new ModelExternalCars( Lisc_Plate.getText(), manufactor.getText(), Model.getText(), Company.getValue().toString(), Width.getText(),Lenght.getText(),Height.getText(), Driver.getText(), Phone.getText() );
                i = sql.InsertExternalCars(toAdd, edit);
            } else {
                toEdit.setLiscPlate(Lisc_Plate.getText());
                toEdit.setManufactor(manufactor.getText());
                toEdit.setModel(toEdit.getModel());
                toEdit.setCompany(Company.getValue().toString());
                toEdit.setDriver(Driver.getText());
                toEdit.setPhone(Phone.getText());
                toEdit.setWidth(Width.getText());
                toEdit.setHeight(Height.getText());
                toEdit.setLenght(Lenght.getText());
                i = sql.InsertExternalCars(toEdit, edit);
                sql.Disconnect();
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


    public void edit(ModelExternalCars s) {
        edit = true;
        Model.setText(s.getModel());
        manufactor.setText(s.getManufactor());
        Driver.setText(s.getDriver());
        Company.setValue(s.getCompany());
        Lisc_Plate.setText(s.getLiscPlate());
        Phone.setText(s.getPhone());
        Width.setText(s.getWidth());
        Height.setText(s.getHeight());
        Lenght.setText(s.getLenght());
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
