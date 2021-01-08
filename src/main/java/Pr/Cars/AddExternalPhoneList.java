package Pr.Cars;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


/**
 * Thid class is the controller for AddΚΤΕΟ which add a new ΚΤΕΟ in the db
 *
 * @author peroze
 * @version 1.0 Alpha
 */
public class AddExternalPhoneList implements Initializable {


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
    private TextField Phone;

    @FXML
    private Button Ok_Button;

    @FXML
    private TextField Name;

    @FXML
    private Label Name_Label;

    @FXML
    private Label Phone_Label;

    @FXML
    private TextField Email;

    @FXML
    private Label Email_Label1;

    @FXML
    private TextField Address;

    @FXML
    private Label Address_Label;



    @FXML
    private ComboBox<String> Posistion;

    @FXML
    private Label Posistion_Label;

    @FXML
    private TextField Note_Text;

    @FXML
    private Button Note_Button;

    @FXML
    private TableView<StringsForTables> Notes_table;

    @FXML
    private TableColumn<StringsForTables, String> Notes_Col;


    private boolean edit;

    private ModelExternalPhoneList toEdit;

    private ArrayList<Label> Labels;

    private ArrayList<TextField> TFields;

    ArrayList<String> Texts;
    boolean placeboo;

    Dictionary dict;
    ObservableList<StringsForTables> Oblist;

