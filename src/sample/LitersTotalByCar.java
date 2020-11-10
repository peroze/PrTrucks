package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;


public class LitersTotalByCar {

    private double x_Offset = 0;
    private double y_Offset = 0;

    @FXML
    private HBox Top_Bar;

    @FXML
    private ImageView Minimize_Button;

    @FXML
    private ImageView X_Button;

    @FXML
    private AnchorPane Totals_Pane;

    @FXML
    private ComboBox Distance;

    @FXML
    private TableView<?> Table1;

    @FXML
    private TableColumn<?, String> Date;

    @FXML
    private TableColumn<?, String> Amount;

    @FXML
    private DatePicker From;

    @FXML
    private DatePicker To;

    @FXML
    private AnchorPane ByCar_Pane;

    @FXML
    private ComboBox Lisc;

    @FXML
    private TableView<?> TableBC;

    @FXML
    private TableColumn<?, ?> Date1;

    @FXML
    private TableColumn<?, ?> Amount1;

    @FXML
    private DatePicker FromBC;

    @FXML
    private DatePicker ToBC;

    @FXML
    private ComboBox DistanceBC;

    @FXML
    void Show_Button_Pr(ActionEvent event) {

    }



}
