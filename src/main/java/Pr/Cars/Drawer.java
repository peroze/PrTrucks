package Pr.Cars;

import animatefx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Drawer {
    private String Active;
    private double x_Offset = 0;
    private double y_Offset = 0;


    @FXML
    private VBox Drawer;

    @FXML
    private Button Trucks_Button;

    @FXML
    private Button EmmisionCard_Button;

    @FXML
    private Button Kteo_Button;

    @FXML
    private Button Service_Button;

    @FXML
    private Button Repair_Button;

    @FXML
    private Button Refill_Button;

    @FXML
    private Button Transfer_Button;

    @FXML
    private Button More_Button;

    @FXML
    private BorderPane  Panel;

    @FXML
    private FontIcon github;

    @FXML
    private FontIcon facebook;

    @FXML
    private FontIcon instagram;

    /**
     * This method is pressed when the Gas Button is pressed and it opens the gas Table in the main scene
     *
     * @param event The event
     */
    @FXML
    void EmmisionCard_Button_Pressed(ActionEvent event) {
        if (!Active.equals("EmmisionCard")) {
            resetBg();
            Active = "EmmisionCard";
            changeBg();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("EmmisionCardList.fxml"));
                Panel.setCenter(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is called when the KTEO button is pressed and it opens the KTEO table in the main window
     *
     * @param event The event
     */
    @FXML
    void Kteo_Button_Pressed(ActionEvent event) {
        if (!Active.equals("Kteo")) {
            resetBg();
            Active = "Kteo";
            changeBg();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("KTEOList.fxml"));
                Panel.setCenter(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is called when the Service button is pressed and it opens the Service table in the main window
     *
     * @param event The event
     */
    @FXML
    void Service_Button_Pressed(ActionEvent event) {
        if (!Active.equals("Service")) {
            resetBg();
            Active = "Service";
            changeBg();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("ServiceList.fxml"));
                Panel.setCenter(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * This method is called when the Repair button is pressed and it opens the Repair table in the main window
     *
     * @param event The event
     */
    @FXML
    void Repair_Button_Pressed(ActionEvent event) {
        if (!Active.equals("Repair")) {
            resetBg();
            Active = "Repair";
            changeBg();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("RepairList.fxml"));
                Panel.setCenter(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void Refill_Button_Pressed(ActionEvent event) {
        if (!Active.equals("Refill")) {
            resetBg();
            Active = "Refill";
            changeBg();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("RefillList.fxml"));
                Panel.setCenter(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is called when the Trucks button is pressed and it opens the Trucks table in the main window
     *
     * @param event The event
     */
    @FXML
    void Trucks_Button_Pressed(ActionEvent event) {
        if (!Active.equals("Trucks")) {
            resetBg();
            Active = "Trucks";
            changeBg();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("TrucksList.fxml"));
                Panel.setCenter(root);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void Transfer_Button_Pressed(ActionEvent event) {
        if (!Active.equals("Transfer")) {
            resetBg();
            Active = "Transfer";
            changeBg();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("DrawerTopComp.fxml"));
                Panel.setCenter(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void More_Button_Pressed(ActionEvent event) {
        if (!Active.equals("More")) {
            resetBg();
            Active = "More";
            changeBg();
            try {

                Parent root = FXMLLoader.load(getClass().getResource("DrawerTop.fxml"));
                Panel.setCenter(root);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * This method changes the Active variable which indicates which page is currently on the main frame
     * @param active The active page
     */
    public void setActive(String active) {
        Active = active;
    }

    /**
     * This method returns the original background at the active button (The active haw a different colour)
     */
    public void resetBg() {
        switch (Active) {
            case "Trucks":
                Trucks_Button.setStyle(null);
                break;
            case "EmmisionCard":
                EmmisionCard_Button.setStyle(null);
                break;
            case "Repair":
                Repair_Button.setStyle(null);
                break;
            case "Kteo":
                Kteo_Button.setStyle(null);
                break;
            case "Service":
                Service_Button.setStyle(null);
                break;
            case "Refill":
                Refill_Button.setStyle(null);
                break;
            case "More":
                More_Button.setStyle(null);
                break;
            case "Transfer":
                Transfer_Button.setStyle(null);
                break;
        }
    }

    /**
     * THis method changes the Background of the Active button (It must be different than the others )
     */
    public void changeBg() {
        switch (Active) {
            case "Trucks":
                Trucks_Button.setStyle("-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0 0 0 3; -fx-border-color: #FA8072;-fx-background-color: grey; -fx-text-fill: white;");
                break;
            case "EmmisionCard":
                EmmisionCard_Button.setStyle("-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0 0 0 3; -fx-border-color: #FA8072;-fx-background-color: grey; -fx-text-fill: white;");
                break;
            case "Repair":
                Repair_Button.setStyle("-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0 0 0 3; -fx-border-color: #FA8072;-fx-background-color: grey; -fx-text-fill: white;");
                break;
            case "Kteo":
                Kteo_Button.setStyle("-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0 0 0 3; -fx-border-color: #FA8072;-fx-background-color: grey; -fx-text-fill: white;");
                break;
            case "Service":
                Service_Button.setStyle("-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0 0 0 3; -fx-border-color: #FA8072;-fx-background-color: grey; -fx-text-fill: white;");
                break;
            case "Refill":
                Refill_Button.setStyle("-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0 0 0 3; -fx-border-color: #FA8072;-fx-background-color: grey; -fx-text-fill: white;");
                break;
            case "More":
                More_Button.setStyle("-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0 0 0 3; -fx-border-color: #FA8072;-fx-background-color: grey; -fx-text-fill: white;");
                break;
            case "Transfer":
                Transfer_Button.setStyle("-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0 0 0 3; -fx-border-color: #FA8072;-fx-background-color: grey; -fx-text-fill: white;");
                break;
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
     * This method is used to minimize the Window
     *
     * @param event This it the given event
     */
    @FXML
    void Minimize_Button_Pressed(MouseEvent event) {
        Stage stage = (Stage) ((FontIcon) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void Social(MouseEvent event) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            try {
                switch (((FontIcon)event.getSource()).getId()){
                    case "github":
                        Desktop.getDesktop().browse(new URI("https://github.com/peroze"));
                        break;
                    case "facebook":
                        Desktop.getDesktop().browse(new URI("https://www.facebook.com/nperoze"));
                        break;
                    case "instagram":
                        Desktop.getDesktop().browse(new URI("https://www.instagram.com/nperoze"));
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}
