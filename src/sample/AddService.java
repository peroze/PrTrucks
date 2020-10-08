package sample;

import javafx.beans.Observable;
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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class AddService implements Initializable {


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
    private TextField Discreption;

    @FXML
    private TextField Price;

    @FXML
    private TextField Lisc_Plate;

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
    private TableColumn<StringsForTables,String> Parts;

    private ObservableList<StringsForTables> Oblist;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Oblist = FXCollections.observableArrayList();


    }


    @FXML
    void AddPart_Btn_Pr(ActionEvent event) {
        Oblist.add(new StringsForTables(AddPart.getText()));
        Parts.setCellValueFactory(new PropertyValueFactory<>("string"));
        AddPart.clear();
        Table.setItems(Oblist);
    }


    @FXML
    void Ok_Button_Pr(ActionEvent event) {
        Sql sql=new Sql();
        String Changes;
        Changes=Oblist.get(0).getString();
        for(int i=1;i<Oblist.size();i++){
            Changes= Changes+"|"+Oblist.get(i).getString();
        }

        ModelService toAdd=new ModelService(Lisc_Plate.getText(),Date.getValue().toString(),Kilometers.getText(),Discreption.getText(),Changes,Workshop.getText(),Date.getValue().plusYears(1).toString(),String.valueOf(Integer.valueOf(Kilometers.getText())+60000),Price.getText());

        int i=sql.InsertService(toAdd);
        if(i==1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Εισαγωγή Επιτυχής");
            //alert.setHeaderText("DB Creation Complete");
            alert.setContentText("Το Service εισήχθει με επιτυχία στην Βαση");
            alert.showAndWait();
            Stage stage = (Stage) Ok_Button.getScene().getWindow();
            stage.close();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Εισαγωγή Απέτυχε");
            alert.setContentText("Το Service δεν κατατάφερε να ενταχθεί, δοκιμάστε ξανά");
            alert.showAndWait();
            Kilometers.clear();
            Lisc_Plate.clear();
            Discreption.clear();
            AddPart.clear();
            Price.clear();
            Oblist= FXCollections.observableArrayList();
            Table.setItems(Oblist);
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
