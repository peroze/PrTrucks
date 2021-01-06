package Pr.Cars;

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
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * This Class is the Corntoller for AddEmmisionCard.fxml which adds a new Emmision Card in the db
 *
 * @author peroze
 * @version 1.0 Alpha
 */
public class AddEmmisionCard implements Initializable {


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
    private ComboBox Lisc_Plate;


    @FXML
    private TextField Kilometers;


    @FXML
    private DatePicker Date;


    @FXML
    private Button Ok_Button;


    @FXML
    private Label Km_Label;

    @FXML
    private Label Lisc_Label;

    @FXML
    private Label Date_Label;

    private ArrayList<Label> Labels;


    ArrayList<String> Texts;

    Dictionary dict;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Texts=new ArrayList<>();
        Labels=new ArrayList<>();
        dict=new Hashtable();
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
        Labels.add(Lisc_Label);
        Texts.add("Πινακίδα");
        Labels.add(Km_Label);
        Texts.add("Χιλιόμετρα");
        Labels.add(Date_Label);
        Texts.add("Ημερομηνια");
        dict.put(Lisc_Plate,Lisc_Label);
        dict.put(Kilometers,Km_Label);
        dict.put(Date,Date_Label);
        Lisc_Plate.setOnMouseClicked(this::setFocusTFIelds);
        Kilometers.setOnMouseClicked(this::setFocusTFIelds);
        Date.setOnMouseClicked(this::setFocusTFIelds);
        ResetHideLabels();
    }

    public void setFocusTFIelds(MouseEvent e){
        ResetHideLabels();
        ((Label)dict.get(e.getSource())).setStyle("-fx-text-fill:  #8B74BD");
        ((Label)dict.get(e.getSource())).setVisible(true);
    }

    public void ResetHideLabels(){
        for(int i=0;i<Labels.size();i++) {
            Labels.get(i).setText(Texts.get(i));
            Labels.get(i).setStyle("-fx-text-fill:#FA8072");
        }

            if (Kilometers.getText().equals("")) {
                Labels.get(1).setVisible(false);
            } else{
                Labels.get(1).setVisible(true);

            }
            if(Lisc_Plate.getValue()==null){
                Labels.get(0).setVisible(false);
            }
            else{
                Labels.get(0).setVisible(true);
            }
            if(Date.getValue()==null){
                Labels.get(2).setVisible(false);
            }
            else{
                Labels.get(2).setVisible(true);
            }
    }

    public void ResetCssTFields(){
        Date.setStyle(null);
        Lisc_Plate.setStyle(null);
        Kilometers.setStyle(null);
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
            ResetHideLabels();
            ResetCssTFields();
            if (Lisc_Plate.getValue() == null) {
                Lisc_Label.setText("Η Πινακίδα είναι κενή");
                Lisc_Label.setStyle("-fx-text-fill: RED");
                Lisc_Label.setVisible(true);
                flag = true;
                Lisc_Plate.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            }
            if (Date.getValue() == null) {
                Date_Label.setText("Η Ημερομηνία είναι κενή");
                Date_Label.setStyle("-fx-text-fill: RED");
                Date_Label.setVisible(true); //Change Style
                flag = true;
            }
            if (Kilometers.getText().equals("")) {
                Km_Label.setStyle("-fx-text-fill: RED");
                Km_Label.setText("Τα Χιλιόμετρα είναι κενά");
                Km_Label.setVisible(true);
                flag = true;
                Kilometers.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            } else {
                try {
                    Integer.valueOf(Kilometers.getText());
                } catch (NumberFormatException e) {
                    Km_Label.setStyle("-fx-text-fill: RED");
                    Km_Label.setText("Τα Χιλιόμετρα πρέπει να είναι ακέραιος");
                    Kilometers.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                    Km_Label.setVisible(true);
                }
            }
            if (flag == true) {
                return;
            }
            Sql sql = new Sql();
            ResultSet rs = null;
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
            ModelEmmisionCard toAdd;
            if (nextG == 6) {
                toAdd = new ModelEmmisionCard(Lisc_Plate.getValue().toString(), Kilometers.getText(), Date.getValue().toString(), "FALSE", Date.getValue().plusMonths(nextG).toString());
            } else {
                toAdd = new ModelEmmisionCard(Lisc_Plate.getValue().toString(), Kilometers.getText(), Date.getValue().toString(), "FALSE", Date.getValue().plusYears(nextG).toString());
            }
            int prKm=sql.Query_Specific_LastEmmisionKM(Lisc_Plate.getValue().toString()).getInt("Kilometers");
            if ((prKm > Integer.valueOf(Kilometers.getText())))
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Επιβαιβέωση");
                alert.setHeaderText("Προηδοποιηση Χιλιομέτρων");
                alert.setContentText("Τα χιλιόμετρα είναι λιγοτέρα από αυτά της προηγούμενής Κάρτας. Είσαι σίγουρος ότι θέλεις να προχωρήσεις?");
                Optional<ButtonType> result = alert.showAndWait();
                if (!(result.get() == ButtonType.OK)) {
                    Km_Label.setText("Τα χιλίομετρα είναι λιγότερα από τα προήγουμενα");
                    Km_Label.setVisible(true);
                    Kilometers.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                    sql.Disconnect();
                    return;
                }
            }
            else if (Integer.valueOf(Kilometers.getText())-prKm>100000  )
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
            int i = sql.InstertEmmisionCard(toAdd);
            if (i == 1) {
                rs = sql.Query_Specific_Trucks(Lisc_Plate.getValue().toString());
                int km = rs.getInt("Kilometers");
                if (km < Integer.valueOf(Kilometers.getText())) {
                    ModelTruck repl = new ModelTruck(rs.getString("id"), rs.getString("LiscPlate"), rs.getString("Manufactor"), rs.getString("Model"), rs.getString("First_Date"), rs.getString("Plaisio"), rs.getString("Type"), rs.getString("Location"), Kilometers.getText(), rs.getString("Data"), rs.getString("ServiceInKm"), rs.getString("KTEOIn"), rs.getString("GasIn"));
                    sql.InsertCar(repl, 5, true);
                }
                sql.Disconnect();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Εισαγωγή Επιτυχής");
                //alert.setHeaderText("DB Creation Complete");
                alert.setContentText("Η Κάρτα εισήχθει με επιτυχία στην Βαση");
                alert.showAndWait();
                Stage stage = (Stage) Ok_Button.getScene().getWindow();
                stage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Εισαγωγή Απέτυχε");
                alert.setContentText("Η κάρτα δεν κατατάφερε να ενταχθεί, δοκιμάστε ξανά");
                alert.showAndWait();

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

    public ArrayList<String> GetNewEntry(ArrayList<String> Entry) {
        return Entry;
    }

}
