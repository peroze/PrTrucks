package sample;


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


/**
 * The main class of the program
 *
 * @author peroze
 * @version 1.0 Alpha
 */
public class Main extends Application {
    private int days;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Sql sql = new Sql();
        ResultSet rs;
        FileManagment FS = new FileManagment();
        String[] chks = FS.Read();
        int daysBc;
        if (chks[5].equals("0.0")) {
            days = 1;
        } else if (chks[5].equals("33.3")) {
            days = 7;
        } else if (chks[5].equals("66.6")) {
            days = 14;
        } else {
            days = 30;
        }
        if (chks[1].equals("0.0")) {
            daysBc = 1;
        } else if (chks[1].equals("33.3")) {
            daysBc = 7;
        } else if (chks[1].equals("66.6")) {
            daysBc = 14;
        } else {
            daysBc = 30;
        }
        if (chks[3].equals("true")) {
            rs = sql.Query_General_KTEO();
            Tray(rs, "KTEO");
        }
        if (chks[2].equals("true")) {
            rs = sql.Query_Group_Service();
            Tray(rs, "Service");
        }
        if (chks[4].equals("true")) {
            rs = sql.Query_General_EmmisionCard();
            Tray(rs, "Emmision");
        }
        if (chks[0].equals("true")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(chks[6]);
            Date date2 = new Date();
            long tempDiffInMillies = date.getTime() - date2.getTime(); //This Variable is used in order to find out which date is later of the two (if > 0 date > date1)
            long diffInMillies = Math.abs(date.getTime() - date2.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if (diff >= daysBc) {
                System.out.println("To Do Backup DB to ExcelFile");
            }
        }
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        primaryStage.setTitle("PrTrucks");
        primaryStage.setScene(new Scene(root, 1007, 675));
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
                    next = rs.getString("MAX(Next_Date)"); // The collumn of service is different because each car have many services stored but we only need the latest
                } else if (Type.equals("KTEO")) {
                    next = rs.getString("DateNext");
                } else {
                    next = rs.getString("NextDate");
                }
                String Lisc = rs.getString("id");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(next);
                Date date2 = new Date();
                long tempDiffInMillies = date.getTime() - date2.getTime(); //This Variable is used in order to find out which date is later of the two (if > 0 date > date1)
                long diffInMillies = Math.abs(date.getTime() - date2.getTime());
                int multi = 1;
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                if (tempDiffInMillies < 0) {
                    multi = -1;
                }
                Sql sql = new Sql();
                if (diff < days) {

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
        if (Type.equals("Emmision")) {
            Type = "Κάρτα Ελέγχου Καυσαεριών";
        }
        if (error == 1) {
            trayIcon.displayMessage("Ειδοποίηση " + Type, "Τα Ακόλουθα οχήματα πρέπει να κάνουν " + Type + " σε λιγότερες από 15 μέρες:\n" + Cars, TrayIcon.MessageType.INFO);
        } else {
            trayIcon.displayMessage("Ειδοποίηση " + Type, "Πρόσοχη εχεί περάσει η ημερομινία για " + Type + " στα ακόλουθα οχήματα:\n" + Cars, TrayIcon.MessageType.ERROR);
        }
    }
}

