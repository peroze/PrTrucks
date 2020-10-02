package sample;

import animatefx.animation.BounceIn;
import animatefx.animation.FadeIn;
import animatefx.animation.Jello;
import animatefx.animation.RollIn;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        primaryStage.setTitle("PrTrucks");
        primaryStage.setScene(new Scene(root, 814, 487));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
