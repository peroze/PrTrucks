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

import java.awt.*;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       /* if (SystemTray.isSupported()) {
            try{
                ArrayList<String> Liscs=new ArrayList<>();
                Liscs.add("τεστ 1574");
                this.displayTray(Liscs,"Service");
            }catch(AWTException ex){

            }catch(MalformedURLException ex){

            }
        } else {
            System.err.println("System tray not supported!");
        }*/
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        primaryStage.setTitle("PrTrucks");
        primaryStage.setScene(new Scene(root, 944, 675));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public void CheckDate(){
        Sql Sql=new Sql();

    }


    public static void  main(String[] args)
    {
        launch(args);
    }

    public void displayTray(ArrayList<String> Liscs,String Type) throws AWTException, MalformedURLException {

        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Java AWT Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
        String Cars="";
        for(int i=0;i<Liscs.size();i++){
            Cars=Cars.concat(Liscs.get(i)+"\n");
        }
        trayIcon.displayMessage("Ειδοποίηση "+Type, "Τα Ακόλουθα οχήματα πρέπει να κάνουν "+Type+" σε λιγότερες από 15 μέρες\n:"+Cars, TrayIcon.MessageType.INFO);
    }
}

