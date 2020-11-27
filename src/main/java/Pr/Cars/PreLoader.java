package Pr.Cars;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class PreLoader extends Preloader {
    Stage Stage;
    Scene scene;
    private int before ;

    @Override
    public void init(){
        try {
            before=0;
            Parent root= FXMLLoader.load(getClass().getResource("SplashScreen.fxml"));
            scene=new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage=primaryStage;
        Stage.setScene(scene);
        Stage.initStyle(StageStyle.UNDECORATED);
        Stage.show();
    }

    @Override
    public void handleApplicationNotification(Preloader.PreloaderNotification indo) {
        SplashScreen.PgBr.setProgress(((ProgressNotification) indo).getProgress());
    }

    @Override
    public void handleStateChangeNotification(Preloader.StateChangeNotification info) {
        StateChangeNotification.Type type = info.getType();
        switch (type) {
            case BEFORE_START:
                Stage.hide();
                break;
        }
    }
}
