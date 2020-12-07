package Pr.Cars;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import org.kordamp.ikonli.javafx.FontIcon;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This Class is the controller of the main Menu
 *
 * @author peroze
 * @version 1.0 Alpha
 */
public class SplashScreen implements Initializable {

    @FXML
    private BorderPane Border_Pane;

    @FXML
    private ProgressBar PrBr;

    @FXML
    private FontIcon github;

    @FXML
    private FontIcon facebook;

    @FXML
    private FontIcon instagram;

    public static ProgressBar PgBr;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PgBr = PrBr;
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

