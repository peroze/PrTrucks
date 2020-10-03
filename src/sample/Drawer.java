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
        private Button Speed_Button;

        @FXML
        void Gas_Button_Pressed(ActionEvent event) {
            resetBg();
            Active="Gas";
            changeBg();
        }

        @FXML
        void Kteo_Button_Pressed(ActionEvent event) {
            resetBg();
            Active="Kteo";
            changeBg();
        }

        @FXML
        void Service_Button_Pressed(ActionEvent event) {
            if(!Active.equals("Service")) {
                resetBg();
                Active="Service";
                changeBg();
                /*try {
                    Parent root = FXMLLoader.load(getClass().getResource("ServiceList.fxml"));
                    Parent.getBorder_Pane().setCenter(root);
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }

        }

        @FXML
        void Speed_Button_Pressed(ActionEvent event) {
            resetBg();
            Active="Speed";
            changeBg();
        }

        @FXML
        void Trucks_Button_Pressed(ActionEvent event) {
            if(!Active.equals("Trucks")) {
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


        public void setActive(String active) {
            Active = active;
         }

         public void resetBg(){
             switch (Active){
                 case "Trucks":
                     Trucks_Button.setStyle(null);
                     break;
                 case "Gas" :
                     Gas_Button.setStyle(null);
                     break;
                 case "Speed":
                     Speed_Button.setStyle(null);
                     break;
                 case "Kteo":
                     Kteo_Button.setStyle(null);
                     break;
                 case"Service":
                     Service_Button.setStyle(null);
                     break;
             }
         }

        public void changeBg(){
            switch (Active){
                case "Trucks":
                    Trucks_Button.setStyle("-fx-background-color: #686466");
                    break;
                case "Gas" :
                    Gas_Button.setStyle("-fx-background-color: #686466");
                    break;
                case "Speed":
                    Speed_Button.setStyle("-fx-background-color: #686466");
                    break;
                case "Kteo":
                    Kteo_Button.setStyle("-fx-background-color: #686466");
                    break;
                case"Service":
                    Service_Button.setStyle("-fx-background-color: #686466");
                    break;
            }
    }

    public void setParent(Main_Menu parent) {
        Parent = parent;
    }
}
