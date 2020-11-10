package sample;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This Class is the controller of the main Menu
 *
 * @author peroze
 * @version 1.0 Alpha
 */
public class Main_Menu /*implements Initializable */{

    private double x_Offset = 0;
    private double y_Offset = 0;


    @FXML
    private ImageView Minimize_Button;

    @FXML
    private HBox Top_Bar;

    @FXML
    private ImageView X_Button;

    @FXML
    private Button Cars_Button;

    @FXML
    private Button Gas_Button;

    @FXML
    private Button Kteo_Button;

    @FXML
    private Button Service_Button;

    @FXML
    private Button Repair_Button;

    @FXML
    private Button Refill_Button;

    @FXML
    private Button Expenses_Button;


    @FXML
    private Button Exit_Button;

    @FXML
    private BorderPane Border_Pane;

    /**
     * This method returns the main panel
     *
     * @return The panel
     */
    public BorderPane getBorder_Pane() {
        return Border_Pane;
    }


    /**
     * This method opens the Cars List Window
     *
     * @param event The event when an action id made
     */
    @FXML
    void Cars_Button_Pressed(ActionEvent event) {
        try {

            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Drawer.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("TrucksList.fxml"));
            Parent root2 = (Parent) fxmlloader.load();
            fxmlloader.<Drawer>getController().setActive("Trucks");
            fxmlloader.<Drawer>getController().changeBg();
            fxmlloader.<Drawer>getController().setParent(this);
            Border_Pane.setCenter(root);
            Border_Pane.setLeft(root2);
            new FadeIn(root).play();
            new FadeIn(root2).play();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * This method opens the Gas List Window
     *
     * @param event The event when an action is made
     */
    @FXML
    void Gas_Button_Pressed(ActionEvent event) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Drawer.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("EmmisionCardList.fxml"));
            Parent root2 = (Parent) fxmlloader.load();
            fxmlloader.<Drawer>getController().setActive("EmmisionCard");
            fxmlloader.<Drawer>getController().changeBg();
            fxmlloader.<Drawer>getController().setParent(this);
            Border_Pane.setCenter(root);
            Border_Pane.setLeft(root2);
            new FadeIn(root).play();
            new FadeIn(root2).play();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * This method opens the Kteo List Window
     *
     * @param event The event when an action id made
     */
    @FXML
    void Kteo_Button_Pressed(ActionEvent event) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Drawer.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("KTEOList.fxml"));
            Parent root2 = (Parent) fxmlloader.load();
            fxmlloader.<Drawer>getController().setActive("Kteo");
            fxmlloader.<Drawer>getController().changeBg();
            fxmlloader.<Drawer>getController().setParent(this);
            Border_Pane.setCenter(root);
            Border_Pane.setLeft(root2);
            new FadeIn(root).play();
            new FadeIn(root2).play();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method opens the expenses windows
     * @param event The event
     */
    @FXML
    void Expenses_Button_Pressed(ActionEvent event){
        System.out.println("ToDo");
    }


    /**
     * This method opens the Service List Window
     *
     * @param event The event when an action id made
     */
    @FXML
    void ServiceButton_Pressed(ActionEvent event) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Drawer.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("ServiceList.fxml"));
            Parent root2 = (Parent) fxmlloader.load();
            fxmlloader.<Drawer>getController().setActive("Service");
            fxmlloader.<Drawer>getController().changeBg();
            fxmlloader.<Drawer>getController().setParent(this);
            Border_Pane.setCenter(root);
            Border_Pane.setLeft(root2);
            new FadeIn(root).play();
            new FadeIn(root2).play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method opens the Speedometer List Window
     *
     * @param event The event when an action id made
     */
    @FXML
    void Repair_Button_Pressed(ActionEvent event) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Drawer.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("RepairList.fxml"));
            Parent root2 = (Parent) fxmlloader.load();
            fxmlloader.<Drawer>getController().setActive("Repair");
            fxmlloader.<Drawer>getController().changeBg();
            fxmlloader.<Drawer>getController().setParent(this);
            Border_Pane.setCenter(root);
            Border_Pane.setLeft(root2);
            new FadeIn(root).play();
            new FadeIn(root2).play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method opens the Refill Window
     *
     * @param event The event when an action id made
     */
    @FXML
    void Refill_Button_Pressed(ActionEvent event) {
        try {

            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Drawer.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("RefillList.fxml"));
            Parent root2 = (Parent) fxmlloader.load();
            fxmlloader.<Drawer>getController().setActive("Gas");
            fxmlloader.<Drawer>getController().changeBg();
            fxmlloader.<Drawer>getController().setParent(this);
            Border_Pane.setCenter(root);
            Border_Pane.setLeft(root2);
            new FadeIn(root).play();
            new FadeIn(root2).play();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method Closes the program
     *
     * @param event The event when the action is made
     */
    @FXML
    void Exit_Button_Pressed(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
        System.exit(1);
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
        System.exit(0);
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
        Border_Pane.getScene().getWindow().setX(event.getScreenX() - x_Offset);
        Border_Pane.getScene().getWindow().setY(event.getScreenY() - y_Offset);
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


    /*@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Drawer.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("DrawerTop.fxml"));
            Parent root2 = (Parent) fxmlloader.load();
            fxmlloader.<Drawer>getController().setActive("Trucks");
            fxmlloader.<Drawer>getController().changeBg();
            fxmlloader.<Drawer>getController().setParent(this);
            Border_Pane.setCenter(root);
            Border_Pane.setLeft(root2);
            new FadeIn(root).play();
            new FadeIn(root2).play();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/
}
