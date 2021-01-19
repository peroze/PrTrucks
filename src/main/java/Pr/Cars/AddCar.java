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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.sql.ResultSet;
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
public class AddCar implements Initializable {


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
    private TextField Char_Text;


    @FXML
    private TextField Code_Text;


    @FXML
    private TableView<StringsForTables> Table;


    @FXML
    private TableColumn<StringsForTables, String> Data_Col;


    @FXML
    private TableColumn<StringsForTables, String> Code_Col;


    @FXML
    private Button Char_Button;


    @FXML
    private Button Ok_Button;

    @FXML
    private ComboBox KTEO;

    @FXML
    private ComboBox Gas;

    @FXML
    private TextField Service;

    @FXML
    private Label Plais_Label;

    @FXML
    private Label Manu_Label;

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

    @FXML
    private Label Emmis_Label;

    @FXML
    private Label Categ_Label;

    @FXML
    private Label Kteo_Label;

    @FXML
    private Label ServKm_Label;

    @FXML
    private DatePicker Fire;

    @FXML
    private Label Fire_Label;

    @FXML
    private DatePicker Speed;

    @FXML
    private Label Speed_Label;

    private boolean edit = false;

    private ModelTruck toEdit;

    private int max_i;

    private int flag2;

    private ArrayList<Label> Labels;

    private ArrayList<TextField> TFields;

    ArrayList<String> Texts;

    Dictionary dict;

    boolean placeboo;


