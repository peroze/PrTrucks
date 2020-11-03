package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Drawer {
    private String Active;
    Main_Menu Parent;


    @FXML
    private VBox Drawer;

    @FXML
    private Button Trucks_Button;

    @FXML
    private Button Gas_Button;

    @FXML
    private Button Kteo_Button;

    @FXML
    private Button Service_Button;

    @FXML
    private Button Repair_Button;

    /**
     * This method is pressed when the Gas Button is pressed and it opens the gas Table in the main scene
     *
     * @param event The event
     */
    @FXML
    void Gas_Button_Pressed(ActionEvent event) {
        if (!Active.equals("Gas")) {
            resetBg();
            Active = "Gas";
            changeBg();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("EmmisionCardList.fxml"));
                Parent.getBorder_Pane().setCenter(root);
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
                Parent.getBorder_Pane().setCenter(root);
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
                Parent.getBorder_Pane().setCenter(root);
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
                Parent.getBorder_Pane().setCenter(root);
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
                Parent.getBorder_Pane().setCenter(root);
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
            case "Gas":
                Gas_Button.setStyle(null);
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
        }
    }

    /**
     * THis method changes the Background of the Active button (It must be different than the others )
     */
    public void changeBg() {
        switch (Active) {
            case "Trucks":
                Trucks_Button.setStyle("-fx-background-color: #686466");
                break;
            case "Gas":
                Gas_Button.setStyle("-fx-background-color: #686466");
                break;
            case "Repair":
                Repair_Button.setStyle("-fx-background-color: #686466");
                break;
            case "Kteo":
                Kteo_Button.setStyle("-fx-background-color: #686466");
                break;
            case "Service":
                Service_Button.setStyle("-fx-background-color: #686466");
                break;
        }
    }

    /**
     * This method adds the parent window (Main Menu)
     * @param parent The parent Window
     */
    public void setParent(Main_Menu parent) {
        Parent = parent;
    }
}
