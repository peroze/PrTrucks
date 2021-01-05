package Pr.Cars;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;


/**
 * Thid class is the controller for AddRepair which add a new Repair in the db
 *
 * @author peroze
 * @version 1.0 Alpha
 */
public class AddRepair implements Initializable {


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
    private TextField Discreption;

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
    private TextField AddPart;

    @FXML
    private Button AddPart_Btn;

    @FXML
    private TextField Workshop;

    @FXML
    private TableView<StringsForTables> Table;

    @FXML
    private TableColumn<StringsForTables, String> Parts;

    @FXML
    private Label Workshop_Label;

    @FXML
    private Label Disc_Label;

    @FXML
    private Label Lisc_Label;

    @FXML
    private Label Price_Label;

    @FXML
    private Label Date_Label;

    @FXML
    private Label KM_Label;

    @FXML
    private TextField Receipt_Number;

    @FXML
    private Label Receipt_Number_Label;

    @FXML
    private TableColumn<StringsForTables, String> ParCode_Col;

    @FXML
    private TableColumn<StringsForTables, String> PartSupl_Col;

    @FXML
    private TableColumn<StringsForTables, String> Price_Column;

    @FXML
    private TableColumn<StringsForTables, String> ParRec_Col;

    @FXML
    private TableColumn<StringsForTables, String> ParDat_Col;

    @FXML
    private TextField Supplier_Text;

    @FXML
    private TextField Serial_Text;

    @FXML
    private TextField RepPrice_Text;

    @FXML
    private TextField RecNum_Text;

    @FXML
    private DatePicker DatePart;


    private ObservableList<StringsForTables> Oblist;

    private boolean edit = false;

    private ModelRepair toEdit;

    private ArrayList<Label> Labels;

    private ArrayList<TextField> TFields;

    ArrayList<String> Texts;

    boolean placeboo;

