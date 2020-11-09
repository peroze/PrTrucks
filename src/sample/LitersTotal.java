package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class LitersTotal {

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
    private ComboBox<?> Lisc;

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
    private ComboBox<?> DistanceBC;

    @FXML
    private Button Totals_Button;

    @FXML
    private Button ByCar_Button;

    private String Active = "Totals";


    @FXML
    void ByCar_Button_Pr(ActionEvent event) {
        if(Active.equals("Totals")){
            ByCar_Button.setStyle("-fx-background-radius: 0;-fx-border-radius: 0;-fx-border-width: 0 0 0 3;-fx-border-color: #FA8072;-fx-background-color: grey;-fx-text-fill: white;");
            Totals_Button.setStyle("-fx-background-radius: 0;-fx-border-radius: 0;-fx-border-width: 0;-fx-background-color: #000013;-fx-text-fill: white;");
            Active="ByCar";
            Totals_Pane.setVisible(false);
            ByCar_Pane.setVisible(true);
        }
    }

    @FXML
    void Totals_Button_Pr(ActionEvent event) {
        if(Active.equals("ByCar")){
            Totals_Button.setStyle("-fx-background-radius: 0;-fx-border-radius: 0;-fx-border-width: 0 0 0 3;-fx-border-color: #FA8072;-fx-background-color: grey;-fx-text-fill: white;");
            ByCar_Button.setStyle("-fx-background-radius: 0;-fx-border-radius: 0;-fx-border-width: 0;-fx-background-color: #000013;-fx-text-fill: white;");
            Active="Totals";
            ByCar_Pane.setVisible(false);
            Totals_Pane.setVisible(true);
        }
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


    /**
     * This is used to make the window draggable
     *
     * @param event This it the given event
     */
    @FXML
    void Top_Bar_Dragged(MouseEvent event) {
        Totals_Button.getScene().getWindow().setX(event.getScreenX() - x_Offset);
        Totals_Button.getScene().getWindow().setY(event.getScreenY() - y_Offset);
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
        Stage stage = (Stage) ((ImageView) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

}
