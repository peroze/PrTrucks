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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Sql sql = new Sql();
        ResultSet rs = sql.Query_General_KTEO();
        Tray(rs, "KTEO");
        rs = sql.Query_Group_Service();
        Tray(rs, "Service");

        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        primaryStage.setTitle("PrTrucks");
        primaryStage.setScene(new Scene(root, 944, 675));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public void CheckDate() {
        Sql Sql = new Sql();

    }


    public static void main(String[] args) {
        launch(args);
    }

    public void Tray(ResultSet rs, String Type) {
        ArrayList<String> LiscsToDo = new ArrayList<>();
        ArrayList<String> LiscsExp = new ArrayList<>();

        try {
            while (rs.next()) {
                String next = "";
                rs.getString("id");
                if (Type.equals("Service")) {
                    next = rs.getString("MAX(Next_Date)");
                } else {
                    next = rs.getString("DateNext");
                }
                String Lisc = rs.getString("id");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(next);
                Date date2 = new Date();
                long diffInMillies = Math.abs(date.getTime() - date2.getTime());
                int multi = 1;
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                if (diffInMillies < 0) {
                    multi = -1;
                }
                Sql sql = new Sql();
                if (diff < 15) {

                    LiscsToDo.add(sql.GetLisxxFromId(Lisc));

                }
                if (multi == -1) {
                    LiscsExp.add(sql.GetLisxxFromId(Lisc));
                }


            }
            if (SystemTray.isSupported()) {
                try {
                    if (!(LiscsToDo.isEmpty() || LiscsToDo == null)) {
                        this.displayTray(LiscsToDo, Type, 1);
                    }
                    if (!(LiscsExp.isEmpty() || LiscsExp == null)) {
                        this.displayTray(LiscsExp, Type, -1);
                    }
                } catch (AWTException ex) {

                } catch (MalformedURLException ex) {

                }
            } else {
                System.err.println("System tray not supported!");
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void displayTray(ArrayList<String> Liscs, String Type, int error) throws AWTException, MalformedURLException {

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
        String Cars = "";
        for (int i = 0; i < Liscs.size(); i++) {
            Cars = Cars.concat(Liscs.get(i) + "\n");
        }
        if (error == 1) {
            trayIcon.displayMessage("Ειδοποίηση " + Type, "Τα Ακόλουθα οχήματα πρέπει να κάνουν " + Type + " σε λιγότερες από 15 μέρες:\n" + Cars, TrayIcon.MessageType.INFO);
        } else {
            trayIcon.displayMessage("Ειδοποίηση " + Type, "Πρόσοχη εχεί περάσει η ημερομινία για " + Type + " στα ακόλουθα οχήματα:\n" + Cars, TrayIcon.MessageType.ERROR);
        }
    }
}

