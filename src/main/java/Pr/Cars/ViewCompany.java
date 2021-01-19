package Pr.Cars;

import com.jfoenix.controls.JFXTextField;
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
import org.kordamp.ikonli.javafx.FontIcon;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


/**
 * This Class is the controller for ViewCar.fxml which shows every detail of a specific Repair
 * @author peroze
 * @version 1.0 Alpha
 */
public class ViewCompany implements Initializable {

    @FXML
    private AnchorPane Panel;


    @FXML
    private Button Ok;

    @FXML
    private TextField Name;

    @FXML
    private TextField Phone;

    @FXML
    private TableView<StringsForTables> Table;

    @FXML
    private TableColumn<StringsForTables, String> Dat_Col;

    @FXML
    private TableColumn<StringsForTables, String> Code_Col;

    @FXML
    private HBox Top_Bar;

    @FXML
    private FontIcon Minimize_Button;

    @FXML
    private FontIcon X_Button;

    ModelCompany truck;

    private double x_Offset = 0;

    private double y_Offset = 0;

    ObservableList<StringsForTables> ObList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Dat_Col.setCellValueFactory(new PropertyValueFactory<>("string"));
        Code_Col.setCellValueFactory(new PropertyValueFactory<>("string2"));
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Panel.requestFocus();
            }
        });


    }

    /**
     * This  is getter for the repair
     * @return The repair
     */
    public ModelCompany getRepair() {
        return truck;
    }

    /**
     * **
     * Tηis is a setter for the truck
     * @param truck The truck
     */
    public void setCompany(ModelCompany truck) {
        this.truck = truck;
        ObList=FXCollections.observableArrayList();
        Name.setText(truck.getName());
        Phone.setText(truck.getPhone());
        if(truck.getPrices()!=null&&!truck.getPrices().isEmpty()) {
            String[] Ch = truck.getPrices().split(Pattern.quote("|"));
            String[][] Dat = new String[Ch.length][2];
            for (int i = 0; i < Ch.length; i++) {
                Dat[i] = Ch[i].split(Pattern.quote("~"));
            }
            for (int i = 0; i < Ch.length; i++) {
                ObList.add(new StringsForTables(Dat[i][0], Dat[i][1]));
            }
        }
        if(ObList.isEmpty()){
            ObList.add(new StringsForTables("",""));
            Table.setSelectionModel(null);
        }
        Table.setItems(ObList);
    }



    /**
     * This method closes the window when the OK button is pressed
     * @param event The event
     */
    @FXML
    void Ok_Button_Pressed(ActionEvent event) {
        Stage stage = (Stage) Ok.getScene().getWindow();
        stage.close();
    }


    /**
     * This method is used to minimize the Window
     * @param event This it the given event
     */
    @FXML
    void Minimize_Button_Pressed(MouseEvent event) {
        Stage stage = (Stage) ((FontIcon) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }


    /**
     * This is used to make the window draggable
     * @param event This it the given event
     */
    @FXML
    void Top_Bar_Dragged(MouseEvent event) {
        Panel.getScene().getWindow().setX(event.getScreenX() - x_Offset);
        Panel.getScene().getWindow().setY(event.getScreenY() - y_Offset);
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
     * This Closes the program
     * @param event The event when an action id made
     */
    @FXML
    void X_Button_Pressed(MouseEvent event) {
        Stage stage = (Stage) ((FontIcon) event.getSource()).getScene().getWindow();
        stage.close();
    }


}
