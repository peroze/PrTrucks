package Pr.Cars;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
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

        public static ProgressBar PgBr;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
       PgBr=PrBr;
    }
}