    private int flag2;// This is used to know which field threw an excpetion




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Sql db=new Sql();
        ObservableList<String> Posistions = FXCollections.observableArrayList();
        ResultSet rs=db.Query_General_Partners_Type();
        try {
            while (rs.next()) {
                Posistions.add(rs.getString("Type"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Pane.requestFocus();
            }
        });
        Notes_Col.setCellValueFactory(new PropertyValueFactory<>("string"));
        Notes_Col.setCellFactory(TextFieldTableCell.<StringsForTables>forTableColumn());
        Notes_Col.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<StringsForTables, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<StringsForTables, String> event) {
                        ((StringsForTables) event.getTableView().getItems().get(
                                event.getTablePosition().getRow())
                        ).setString(event.getNewValue());
                    }
                }
        );
        Oblist=FXCollections.observableArrayList();
        placeboo=true;
        Oblist.add(new StringsForTables(""));
        Notes_table.setItems(Oblist);
        Posistion.setItems(Posistions);
        TFields = new ArrayList<>();
        Labels = new ArrayList<>();
        Texts = new ArrayList<>();
        dict = new Hashtable();
        edit = false;
        Labels.add(Name_Label);
        TFields.add(Name);
        Texts.add("Όνομα");
        Labels.add(Phone_Label);
        Name.setOnMouseClicked(this::setFocusTFIelds);
        dict.put(Name, Labels.get(0));
        TFields.add(Phone);
        Texts.add("Τηλέφωνο");
        Phone.setOnMouseClicked(this::setFocusTFIelds);
        dict.put(Phone, Labels.get(1));
        Labels.add(Email_Label1);
        Texts.add("E-Mail");
        TFields.add(Email);
        Email.setOnMouseClicked(this::setFocusTFIelds);
        dict.put(Email, Labels.get(2));
        Labels.add(Address_Label);
        Texts.add("Διεύθυνση");
        TFields.add(Address);
        Address.setOnMouseClicked(this::setFocusTFIelds);
        dict.put(Address, Labels.get(3));
        Labels.add(Posistion_Label);
        Texts.add("Τύπος Συνεργάτη");
        Posistion.setOnMouseClicked(this::setFocusTFIelds);
        ResetHideLabels();
        Notes_table.setRowFactory((tv -> {
            TableRow<StringsForTables> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(placeboo==true){
                    Notes_table.getSelectionModel().clearSelection();
                }
            });
            return row;
        }));
        ContextMenu Cont=new ContextMenu();
        MenuItem Del=new MenuItem("Διαγραφή");
        Del.setOnAction(this::Del);
        Cont.getItems().add(Del);
        Notes_table.setContextMenu(Cont);
    }

    public void Del(ActionEvent e){
        StringsForTables temp = Notes_table.getSelectionModel().getSelectedItem();
        int i = 0;
        boolean check = false;
        while (i < Oblist.size()) {
            if (temp.equals(Oblist.get(i))) {
                Oblist.remove(i);
                check = true;
                break;
            }
            i++;
        }
        if(Oblist.isEmpty()){
            Oblist.add(new StringsForTables("","","","","",""));
            placeboo=true;
        }
    }



    public void setFocusTFIelds(MouseEvent e) {
        ResetHideLabels();
        if(e.getSource()==Posistion) {
            Posistion_Label.setStyle("-fx-text-fill:  #8B74BD");
            Posistion_Label.setVisible(true);
            return;
        }
        ((Label) dict.get(e.getSource())).setStyle("-fx-text-fill:  #8B74BD");
        ((Label) dict.get(e.getSource())).setVisible(true);
    }

    public void ResetHideLabels() {
        for (int i = 0; i < Labels.size() - 1; i++) {
            Labels.get(i).setText(Texts.get(i));
            Labels.get(i).setStyle("-fx-text-fill:#FA8072");
            if(TFields.get(i).getText()==null){
                Labels.get(i).setVisible(false);
            }
            else {
                if (TFields.get(i).getText().equals("")) {
                    Labels.get(i).setVisible(false);
                } else {
                    Labels.get(i).setVisible(true);
                }
            }
        }
        if(Posistion.getValue()==null) {
            Labels.get(4).setVisible(false);
        }
        Labels.get(4).setText(Texts.get(4));
        Labels.get(4).setStyle("-fx-text-fill:#FA8072");
    }

    public void ResetCssTFields() {
        for (int i = 0; i < TFields.size(); i++) {
            TFields.get(i).setStyle(null);
        }
        Email.setStyle(null);
    }

    @FXML
    void Char_Button_Pr(ActionEvent event) {
        if(placeboo==true){
            Oblist.remove(0);
            placeboo=false;
        }
        if(Note_Text.getText().equals(null)){
            return;
        }
        Oblist.add(new StringsForTables(Note_Text.getText()));
        Note_Text.clear();
    }



    /**
     * This button adds the new KTEO in the db
     *
     * @param event The event
     */
    @FXML
    void Ok_Button_Pr(ActionEvent event) {
        if(placeboo==true){
            Oblist.remove(0);
        }
        boolean flag = false;
        ResetHideLabels();
        ResetCssTFields();
        if (Name.getText().equals("")) {
            Name_Label.setText("Το Όνομα είναι κενό");
            Name_Label.setStyle("-fx-text-fill: RED");
            Name_Label.setVisible(true);
            flag = true;
            Name.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
        }
        if (Phone.getText().equals("")) {
            Phone_Label.setStyle("-fx-text-fill: RED");
            Phone_Label.setText("Το τηλέφωνο είναι κενό");
            Phone_Label.setVisible(true);
            flag = true;
            Phone.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
        }
        if (Posistion.getValue() == null) {
            Posistion_Label.setStyle("-fx-text-fill: RED");
            Posistion_Label.setText("Ο τύπος Συνεργάτη είναι κενός");
            Posistion_Label.setVisible(true);
            flag = true;
            Posistion.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
        }
        if (flag == true) {
            return;
        }
        Sql sql = new Sql();
        int i;
        String Notes;
        Notes="";
        if(!Oblist.isEmpty()) {
            for (int k = 0; k < Oblist.size(); k++) {
                Notes = Notes + "|" + Oblist.get(k).getString();
            }
        }
        if (edit == false) {
            ModelExternalPhoneList toAdd = new ModelExternalPhoneList(Name.getText(), Phone.getText(),Address.getText(), Email.getText(),Posistion.getValue().toString(),Notes);
            i = sql.InsertExternalPhone(toAdd, false);
        } else {
            toEdit.setPhone(Phone.getText());
            toEdit.setName(Name.getText());
            toEdit.setEmail(Email.getText());
            toEdit.setDiscreption(Posistion.getValue().toString());
            toEdit.setNotes(Notes);
            i = sql.InsertExternalPhone(toEdit, true);
        }
        if (i == 1) {
            sql.Disconnect();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Εισαγωγή Επιτυχής");
            //alert.setHeaderText("DB Creation Complete");
            alert.setContentText("Ο Υπάλληλος εισήχθει με επιτυχία στην Βαση");
            alert.showAndWait();
            Stage stage = (Stage) Ok_Button.getScene().getWindow();
            sql.Disconnect();
            stage.close();
        } else {
            sql.Disconnect();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Εισαγωγή Απέτυχε");
            alert.setContentText("Ο Υπάλληλος δεν κατατάφερε να ενταχθεί, δοκιμάστε ξανά");
            alert.showAndWait();
            Phone.clear();
            Name.clear();
        }
    }

    public void edit(ModelExternalPhoneList a) {
        toEdit = a;
        edit = true;
        if(placeboo==true){
            Oblist.remove(0);
        }
        Name.setText(a.getName());
        Phone.setText(a.getPhone());
        Email.setText(a.getEmail());
        Posistion.setValue(a.getDiscreption());
        Address.setText(a.getAddress());
        ResetHideLabels();
        if(a.getNotes()==null){
            return;
        }
        String[] Ch = a.getNotes().split(Pattern.quote("|"));
        for (int i = 0; i < Ch.length; i++) {
            Oblist.add(new StringsForTables(Ch[i]));
        }
        placeboo=false;
        if(Oblist.isEmpty()){
            Oblist.add(new StringsForTables(""));
            placeboo=true;
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
