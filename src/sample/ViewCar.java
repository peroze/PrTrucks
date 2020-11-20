package sample;

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

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


/**
 * This Class is the controller for ViewCar.fxml which shows every detail of a specific Repair
 * @author peroze
 * @version 1.0 Alpha
 */
public class ViewCar implements Initializable {

    @FXML
    private AnchorPane Panel;


    @FXML
    private Button Ok;


    @FXML
    private TextField LiscPlate;


    @FXML
    private TextField Location;

    @FXML
    private TextField Type;

    @FXML
    private TextField Date;

    @FXML
    private TextField Kilometers;


    @FXML
    private TextField Plaisio;


    @FXML
    private TableView<StringsForTables> Table;

    @FXML
    private TableColumn<StringsForTables, String> Dat_Col;

    @FXML
    private TableColumn<StringsForTables, String> Code_Col;

    @FXML
    private HBox Top_Bar;

    @FXML
    private ImageView Minimize_Button;

    @FXML
    private ImageView X_Button;

    ModelTruck truck;

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
    public ModelTruck getRepair() {
        return truck;
    }

    /**
     * **
     * Tηis is a setter for the truck
     * @param truck The truck
     */
    public void setTruck(ModelTruck truck) {
        this.truck = truck;
        ObList = FXCollections.observableArrayList();
        LiscPlate.setText(truck.getLiscPlate());
        Location.setText(truck.getLocation());
        Date.setText(truck.getDate());
        Kilometers.setText(truck.getKilometers());
        Plaisio.setText(truck.getPlaisio());
        Type.setText(truck.getType());
        String[] Ch=truck.getData().split(Pattern.quote("|"));
        String[][] Dat= new String[Ch.length][2];
        for (int i=0;i<Ch.length;i++){
            Dat[i]=Ch[i].split(Pattern.quote("~"));
        }
        for(int i=0;i<Ch.length;i++){
            ObList.add(new StringsForTables(Dat[i][0],Dat[i][1]));
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
        Stage stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
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
        Stage stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        stage.close();
    }


}
