package sample;

import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class Main_Menu {

    private double x_Offset=0;
    private double y_Offset=0;


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
    private Button Speed_Button;

    @FXML
    private Button Exit_Button;

    @FXML
    private BorderPane Border_Pane;


    public BorderPane getBorder_Pane() {
        return Border_Pane;
    }

    @FXML
    void Cars_Button_Pressed(ActionEvent event) {
        try {

            FXMLLoader fxmlloader=new FXMLLoader(getClass().getResource("Drawer.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("TrucksList.fxml"));
            Parent root2=(Parent)fxmlloader.load();
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

    @FXML
    void Exit_Button_Pressed(ActionEvent event) {
        Stage stage= (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.close();


    }

    @FXML
    void Gas_Button_Pressed(ActionEvent event) {

    }

    @FXML
    void Kteo_Button_Pressed(ActionEvent event) {

    }

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
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Speed_Button_Pressed(ActionEvent event) {

    }

    @FXML
    void X_Button_Pressed(MouseEvent event) {
        Stage stage= (Stage)((ImageView)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void All_Hover(MouseEvent event) {

    }

    @FXML
    void Top_Bar_Dragged(MouseEvent event) {
        ((Stage)((HBox)event.getSource()).getScene().getWindow()).setX(event.getSceneX()+x_Offset);
        ((Stage)((HBox)event.getSource()).getScene().getWindow()).setY(event.getSceneY()+y_Offset);
    }

    @FXML
    void Top_Bar_Pressed(MouseEvent event) {
        x_Offset= ((Stage)((HBox)event.getSource()).getScene().getWindow()).getX()-event.getSceneX();
        y_Offset= ((Stage)((HBox)event.getSource()).getScene().getWindow()).getY()-event.getSceneY();
    }

    @FXML
    void Minimize_Button_Pressed(MouseEvent event) {
        Stage stage= (Stage)((ImageView)event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }



}
