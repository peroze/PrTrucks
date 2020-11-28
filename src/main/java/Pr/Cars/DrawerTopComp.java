package Pr.Cars;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuButton;
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
    private MenuButton Totals_Button;

    @FXML
    private MenuButton Cars_Button;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

    @FXML
    void Cars_Button_Pressed(MouseEvent event) {
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
    void Totals_Button_Pressed(MouseEvent event) {
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
                Totals_Button.setStyle(null);
                break;
            case "Cars":
                Cars_Button.setStyle(null);
                break;
        }
    }

    /**
     * This method changes the Background of the Active button (It must be different than the others )
     */
    public void changeBg() {
        switch (Active) {
            case "Companies":
                Totals_Button.setStyle("-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0 0 3 0; -fx-border-color: #FA8072;-fx-background-color: grey; -fx-text-fill: white;");
                break;
            case "Cars":
                Cars_Button.setStyle("-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0 0 3 0; -fx-border-color: #FA8072;-fx-background-color: grey; -fx-text-fill: white;");
                break;
        }
    }

}
