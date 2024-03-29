package Pr.Cars;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * This Class is the controller for ViewService.fxml which shows every detail of a specific Service
 *
 * @author peroze
 * @version 1.0 Alpha
 */
public class ViewExternalPhone implements Initializable {

    @FXML
    private AnchorPane Panel;

    @FXML
    private TextField Name;

    @FXML
    private TextField Phone;

    @FXML
    private TextField Type;

    @FXML
    private TextField Address;

    @FXML
    private TextField Email;

    @FXML
    private Button Ok;

    @FXML
    private TableView<StringsForTables> Table;

    @FXML
    private TableColumn<StringsForTables, String> Note;

    @FXML
    private HBox Top_Bar;

    @FXML
    private ImageView Minimize_Button;

    @FXML
    private ImageView X_Button;

    ModelExternalPhoneList list;

    private double x_Offset = 0;

    private double y_Offset = 0;

    ObservableList<StringsForTables> ObList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Panel.requestFocus();
            }
        });
        Note.setCellValueFactory(new PropertyValueFactory<>("string"));
    }

    /**
     * This method is a getter for the Service
     *
     * @return The comlpete information of a specific Service
     */
    public ModelExternalPhoneList getService() {
        return list;
    }

    /**
     * This method is a setter for the specific
     *
     * @param list
     */
    public void setService(ModelExternalPhoneList list) {
        this.list = list;
        ObList = FXCollections.observableArrayList();
        Name.setText(list.getName());
        Address.setText(list.getAddress());
        Phone.setText(list.getPhone());
        Email.setText(list.getEmail());
        Type.setText(list.getDiscreption());
        if (list.getNotes() != null&&!list.getNotes().isEmpty()) {
            String[] Ch = list.getNotes().split(Pattern.quote("|"));
            for (int i = 0; i < Ch.length; i++) {
                ObList.add(new StringsForTables(Ch[i]));
            }
        } else {
            ObList.add(new StringsForTables(""));
            Table.setSelectionModel(null);
        }
        System.out.println(ObList.get(0).getString()+" Mpike");
        Table.setItems(ObList);
    }

    /**
     * This method closes the window when the OK button is pressed
     *
     * @param event
     */
    @FXML
    void Ok_Button_Pressed(ActionEvent event) {
        Stage stage = (Stage) Ok.getScene().getWindow();
        stage.close();
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
     * This is used to make the window draggable
     *
     * @param event This it the given event
     */
    @FXML
    void Top_Bar_Dragged(MouseEvent event) {
        Panel.getScene().getWindow().setX(event.getScreenX() - x_Offset);
        Panel.getScene().getWindow().setY(event.getScreenY() - y_Offset);
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
     * This Closes the program
     *
     * @param event The event when an action id made
     */
    @FXML
    void X_Button_Pressed(MouseEvent event) {
        Stage stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        stage.close();
    }


}
