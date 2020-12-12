package Pr.Cars;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DrawerTopComp implements Initializable {
    private String Active;

    @FXML
    private BorderPane OutPane;

    @FXML
    private MenuItem Totals_Button;

    @FXML
    private MenuItem Cars_Button;

    @FXML
    private MenuButton Transfer;

    @FXML
    private MenuButton Partners;

    @FXML
    private MenuButton Employers;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Active = "Partners";
        changeBg();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ExternalPhoneList.fxml"));
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    OutPane.requestFocus();
                }
            });
            OutPane.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Cars_Button_Pressed(ActionEvent event) {
        if (!(Active.equals("Cars"))) {
            resetBg();
            Active = "Cars";
            changeBg();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("ExternalCarsList.fxml"));
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        OutPane.requestFocus();
                    }
                });
                OutPane.setCenter(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void Totals_Button_Pressed(ActionEvent event) {
        if (!(Active.equals("Companies"))) {
            resetBg();
            Active = "Companies";
            changeBg();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("CompanyList.fxml"));
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        OutPane.requestFocus();
                    }
                });
                OutPane.setCenter(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void Partners_Button_Pressed(MouseEvent event)  {
        if (!(Active.equals("Partners"))) {
            resetBg();
            Active = "Partners";
            changeBg();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("ExternalPhoneList.fxml"));
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        OutPane.requestFocus();
                    }
                });
                OutPane.setCenter(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void Employers_Button_Pressed(MouseEvent event) {
        if (!(Active.equals("Employers"))) {
            resetBg();
            Active = "Employers";
            changeBg();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("InternalPhoneList.fxml"));
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        OutPane.requestFocus();
                    }
                });
                OutPane.setCenter(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * This method changes the Active variable which indicates which page is currently on the main frame
     *
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
            case "Companies":
                Transfer.setStyle(null);
                break;
            case "Cars":
                Transfer.setStyle(null);
                break;
            case "Partners":
                Partners.setStyle(null);
                break;
            case "Employers":
                Employers.setStyle(null);
                break;
        }
    }

    /**
     * This method changes the Background of the Active button (It must be different than the others )
     */
    public void changeBg() {
        switch (Active) {
            case "Companies":
                Transfer.setStyle("-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0 0 3 0; -fx-border-color: #FA8072;-fx-background-color: grey; -fx-text-fill: white;");
                break;
            case "Cars":
                Transfer.setStyle("-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0 0 3 0; -fx-border-color: #FA8072;-fx-background-color: grey; -fx-text-fill: white;");
                break;
            case "Partners":
                Partners.setStyle("-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0 0 3 0; -fx-border-color: #FA8072;-fx-background-color: grey; -fx-text-fill: white;");
                break;
            case "Employers":
                Employers.setStyle("-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0 0 3 0; -fx-border-color: #FA8072;-fx-background-color: grey; -fx-text-fill: white;");
                break;
        }
    }

}
