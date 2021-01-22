package Pr.Cars;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

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
public class AddCompany implements Initializable {


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
    private Label Phone_Label;

    @FXML
    private Label Name_Label;

    @FXML
    private TableView<StringsForTables> Table;


    @FXML
    private TableColumn<StringsForTables, String> Data_Col;


    @FXML
    private TableColumn<StringsForTables, String> Code_Col;

    @FXML
    private TextField Char_Text;


    @FXML
    private TextField Code_Text;


    private ObservableList<StringsForTables> oblist;
    private boolean edit;
    private ModelCompany toEdit;

    private ArrayList<Label> Labels;

    private ArrayList<TextField> TFields;

    ArrayList<String> Texts;

    Dictionary dict;

    Boolean placeboo;


    private int flag2;// This is used to know which field threw an excpetion


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        placeboo=true;
        oblist=FXCollections.observableArrayList();
        TFields=new ArrayList<>();
        Labels=new ArrayList<>();
        Texts=new ArrayList<>();
        dict=new Hashtable();
        Table.setEditable(true);
        Data_Col.setCellValueFactory(new PropertyValueFactory<>("string"));
        Code_Col.setCellValueFactory(new PropertyValueFactory<>("string2"));
        Data_Col.setCellFactory(TextFieldTableCell.<StringsForTables>forTableColumn());
        Data_Col.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<StringsForTables, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<StringsForTables, String> event) {
                        ((StringsForTables) event.getTableView().getItems().get(
                                event.getTablePosition().getRow())
                        ).setString(event.getNewValue());
                    }
                }
        );
        Code_Col.setCellFactory(TextFieldTableCell.<StringsForTables>forTableColumn());
        Code_Col.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<StringsForTables, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<StringsForTables, String> event) {
                        ((StringsForTables) event.getTableView().getItems().get(
                                event.getTablePosition().getRow())
                        ).setString2(event.getNewValue());
                    }
                }
        );
        Table.setRowFactory((tv -> {
            TableRow<StringsForTables> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(placeboo==true){
                    Table.getSelectionModel().clearSelection();
                }
            });
            return row;
        }));
        oblist.add(new StringsForTables("",""));
        Table.setItems(oblist);
        ContextMenu Cont=new ContextMenu();
        MenuItem Del=new MenuItem("Διαγραφή");
        Del.setOnAction(this::Del);
        Cont.getItems().add(Del);
        Table.setContextMenu(Cont);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Pane.requestFocus();
            }
        });
        edit=false;
        Labels.add(Name_Label);
        TFields.add(Name);
        Texts.add("Όνομα");
        Labels.add(Phone_Label);
        Name.setOnMouseClicked(this::setFocusTFIelds);
        dict.put(Name,Labels.get(0));
        TFields.add(Phone);
        Texts.add("Τηλέφωνο");
        Phone.setOnMouseClicked(this::setFocusTFIelds);
        dict.put(Phone,Labels.get(1));
        ResetHideLabels();
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
            if(TFields.get(i).getText()==null){
                Labels.get(i).setVisible(false);
            }
            else if (TFields.get(i).getText().equals("")) {
                Labels.get(i).setVisible(false);
            } else{
                    Labels.get(i).setVisible(true);
            }
            }
    }



    public void ResetCssTFields(){
        for(int i=0;i<TFields.size();i++){
            TFields.get(i).setStyle(null);
        }
    }

    @FXML
    void EnterPress(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            TextField ev = (TextField) event.getSource();
            if (ev.equals(Name)) {
                Phone.requestFocus();
                ResetHideLabels();
                ((Label) dict.get(Phone)).setStyle("-fx-text-fill:  #8B74BD");
                ((Label) dict.get(Phone)).setVisible(true);
            } else if (ev.equals(Char_Text)) {
                Code_Text.requestFocus();
                ResetHideLabels();
            } else if (ev.equals(Code_Text)) {
                Char_Button_Pr(null);
            }
        }
    }




    public void Del(ActionEvent e){
        try {
        StringsForTables temp = Table.getSelectionModel().getSelectedItem();
        int i = 0;
        boolean check = false;
        while (i < oblist.size()) {
            if (temp.equals(oblist.get(i))) {
                oblist.remove(i);
                check = true;
                break;
            }
            i++;
        }
        if(oblist.isEmpty()){
            placeboo=true;
            oblist.add(new StringsForTables("",""));
        }
    }
        catch (Exception eχ){
        eχ.printStackTrace();
    }

}



    /**
     * This button adds the new KTEO in the db
     *
     * @param event The event
     */
    @FXML
    void Ok_Button_Pr(ActionEvent event) {
            if(placeboo==true){
                oblist.remove(0);
            }
            boolean flag = false;
            ResetHideLabels();
            ResetCssTFields();
            String Data=null;
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
            if (flag == true) {
                if (placeboo==true) {
                    oblist.add(new StringsForTables(""));
                }
                return;
            }
            Sql sql = new Sql();
             Data = oblist.get(0).getString() + "~" + oblist.get(0).getString2();
             for (int i = 1; i < oblist.size(); i++) {
                    Data = Data + "|" + oblist.get(i).getString() + "~" + oblist.get(i).getString2();
             }
            int i;
            if(edit==false) {
                ModelCompany toAdd = new ModelCompany(Name.getText(), Phone.getText(),Data);
                i = sql.InstertCompany(toAdd, false);
            }
            else{
                toEdit.setPhone(Phone.getText());
                toEdit.setName(Name.getText());
                toEdit.setPrices(Data);
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
                if(oblist.isEmpty()){
                    placeboo=true;
                    oblist.add(new StringsForTables("",""));
                }
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
        oblist.remove(0);
        if (a.getPrices() != null&&!a.getPrices().isEmpty()) {
            String[] Ch = a.getPrices().split(Pattern.quote("|"));
            String[][] Dat = new String[Ch.length][2];
            for (int i = 0; i < Ch.length; i++) {
                Dat[i] = Ch[i].split(Pattern.quote("~"));
            }
            for (int i = 0; i < Ch.length; i++) {
                oblist.add(new StringsForTables(Dat[i][0], Dat[i][1]));
            }
            Table.setItems(oblist);
        }
        ResetHideLabels();
        placeboo=false;
        if(oblist.isEmpty()){
            placeboo=true;
            oblist.add(new StringsForTables("",""));
        }

    }


    @FXML
    void Char_Button_Pr(ActionEvent event) {
        if(placeboo==true){
            oblist.remove(0);
            placeboo=false;
        }
        String temp1 = Char_Text.getText();
        String temp2 = Code_Text.getText();
        oblist.add(new StringsForTables(temp1, temp2));
        Char_Text.setText("");
        Code_Text.setText("");
        Table.setItems(oblist);

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
