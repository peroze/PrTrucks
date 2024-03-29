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
import org.apache.poi.ss.formula.functions.T;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


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
    private FontIcon Minimize_Button;

    @FXML
    private FontIcon X_Button;


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

    private ArrayList<Label> Labels;

    private ArrayList<TextField> TFields;

    ArrayList<String> Texts;

    Dictionary dict;

    private ObservableList<StringsForTables> Oblist;

    boolean placeboo;

    private int flag2;// This is used to know which field threw an excpetion


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        placeboo=true;
        TFields=new ArrayList<>();
        Labels=new ArrayList<>();
        Texts=new ArrayList<>();
        dict=new Hashtable();
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
        Table.setRowFactory((tv -> {
            TableRow<StringsForTables> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(placeboo==true){
                    Table.getSelectionModel().clearSelection();
                }
            });
            return row;
        }));
        Table.setEditable(true);
        Parts.setCellValueFactory(new PropertyValueFactory<>("string"));
        Parts.setCellFactory(TextFieldTableCell.<StringsForTables>forTableColumn());
        Parts.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<StringsForTables, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<StringsForTables, String> event) {
                        ((StringsForTables) event.getTableView().getItems().get(
                                event.getTablePosition().getRow())
                        ).setString(event.getNewValue());
                    }
                }
        );
        Oblist.add(new StringsForTables(""));
        Table.setItems(Oblist);
        Lisc_Plate.valueProperty().addListener((ov, oldValue, newValue)->{
            Date.requestFocus();
            ResetHideLabels();
            ((Label) dict.get(Date)).setStyle("-fx-text-fill:  #8B74BD");
            ((Label) dict.get(Date)).setVisible(true);
            Date.show();
        });
        Date.valueProperty().addListener((ov, oldValue, newValue)->{
            Kilometers.requestFocus();
            ResetHideLabels();
            ((Label) dict.get(Kilometers)).setStyle("-fx-text-fill:  #8B74BD");
            ((Label) dict.get(Kilometers)).setVisible(true);
        });
        ContextMenu Cont = new ContextMenu();
        MenuItem Del = new MenuItem("Διαγραφή");
        Del.setOnAction(this::Del);
        Cont.getItems().add(Del);
        Table.setContextMenu(Cont);
        sql.Disconnect();
        Labels.add(Lisc_Label);
        Texts.add("Πινακίδα");
        dict.put(Lisc_Plate,Lisc_Label);
        Lisc_Plate.setOnMouseClicked(this::setFocusTFIelds);
        Labels.add(Date_Label);
        Texts.add("Ημερομηνία");
        dict.put(Date,Date_Label);
        Date.setOnMouseClicked(this::setFocusTFIelds);
        Labels.add(Km_Label);
        TFields.add(Kilometers);
        Texts.add("Χιλιόμετρα");
        dict.put(Kilometers,Km_Label);
        Kilometers.setOnMouseClicked(this::setFocusTFIelds);
        Labels.add(Locat_Label);
        TFields.add(Workshop);
        Texts.add("Εταιρία");
        dict.put(Workshop,Locat_Label);
        Workshop.setOnMouseClicked(this::setFocusTFIelds);
        Labels.add(Model_Label);
        TFields.add(Price);
        Texts.add("Τιμή");
        dict.put(Price,Model_Label);
        Price.setOnMouseClicked(this::setFocusTFIelds);
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
            Labels.get(i).setStyle("-fx-text-fill:#ff6542");
            if(i==0&&Lisc_Plate.getValue()==null){
                Labels.get(i).setVisible(false);
            }
            else if(i==1&&Date.getValue()==null){
                Labels.get(i).setVisible(false);
            }
            else if(i!=0&&i!=1){
                if(TFields.get(i-2).getText()==null){
                    Labels.get(i).setVisible(false);
                }
                else if (TFields.get(i-2).getText().equals("")) {
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
        Date.setStyle(null);
        Lisc_Plate.setStyle(null);


    }

    @FXML
    void EnterPressed(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            TextField ev = (TextField) event.getSource();
            System.out.println(ev.getId());
            if (ev.equals(Kilometers)) {
                Workshop.requestFocus();
                ResetHideLabels();
                ((Label) dict.get(Workshop)).setStyle("-fx-text-fill:  #8B74BD");
                ((Label) dict.get(Workshop)).setVisible(true);

            } else if (ev.equals(Workshop)) {
                Price.requestFocus();
                ResetHideLabels();
                ((Label) dict.get(Price)).setStyle("-fx-text-fill:  #8B74BD");
                ((Label) dict.get(Price)).setVisible(true);
            }
        }
    }

    public void Del(ActionEvent e) {
        StringsForTables temp = Table.getSelectionModel().getSelectedItem();
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
            placeboo=true;
            Oblist.add(new StringsForTables(""));
        }
    }



    /**
     * This method is called when the AddWarn button is pressed and it adds a warning which have been given in KTEO
     *
     * @param event The event
     */
    @FXML
    void AddWarn_Btn_Pr(ActionEvent event) {
        if(placeboo==true){
            Oblist.remove(0);
            placeboo=false;
        }
        Oblist.add(new StringsForTables(AddWarn.getText()));
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
            if(placeboo==true){
                Oblist.remove(0);
            }
            flag2 = -1;
            boolean flag = false;
            ResetCssTFields();
            ResetHideLabels();
            if (Lisc_Plate.getValue() == null) {
                Lisc_Label.setText("Η Πινακίδα είναι κενή");
                Lisc_Label.setStyle("-fx-text-fill: RED");
                Lisc_Label.setVisible(true);
                flag = true;
                Lisc_Plate.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            }
            if (Workshop.getText().equals("")) {
                Locat_Label.setText("Η Εταιρία είναι κενή");
                Locat_Label.setStyle("-fx-text-fill: RED");
                Locat_Label.setVisible(true);
                flag = true;
                Workshop.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            }
            if (Date.getValue() == null) {
                Date_Label.setText("Η Ημερομηνία είναι κενή");
                Date_Label.setStyle("-fx-text-fill: RED");
                Date_Label.setVisible(true); //Change Style
                flag = true;

            }
            if (Kilometers.getText().equals("")) {
                Km_Label.setText("Τα Χιλιόμετρα είναι κενά");
                Km_Label.setStyle("-fx-text-fill: RED");
                Km_Label.setVisible(true);
                flag = true;
                Kilometers.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            } else {
                try {
                    Double.valueOf(Kilometers.getText());
                } catch (NumberFormatException e) {
                    placeboo=true;
                    Oblist.add(new StringsForTables(""));
                    Km_Label.setText("Τα Χιλιόμετρα πρέπει να είναι ακέραιος");
                    Km_Label.setStyle("-fx-text-fill: RED");
                    Km_Label.setVisible(true);
                    flag = true;
                    Kilometers.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                }
            }
            if (Price.getText().equals("")) {
                Model_Label.setText("Η τιμή είναι κενή");
                Model_Label.setStyle("-fx-text-fill: RED");
                Model_Label.setVisible(true);
                flag = true;
                Price.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            } else {
                try {
                    Integer.valueOf(Kilometers.getText());
                } catch (NumberFormatException e) {
                    placeboo=true;
                    Oblist.add(new StringsForTables(""));
                    Model_Label.setText("Τα Χιλιόμετρα πρέπει να είναι ακέραιος");
                    Model_Label.setStyle("-fx-text-fill: RED");
                    Model_Label.setVisible(true);
                    flag = true;
                    Price.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                }
            }
            if (flag == true) {

                if(placeboo==true) {
                    Oblist.add(new StringsForTables(""));
                }
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
            int prKm=sql.Query_Specific_LastKteoKM(Lisc_Plate.getValue().toString()).getInt("Kilometers");
            if (prKm > Integer.valueOf(Kilometers.getText()))
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Επιβαιβέωση");
                alert.setHeaderText("Προηδοποιηση Χιλιομέτρων");
                alert.setContentText("Τα χιλιόμετρα είναι λιγοτέρα από αυτά του προηγούμενου KTEO. Είσαι σίγουρος ότι θέλεις να προχωρήσεις?");
                Optional<ButtonType> result = alert.showAndWait();
                if (!(result.get() == ButtonType.OK)) {
                    Km_Label.setText("Τα χιλίομετρα είναι λιγότερα από τα προήγουμενα");
                    Km_Label.setVisible(true);
                    Kilometers.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                    sql.Disconnect();
                    return;
                }
            }
             else if (Integer.valueOf(Kilometers.getText())-prKm>200000  )
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Επιβαιβέωση");
                alert.setHeaderText("Προηδοποιηση Χιλιομέτρων");
                alert.setContentText("Τα χιλιόμετρα είναι πολύ μεγαλύτερα  από αυτά του προηγούμενου KTEO. Είσαι σίγουρος ότι θέλεις να προχωρήσεις?");
                Optional<ButtonType> result = alert.showAndWait();
                if (!(result.get() == ButtonType.OK)) {
                    Km_Label.setText("Τα χιλίομετρα είναι πολυ μεγαλύτερα από τα προήγουμενα");
                    Km_Label.setVisible(true);
                    Kilometers.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                    sql.Disconnect();
                    return;
                }
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
                    ModelTruck repl = new ModelTruck(rs.getString("id"), rs.getString("LiscPlate"), rs.getString("Manufactor"), rs.getString("Model"), rs.getString("First_Date"), rs.getString("Plaisio"), rs.getString("Type"), rs.getString("Location"), Kilometers.getText(), rs.getString("Data"), rs.getString("ServiceInKm"), rs.getString("KTEOIn"), rs.getString("GasIn"),rs.getString("FireExtiguiser"),rs.getString("SpeedWriter"));
                    sql.InsertCar(repl, 5, true);
                }
                sql.Disconnect();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Εισαγωγή Επιτυχής");
                //alert.setHeaderText("DB Creation Complete");
                alert.setContentText("Το Service εισήχθει με επιτυχία στην Βαση");
                alert.showAndWait();
                Stage stage = (Stage) Ok_Button.getScene().getWindow();
                sql.Disconnect();
                stage.close();
            } else {
                sql.Disconnect();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Εισαγωγή Απέτυχε");
                alert.setContentText("Το KTEO δεν κατατάφερε να ενταχθεί, δοκιμάστε ξανά");
                alert.showAndWait();
                Kilometers.clear();
                Oblist.add(new StringsForTables(""));
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