    Dictionary dict;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Texts=new ArrayList<>();
            TFields=new ArrayList<>();
            dict=new Hashtable();
            Labels=new ArrayList<>();
            toEdit = null;
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
            Oblist.add(new StringsForTables("","","","","",""));
            placeboo=true;
            Lisc_Plate.setItems(Cars);
            Parts.setCellValueFactory(new PropertyValueFactory<>("string"));
            ParCode_Col.setCellValueFactory(new PropertyValueFactory<>("string2"));
            PartSupl_Col.setCellValueFactory(new PropertyValueFactory<>("string3"));
            Price_Column.setCellValueFactory(new PropertyValueFactory<>("string4"));
            ParRec_Col.setCellValueFactory(new PropertyValueFactory<>("string5"));
            ParDat_Col.setCellValueFactory(new PropertyValueFactory<>("string6"));
            Table.setRowFactory((tv -> {
                TableRow<StringsForTables> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if(placeboo==true){
                        Table.getSelectionModel().clearSelection();
                    }
                    else if (event.getClickCount() == 2 && (!row.isEmpty())&&placeboo==false) {
                        DoubleClickTable();
                    }
                });
                return row;
            }));
            Table.setItems(Oblist);
            ContextMenu Cont = new ContextMenu();
            MenuItem Del = new MenuItem("Διαγραφή");
            Del.setOnAction(this::Del);
            Cont.getItems().add(Del);
            Table.setContextMenu(Cont);
            sql.Disconnect();
            Labels = new ArrayList<>();// TO DOOOOOOOO
            Labels.add(Lisc_Label);
            Labels.add(Date_Label);
            Labels.add(Disc_Label);
            Labels.add(KM_Label);
            Labels.add(Workshop_Label);
            Labels.add(Price_Label);
            Labels.add(Receipt_Number_Label);
            int Size = Labels.size();
            TFields = new ArrayList<>();
            TFields.add(Discreption);
            TFields.add(Kilometers);
            TFields.add(Workshop);
            TFields.add(Price);
            TFields.add(Receipt_Number);
            for (int i = 0; i < Labels.size(); i++) {
                Texts.add(Labels.get(i).getText());
            }
            for (int i = 0; i < Size; i++) {
                if (i == 1) {
                    dict.put(Date, Labels.get(i));
                    Date.setOnMouseClicked(this::setFocusTFIelds);
                } else if (i == 0) {
                    dict.put(Lisc_Plate, Labels.get(i));
                    Lisc_Plate.setOnMouseClicked(this::setFocusTFIelds);
                } else {
                    dict.put(TFields.get(i - 2), Labels.get(i));
                    TFields.get(i - 2).setOnMouseClicked(this::setFocusTFIelds);
                }
            }
            sql.Disconnect();
            ResetHideLabels();
        } catch (Exception e) {
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
            if(i==1&&Date.getValue()==null){
                Labels.get(i).setVisible(false);
            }
            else if(i==0&&Lisc_Plate.getValue()==null){
                Labels.get(i).setVisible(false);
            }
            else if(i!=0&&i!=1) {

                if (TFields.get(i-2).getText().equals( "")) {
                    Labels.get(i).setVisible(false);
                }
                else{
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
        Date.setStyle(null);
        Lisc_Plate.setStyle(null);
    }


    public void Del(ActionEvent e) {
        DoubleClickTable();
    }

    /**
     * This methods deletes an entry from the table and it is called when a row is double clicked
     */
    private void DoubleClickTable() {
        StringsForTables temp = Table.getSelectionModel().getSelectedItem();
        int i = 0;
        boolean check = false;
        while (i < Oblist.size()) {
            if (temp.equals(Oblist.get(i))) {
                Oblist.remove(i);
                check = true;
                break;
            }
        }
        if(Oblist.isEmpty()){
            placeboo=true;
            Oblist.add(new StringsForTables("","","","","",""));
        }
    }

    /**
     * This method is called when the AddPart button is pressed and it adds the part which have been replaced in the car int the changes list
     *
     * @param event The event
     */
    @FXML
    void AddPart_Btn_Pr(ActionEvent event) {
        if(placeboo==true){
            Oblist.remove(0);
            placeboo=false;
        }
        if(AddPart.getText().equals(null)){
            return;
        }
        String DateTemp;
        if(DatePart.getValue()==null){
            DateTemp="";
        }
        else{
            DateTemp=DatePart.getValue().toString();
        }
        Oblist.add(new StringsForTables(AddPart.getText(),Serial_Text.getText(),Supplier_Text.getText(),RepPrice_Text.getText(),RecNum_Text.getText(),DateTemp));
        AddPart.clear();
        Serial_Text.clear();
        Supplier_Text.clear();
        RepPrice_Text.clear();
        RecNum_Text.clear();
        Table.setItems(Oblist);
    }

    /**
     * This button adds the new repair in the db
     *
     * @param event The event
     */
    @FXML
    void Ok_Button_Pr(ActionEvent event) {
        boolean flag = false;
        ResetCssTFields();
        ResetHideLabels();
        if(placeboo==true){
            Oblist.remove(0);
        }
        if (Lisc_Plate.getValue() == null) {
            Lisc_Label.setStyle("-fx-text-fill: RED");
            Lisc_Label.setText("Η Πινακίδα είναι κενή");
            Lisc_Label.setVisible(true);
            flag = true;
            Lisc_Plate.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
        }
        if (Date.getValue() == null) {
            Date_Label.setStyle("-fx-text-fill: RED");
            Date_Label.setText("Η Ημερομηνία είναι κενή");
            Date_Label.setVisible(true);
            flag = true;
        }
        if (Discreption.getText().equals("")) {
            Disc_Label.setStyle("-fx-text-fill: RED");
            Disc_Label.setText("Η Περιγραφή είναι κενή");
            Disc_Label.setVisible(true);
            Discreption.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
        }
        if (Workshop.getText().equals("")) {
            Workshop_Label.setStyle("-fx-text-fill: RED");
            Workshop_Label.setText("Η Περιγραφή είναι κενή");
            Workshop_Label.setVisible(true);
            Workshop.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
        }
        if (Price.getText().equals("")) {
            Price_Label.setStyle("-fx-text-fill: RED");
            Price_Label.setText("Η τιμή είναι κενή");
            Price_Label.setVisible(true);
            flag = true;
            Price.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
        }
        if(Receipt_Number.getText().equals("")){
            Receipt_Number_Label.setStyle("-fx-text-fill: RED");
            Receipt_Number_Label.setText("Ο Αριθμός Απόδηξεις είναι κενή");
            Receipt_Number_Label.setVisible(true);
            flag=true;
            Receipt_Number.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
        }
        try {
            Double.valueOf(Price.getText());
        } catch (NumberFormatException e) {
            Price_Label.setStyle("-fx-text-fill: RED");
            Price_Label.setText("Η τιμή πρέπει να είναι αριθμός");
            Price_Label.setVisible(true);
            Price.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            flag = true;
        }
        try {
            Integer.valueOf(Kilometers.getText());
        } catch (NumberFormatException e) {
            KM_Label.setStyle("-fx-text-fill: RED");
            KM_Label.setText("Τα χιλίομετρα πρέπει να είναι αριθμός");
            KM_Label.setVisible(true);
            Kilometers.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
        }
        if (flag == true) {
            return;
        }
        Sql sql = new Sql();
        String Changes;
        Changes = Oblist.get(0).getString()+"~"+Oblist.get(0).getString2()+"~"+Oblist.get(0).getString3()+"~"+Oblist.get(0).getString4()+"~"+Oblist.get(0).getString5()+"~"+Oblist.get(0).getString6();
        for (int i = 1; i < Oblist.size(); i++) {
            Changes = Changes + "|" + Oblist.get(i).getString()+"~"+Oblist.get(i).getString2()+"~"+Oblist.get(i).getString3()+"~"+Oblist.get(i).getString4()+"~"+Oblist.get(i).getString5()+"~"+Oblist.get(i).getString6();
        }
        try {
            int prKm = sql.Query_Specific_LastRepairKM(Lisc_Plate.getValue().toString()).getInt("Kilometers");
            if (Integer.valueOf(Kilometers.getText()) - prKm > 100000) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Επιβαιβέωση");
                alert.setHeaderText("Προηδοποιηση Χιλιομέτρων");
                alert.setContentText("Τα χιλιόμετρα είναι πολύ μεγαλύτερα  από αυτά τής προηγούμενης Επισκευής. Είσαι σίγουρος ότι θέλεις να προχωρήσεις?");
                Optional<ButtonType> result = alert.showAndWait();
                if (!(result.get() == ButtonType.OK)) {
                    Kilometers.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                    KM_Label.setStyle("-fx-text-fill: RED");
                    KM_Label.setText("Λάθος στα χιλίομετρα");
                    KM_Label.setVisible(true);
                    sql.Disconnect();
                    return;
                }
            }
            else if (Integer.valueOf(Kilometers.getText()) < prKm ) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Επιβαιβέωση");
                alert.setHeaderText("Προηδοποιηση Χιλιομέτρων");
                alert.setContentText("Τα χιλιόμετρα είναι μικρότερα από αυτά τής προηγούμενης Επισκευής. Είσαι σίγουρος ότι θέλεις να προχωρήσεις?");
                Optional<ButtonType> result = alert.showAndWait();
                if (!(result.get() == ButtonType.OK)) {
                    Kilometers.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                    KM_Label.setStyle("-fx-text-fill: RED");
                    KM_Label.setText("Λάθος στα χιλίομετρα");
                    KM_Label.setVisible(true);
                    sql.Disconnect();
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        int i;
        if (edit == false) {
            ModelRepair toAdd = new ModelRepair(Lisc_Plate.getValue().toString(), Price.getText(), Kilometers.getText(), Date.getValue().toString(), Discreption.getText(), Workshop.getText(), Changes,Receipt_Number.getText());
            i = sql.InsertRepair(toAdd, false);
        } else {
            toEdit.setLiscPlate(Lisc_Plate.getValue().toString());
            toEdit.setDate(Date.getValue().toString());
            toEdit.setPrice(Price.getText());
            toEdit.setWorkshop(Workshop.getText());
            toEdit.setKilometers(Kilometers.getText());
            toEdit.setDiscreption(Discreption.getText());
            toEdit.setChanges(Changes);
            toEdit.setReceipt_Number(Receipt_Number.getText());
            i = sql.InsertRepair(toEdit, true);
        }
        if (i == 1) {

            try {
                ResultSet rs = sql.Query_Specific_Trucks(Lisc_Plate.getValue().toString());
                int km = rs.getInt("Kilometers");
                if (km < Integer.valueOf(Kilometers.getText())) {
                    ModelTruck repl = new ModelTruck(rs.getString("id"), rs.getString("LiscPlate"), rs.getString("Manufactor"), rs.getString("Model"), rs.getString("First_Date"), rs.getString("Plaisio"), rs.getString("Type"), rs.getString("Location"), Kilometers.getText(), rs.getString("Data"), rs.getString("ServiceInKm"), rs.getString("KTEOIn"), rs.getString("GasIn"));
                    sql.InsertCar(repl, 5, true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            sql.Disconnect();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Εισαγωγή Επιτυχής");
            //alert.setHeaderText("DB Creation Complete");
            alert.setContentText("Η επισκευή εισήχθει με επιτυχία στην Βαση");
            alert.showAndWait();
            Stage stage = (Stage) Ok_Button.getScene().getWindow();
            sql.Disconnect();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Εισαγωγή Απέτυχε");
            alert.setContentText("Η επισκευή δεν κατατάφερε να ενταχθεί, δοκιμάστε ξανά");
            alert.showAndWait();
        }

    }


    public void edit(ModelRepair s) {
        Oblist=FXCollections.observableArrayList();
        edit = true;
        Workshop.setText(s.getWorkshop());
        Price.setText(s.getPrice());
        Discreption.setText(s.getDiscreption());
        LocalDate dat;
        dat = LocalDate.parse(s.getDate());
        Date.setValue(dat);
        Lisc_Plate.setValue(s.getLiscPlate());
        Kilometers.setText(s.getKilometers());
        Receipt_Number.setText(s.getReceipt_Number());
        if (s.getChanges() != null) {
            String[] Ch = s.getChanges().split(Pattern.quote("|"));
            System.out.println(Ch[0]);
            for (int i = 0; i < Ch.length; i++) {
                String[] Ch1=Ch[i].split(Pattern.quote("~"));
                System.out.println(Ch1);
                Oblist.add(new StringsForTables(Ch1[0],Ch1[1],Ch1[2],Ch1[3],Ch1[4],Ch1[5]));
            }
            Table.setItems(Oblist);
        }
        toEdit = s;
        ResetHideLabels();
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


    @FXML
    void All_Hover(MouseEvent event) {

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
     * Τhis method is used to return the new Entry
     *
     * @param Entry The new entry
     * @return The new Entry
     */
    public ArrayList<String> GetNewEntry(ArrayList<String> Entry) {
        return Entry;
    }

}
