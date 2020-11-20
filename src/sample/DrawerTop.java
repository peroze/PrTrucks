package sample;
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

public class DrawerTop implements Initializable {
    private String Active;
    private String Active2;

    @FXML
    private MenuButton Totals_Button;

    @FXML
    private MenuItem TotalExpenses_Button;

    @FXML
    private MenuItem ByCarExp_Button;

    @FXML
    private MenuButton Fuel_Button;

    @FXML
    private MenuItem TotalFuel_Button;

    @FXML
    private MenuItem ByCarFuel_Button;

    @FXML
    private MenuButton Backup_Button;

    @FXML
    private BorderPane OutPane;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Active = "Expenses";
        Active2="Total";
        changeBg();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ExpensesTotal.fxml"));
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
    void Backup_Button_Pr(MouseEvent event) {
        if (!(Active.equals("Settings"))) {
            resetBg();
            Active = "Settings";
            changeBg();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Settings.fxml"));
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
    void ByCarExp_Button_Pr(ActionEvent event) {
        if (!(Active.equals("Expenses")&&Active2.equals("ByCar"))) {
            resetBg();
            Active2="ByCar";
            Active = "Expenses";
            changeBg();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("ExpensesTotalByCar.fxml"));
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
    void ByCarFuel_Button_Pr(ActionEvent event) {
        if (!(Active.equals("Fuel")&&Active2.equals("ByCar"))) {
            resetBg();
            Active2="ByCar";
            Active = "Fuel";
            changeBg();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("LitersTotalByCar.fxml"));
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
    void TotalExpenses_Button_Pr(ActionEvent event) {
        if (!(Active.equals("Expenses")&&Active2.equals("Total"))) {
            resetBg();
            Active2="Total";
            Active = "Expenses";
            changeBg();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("ExpensesTotal.fxml"));
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
    void TotalFuel_Button_Pr(ActionEvent event) {
        if (!(Active.equals("Fuel")&&Active2.equals("Total"))) {
            resetBg();
            Active2="Total";
            Active = "Fuel";
            changeBg();
            try {
                Parent root = FXMLLoader.load(getClass().getResource("LitersTotal.fxml"));
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
            case "Expenses":
                Totals_Button.setStyle(null);
                break;
            case "Fuel":
                Fuel_Button.setStyle(null);
                break;
            case"Settings":
                Backup_Button.setStyle(null);
        }
    }

    /**
     * This method changes the Background of the Active button (It must be different than the others )
     */
    public void changeBg() {
        switch (Active) {
            case "Expenses":
                Totals_Button.setStyle("-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0 0 3 0; -fx-border-color: #FA8072;-fx-background-color: grey; -fx-text-fill: white;");
                break;
            case "Fuel":
                Fuel_Button.setStyle("-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0 0 3 0; -fx-border-color: #FA8072;-fx-background-color: grey; -fx-text-fill: white;");
                break;
            case "Settings":
                Backup_Button.setStyle("-fx-background-radius: 0; -fx-border-radius: 0; -fx-border-width: 0 0 3 0; -fx-border-color: #FA8072;-fx-background-color: grey; -fx-text-fill: white;");
                break;
        }
    }

}
