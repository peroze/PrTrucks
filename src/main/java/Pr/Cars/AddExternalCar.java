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
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
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
    private FontIcon Minimize_Button;

    @FXML
    private FontIcon X_Button;

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
    private Label Comp_Label;

    @FXML
    private Label Phone_Label1;

    @FXML
    private TextField Height;

    @FXML
    private TextField Lenght;


    private boolean edit = false;

    private ModelExternalCars toEdit;

    private ArrayList<Label> Labels;

    private ArrayList<TextField> TFields;

    ArrayList<String> Texts;

    Dictionary dict;



    private ObservableList<StringsForTables> oblist;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       try {
           int test=0;
           toEdit = null;
           dict = new Hashtable();
           Texts = new ArrayList<>();
           dict = new Hashtable();
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
           Labels = new ArrayList<>();
           Labels.add(Lisc_Label);
           Labels.add(Manu_Label);
           Labels.add(Model_Label);
           Labels.add(Comp_Label);
           Labels.add(Driver_Label);
           Labels.add(Phone_Label1);
           Labels.add(Lenght_Label);
           Labels.add(Height_Label);
           Labels.add(Width_label);
           int Size=Labels.size();
           TFields = new ArrayList<>();
           TFields.add(Lisc_Plate);
           TFields.add(manufactor);
           TFields.add(Model);
           TFields.add(Driver);
           TFields.add(Phone);
           TFields.add(Lenght);
           TFields.add(Height);
           TFields.add(Width);
           for(int i=0;i<Labels.size();i++){
               Texts.add(Labels.get(i).getText());
           }
           for (int i = 0; i < Size; i++) {

               if (i == 3) {
                   dict.put(Company, Labels.get(i));
                   Company.setOnMouseClicked(this::setFocusTFIelds);
               } else {
                   if(i==0||i==1||i==2){
                       dict.put(TFields.get(i), Labels.get(i));
                       TFields.get(i).setOnMouseClicked(this::setFocusTFIelds);
                   }
                   else {
                       dict.put(TFields.get(i-1), Labels.get(i));
                        TFields.get(i-1).setOnMouseClicked(this::setFocusTFIelds);}
               }
           }
           ResetHideLabels();
           db.Disconnect();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       catch (Exception e){
           return;
       }
    }

    public void setFocusTFIelds(MouseEvent e){
        ResetHideLabels();
        ((Label)dict.get(e.getSource())).setStyle("-fx-text-fill:  #8B74BD");
        ((Label)dict.get(e.getSource())).setVisible(true);
    }

    public void ResetHideLabels(){
        for(int i=0;i<Labels.size();i++){
            Labels.get(i).setText(Texts.get(i));
            Labels.get(i).setStyle("-fx-text-fill:#FA8072");
            if(i==3&&Company.getValue()==null){
                Labels.get(i).setVisible(false);
            }
            if(i==0&&TFields.get(0).getText().equals("")||i==1&&TFields.get(1).getText().equals("")||i==2&&TFields.get(2).getText().equals("")){
                Labels.get(i).setVisible(false);
            }
            else if(i!=0&&i!=1) {

                if (TFields.get(i-1).getText().equals( "")) {
                    Labels.get(i).setVisible(false);
                } else{
                    Labels.get(i).setVisible(true);
                }
            }
            else{
                Labels.get(i).setVisible(true);
            }
        }
    }

    public void ResetCssTFields(){
        for(int i=0;i<TFields.size();i++){
            TFields.get(i).setStyle(null);
        }
        Company.setStyle(null);
    }



    /**
     * This class is used when the Ok button is pressed and it ads a new car in data Base
     *
     * @param event The event
     */
    @FXML
    void Ok_Button_Pr(ActionEvent event) {
            boolean flag = false;
            ResetCssTFields();
            ResetHideLabels();
            if (Lisc_Plate.getText().equals("")) {
                Lisc_Label.setStyle("-fx-text-fill: RED");
                Lisc_Label.setText("Η Πινακίδα είναι κενή");
                Lisc_Label.setVisible(true);
                flag = true;
                Lisc_Plate.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            }
            if (manufactor.getText().equals("")) {
                Manu_Label.setStyle("-fx-text-fill: RED");
                Manu_Label.setText("Ο Κατασκευαστής είναι κενός");
                Manu_Label.setVisible(true);
                flag = true;
                manufactor.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            }
            if (Model.getText().equals("")) {
                Lisc_Label.setStyle("-fx-text-fill: RED");
                Lisc_Label.setText("Το Μοντέλο είναι κενό");
                Lisc_Label.setVisible(true);
                flag = true;
                Model.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            }
            if (Driver.getText().equals("")) {
                Driver_Label.setStyle("-fx-text-fill: RED");
                Driver_Label.setText("Ο Οδηγός είναι κενός");
                Driver_Label.setVisible(true);
                flag = true;
                Driver.setStyle(" -fx-background-color: transparent;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            }
            if (Phone.getText().equals("")) {
                Phone_Label1.setStyle("-fx-text-fill: RED");
                Phone_Label1.setText("Το Τηλέφωνο του Οδηγού είναι κενή");
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
                Width_label.setStyle("-fx-text-fill: RED");
                Width_label.setText("Το πλάτος πρέπει να είναι ακέραιος");
                Width_label.setVisible(true);
                Width.setStyle(" -fx-background-color: transparent;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                flag = true;
            }
            try{
                Integer.valueOf(Height.getText());
            }
            catch (NumberFormatException e){
                Height_Label.setStyle("-fx-text-fill: RED");
                Height_Label.setText("Το Ύψος πρέπει να είναι ακέραιος");
                Height_Label.setVisible(true);
                Height.setStyle(" -fx-background-color: transparent;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                flag = true;
            }
            try{
                Integer.valueOf(Lenght.getText());
            }
            catch (NumberFormatException e){
                Lenght_Label.setStyle("-fx-text-fill: RED");
                Lenght_Label.setText("Το Μήκος πρέπει να είναι ακέραιος");
                Lenght_Label.setVisible(true);
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
        ResetHideLabels();
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