    private ObservableList<StringsForTables> oblist;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            placeboo = true;
            int test = 0;
            toEdit = null;
            dict = new Hashtable();
            Texts = new ArrayList<>();
            dict = new Hashtable();
            oblist = FXCollections.observableArrayList();
            oblist.add(new StringsForTables("", ""));
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Pane.requestFocus();
                }
            });
            ObservableList<String> Types = FXCollections.observableArrayList();
            Sql sql = new Sql();
            ResultSet rs = sql.Query_General_Types();
            while (rs.next()) {
                Types.add(rs.getString("Type"));
            }
            Type.setItems(Types);
            ObservableList<String> Locations = FXCollections.observableArrayList();
            rs = sql.Query_General_Locations();
            while (rs.next()) {
                Locations.add(rs.getString("City"));
            }
            Location.setItems(Locations);
            ObservableList<String> Kteo = FXCollections.observableArrayList();
            Kteo.add("1 Έτος");
            Kteo.add("2 Έτοι");
            Kteo.add("Όχι Κτεο");
            ObservableList<String> Gasc = FXCollections.observableArrayList();
            Gasc.add("6 Μήνες");
            Gasc.add("1 Έτος");
            Gasc.add("Όχι Κάρτα");
            KTEO.setItems(Kteo);
            Gas.setItems(Gasc);
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
                    if (placeboo == true) {
                        Table.getSelectionModel().clearSelection();
                    }
                });
                return row;
            }));
            Table.setItems(oblist);
            ContextMenu Cont = new ContextMenu();
            MenuItem Del = new MenuItem("Διαγραφή");
            Del.setOnAction(this::Del);
            Cont.getItems().add(Del);
            Table.setContextMenu(Cont);
            Labels = new ArrayList<>();
            Labels.add(Km_Label);
            Labels.add(Manu_Label);
            Labels.add(Plais_Label);
            Labels.add(Model_Label);
            Labels.add(Date_Label);
            Labels.add(ServKm_Label);
            Labels.add(Kteo_Label);
            Labels.add(Emmis_Label);
            Labels.add(Categ_Label);
            Labels.add(Locat_Label);
            Labels.add(Lisc_Label);
            Labels.add(Fire_Label);
            Labels.add(Speed_Label);
            int Size = Labels.size();
            TFields = new ArrayList<>();
            TFields.add(Kilometers);
            TFields.add(manufactor);
            TFields.add(Plaisio);
            TFields.add(Model);
            TFields.add(Service);
            TFields.add(Lisc_Plate);
            Texts.add("Χιλίομετρα");
            Texts.add("Κατασκευαστής");
            Texts.add("Αριθμός Πλαισίου");
            Texts.add("Μοντέλο");
            Texts.add("Ημερομηνία 1ης Κυκλοφορίας");
            Texts.add("Διάρκεια Service(Km)");
            Texts.add("Διάρκεια ΚΤΕΟ");
            Texts.add("Διάρκεια Κάρτας Καυσ.");
            Texts.add("Κατηγόρια");
            Texts.add("Τοποθεσία");
            Texts.add("Πινακίδα");
            Texts.add("Επομ Αναγώμωση Πυροσβ.");
            Texts.add("Επόμ. άδειασμα Ταχογραφου");
            for (int i = 0; i < Size; i++) {
                if (i == 4) {
                    dict.put(Date, Labels.get(i));
                    Date.setOnMouseClicked(this::setFocusTFIelds);
                } else if (i == 6) {
                    dict.put(KTEO, Labels.get(i));
                    KTEO.setOnMouseClicked(this::setFocusTFIelds);
                } else if (i == 7) {
                    dict.put(Gas, Labels.get(i));
                    Gas.setOnMouseClicked(this::setFocusTFIelds);
                } else if (i == 8) {
                    dict.put(Type, Labels.get(i));
                    Type.setOnMouseClicked(this::setFocusTFIelds);
                } else if (i == 9) {
                    dict.put(Location, Labels.get(i));
                    Location.setOnMouseClicked(this::setFocusTFIelds);

                } else if(i==11) {
                    dict.put(Fire, Labels.get(i));
                    Fire.setOnMouseClicked(this::setFocusTFIelds);
                }else if(i==12){
                    dict.put(Speed, Labels.get(i));
                    Speed.setOnMouseClicked(this::setFocusTFIelds);
                } else {
                    int k = i;
                    if (i == 10) {
                        k = 5;
                    } else if (i == 5) {
                        k = 4;
                    }
                    dict.put(TFields.get(k), Labels.get(i));
                    TFields.get(k).setOnMouseClicked(this::setFocusTFIelds);
                }
            }
            sql.Disconnect();
            ResetHideLabels();
        } catch (Exception e) {
            return;
        }

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
            if (i == 4 && Date.getValue() == null) {
                Labels.get(i).setVisible(false);
            } else if (i == 6 && KTEO.getValue() == null) {
                Labels.get(i).setVisible(false);
            } else if (i == 7 && Gas.getValue() == null) {
                Labels.get(i).setVisible(false);
            } else if (i == 8 && Type.getValue() == null) {
                Labels.get(i).setVisible(false);
            } else if (i == 9 && Location.getValue() == null) {
                Labels.get(i).setVisible(false);
            }else if(i==11&&Fire.getValue() == null) {
                Labels.get(i).setVisible(false);
            }else if(i==12&&Speed.getValue() == null){
                Labels.get(i).setVisible(false);
            } else if (i != 8 && i != 9 && i != 7 && i != 6 && i != 4 && i!=11 && i!=12) {
                int k = i;
                if (i == 5) {
                    k = 4;
                } else if (i == 10) {
                    k = 5;
                }
                if (TFields.get(k).getText() == null) {
                    Labels.get(i).setVisible(false);
                } else if (TFields.get(k).getText().equals("")) {
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
        Type.setStyle(null);
        KTEO.setStyle(null);
        Location.setStyle(null);
        Gas.setStyle(null);
        Fire.setStyle(null);
        Speed.setStyle(null);
    }

    public void Del(ActionEvent e) {
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
            if (oblist.isEmpty()) {
                placeboo = true;
                oblist.add(new StringsForTables("", ""));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public int getMax_i() {
        return max_i;
    }

    public void setMax_i(int max_i) {
        this.max_i = max_i;
    }

    /**
     * This class is used when the Ok button is pressed and it ads a new car in data Base
     *
     * @param event The event
     */
    @FXML
    void Ok_Button_Pr(ActionEvent event) {
        if (placeboo == true) {
            oblist.remove(0);
        }
        boolean flag = false;
        ResetCssTFields();
        ResetHideLabels();
        if (Plaisio.getText().equals("")) {
            Plais_Label.setVisible(true);
            Plais_Label.setStyle("-fx-text-fill: RED");
            Plais_Label.setText("Ο Αριθμός Πλαισίου είναι κενός");
            flag = true;
            Plaisio.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
        }
        if (Lisc_Plate.getText().equals("")) {
            Lisc_Label.setVisible(true);
            Lisc_Label.setStyle("-fx-text-fill: RED");
            Lisc_Label.setText("Η Πινακίδα είναι κενή");
            flag = true;
            Lisc_Plate.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
        }
        if (manufactor.getText().equals("")) {
            Manu_Label.setVisible(true);
            Manu_Label.setStyle("-fx-text-fill: RED");
            Manu_Label.setText("Ο Κατασκευαστής είναι κενός");
            flag = true;
            manufactor.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
        }
        if (Model.getText().equals("")) {
            Model_Label.setVisible(true);
            Model_Label.setStyle("-fx-text-fill: RED");
            Model_Label.setText("Το μοντέλο είναι κενό");
            flag = true;
            Model.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
        }
        if (Date.getValue() == null) {
            Date_Label.setVisible(true);
            Date_Label.setStyle("-fx-text-fill: RED");
            Date_Label.setText("Η Ημερομήνία είναι κενή");
            flag = true;

        }
        if (Speed.getValue() == null) {
            Speed_Label.setVisible(true);
            Speed_Label.setStyle("-fx-text-fill: RED");
            Speed_Label.setText("Η ημερ. ταχογράφου είναι κενή");
            flag = true;
        }
        if (Fire.getValue() == null) {
            Fire_Label.setVisible(true);
            Fire_Label.setStyle("-fx-text-fill: RED");
            Fire_Label.setText("Η ημερ. αναγώμοσης είναι κενή");
            flag = true;
        }
        if (Date.getValue() == null) {
            Date_Label.setVisible(true);
            Date_Label.setStyle("-fx-text-fill: RED");
            Date_Label.setText("Η Ημερομήνία είναι κενή");
            flag = true;

        }
        if (Type.getValue() == null) {
            Categ_Label.setVisible(true);
            Categ_Label.setStyle("-fx-text-fill: RED");
            Categ_Label.setText("Η Κατηγορία είναι κενή");
            flag = true;
            Type.setStyle(" -fx-background-color: transparent;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
        }
        if (Location.getValue() == null) {
            Locat_Label.setVisible(true);
            Locat_Label.setStyle("-fx-text-fill: RED");
            Locat_Label.setText("Η Τοποθεσία είναι κενή");
            flag = true;
            Location.setStyle(" -fx-background-color: transparent;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
        }
        if (KTEO.getValue() == null) {
            Kteo_Label.setVisible(true);
            Kteo_Label.setStyle("-fx-text-fill: RED");
            Kteo_Label.setText("Η Διάρκεια Κτέο είναι κενή");
            flag = true;
            KTEO.setStyle(" -fx-background-color: transparent;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
        }
        if (Gas.getValue() == null) {
            Emmis_Label.setVisible(true);
            Emmis_Label.setStyle("-fx-text-fill: RED");
            Emmis_Label.setText("Η Διάρκ. Κάρτας είναι κενή");
            flag = true;
            Gas.setStyle(" -fx-background-color: transparent;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
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
                Km_Label.setText("Τα Χιλιόμετρα πρέπει να είναι ακέραιος");
                placeboo = true;
                oblist.add(new StringsForTables(""));
                Km_Label.setVisible(true);
                Km_Label.setStyle("-fx-text-fill: RED");
                flag = true;
                Service.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            }
        }
        if (Service.getText().equals("")) {
            ServKm_Label.setText("Τα Χιλιόμετρα επόμενου Service είναι κενα");
            ServKm_Label.setVisible(true);
            ServKm_Label.setStyle("-fx-text-fill: RED");
            flag = true;
            Service.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
        } else {
            try {
                Integer.valueOf(Service.getText()); // if the given value contains characters a number format exception will be thrown
            } catch (NumberFormatException e) {
                placeboo = true;
                oblist.add(new StringsForTables(""));
                ServKm_Label.setText("Τα Χιλιόμετρα Service πρέπει να είναι αριθμός");
                ServKm_Label.setVisible(true);
                ServKm_Label.setStyle("-fx-text-fill: RED");
                flag = true;
                Kilometers.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            }
        }
        if (flag == true) {
            if (placeboo == true) {
                oblist.add(new StringsForTables(""));
            }
            return;
        }
        Sql sql = new Sql();
        String Data;
        Data = "";
        if (!oblist.isEmpty()) {
            Data = oblist.get(0).getString() + "~" + oblist.get(0).getString2();
            for (int i = 1; i < oblist.size(); i++) {
                Data = Data + "|" + oblist.get(i).getString() + "~" + oblist.get(i).getString2();
            }
        }
        int i;
        if (edit == false) {
            ModelTruck toAdd = new ModelTruck("-1", Lisc_Plate.getText(), manufactor.getText(), Model.getText(), Date.getValue().toString(), Plaisio.getText(), Type.getValue().toString(), Location.getValue().toString(), Kilometers.getText(), Data, Service.getText(), Gas.getValue().toString(), KTEO.getValue().toString(),Fire.getValue().toString(),Speed.getValue().toString());
            i = sql.InsertCar(toAdd, max_i, edit);
        } else {
            toEdit.setLiscPlate(Lisc_Plate.getText());
            toEdit.setManufactor(manufactor.getText());
            toEdit.setModel(toEdit.getModel());
            toEdit.setDate(Date.getValue().toString());
            toEdit.setPlaisio(Plaisio.getText());
            toEdit.setType(Type.getValue().toString());
            toEdit.setLocation(Location.getValue().toString());
            toEdit.setKilometers(Kilometers.getText());
            toEdit.setData(Data);
            toEdit.setKteoIn(KTEO.getValue().toString());
            toEdit.setGasIn(Gas.getValue().toString());
            toEdit.setServiceInkm(Service.getText());
            toEdit.setFireExtinguiser(Fire.getValue().toString());
            toEdit.setSpeedWriter(Speed.getValue().toString());
            i = sql.InsertCar(toEdit, max_i, edit);
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
            if (oblist.isEmpty()) {
                placeboo = true;
                oblist.add(new StringsForTables("", ""));
            }
            alert.showAndWait();
        }
    }

    @FXML
    void Char_Button_Pr(ActionEvent event) {
        if (placeboo == true) {
            oblist.remove(0);
            placeboo = false;
        }
        String temp1 = Char_Text.getText();
        String temp2 = Code_Text.getText();
        oblist.add(new StringsForTables(temp1, temp2));
        Char_Text.setText("");
        Code_Text.setText("");
        Table.setItems(oblist);

    }

    public void edit(ModelTruck s) {
        edit = true;
        Model.setText(s.getModel());
        manufactor.setText(s.getManufactor());
        Plaisio.setText(s.getPlaisio());
        LocalDate dat;
        dat = LocalDate.parse(s.getDate());
        Date.setValue(dat);
        Lisc_Plate.setText(s.getLiscPlate());
        Kilometers.setText(s.getKilometers());
        Location.setValue(s.getLocation());
        Type.setValue(s.getType());
        Service.setText(s.getServiceInkm());
        KTEO.setValue(s.getKteoIn());
        Gas.setValue(s.getGasIn());
        LocalDate dat1;
        dat1 = LocalDate.parse(s.getSpeedWriter());
        Speed.setValue(dat1);
        LocalDate dat2;
        dat2 = LocalDate.parse(s.getFireExtinguiser());
        Fire.setValue(dat2);
        oblist.remove(0);
        if (!s.getData().isEmpty()||!s.getData().equals("")) {
            System.out.println(s.getData().isEmpty());
            String[] Ch = s.getData().split(Pattern.quote("|"));
            String[][] Dat = new String[Ch.length][2];
            for (int i = 0; i < Ch.length; i++) {
                Dat[i] = Ch[i].split(Pattern.quote("~"));
            }
            for (int l = 0; l < Ch.length; l++) {
                oblist.add(new StringsForTables(Dat[l][0], Dat[l][1]));
            }
            Table.setItems(oblist);
        }
        toEdit = s;
        placeboo = false;
        if (oblist.isEmpty()) {
            oblist.add(new StringsForTables(""));
            placeboo = true;
        }
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
