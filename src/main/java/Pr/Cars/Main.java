package Pr.Cars;


import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
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

        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        primaryStage.setTitle("PrTrucks");
        primaryStage.setScene(new Scene(root, 1007, 675));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    @Override
    public void init() throws Exception{
        Sql sql = new Sql();
        double count=0;
        double pr=0.5;
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
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(0.15));
        Thread.sleep(500);
        if (chks[3].equals("true")) {
            rs = sql.Query_General_KTEO();
            Tray(rs, "KTEO");
        }
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(0.20));
        Thread.sleep(500);
        if (chks[2].equals("true")) {
            rs = sql.Query_Group_Service();
            Tray(rs, "Service");
        }
        if (chks[4].equals("true")) {
            rs = sql.Query_General_EmmisionCard();
            Tray(rs, "Emmision");
        }
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(0.25));
        Thread.sleep(500);
        if (chks[0].equals("true")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(chks[6]);
            Date date2 = new Date();
            long tempDiffInMillies = date.getTime() - date2.getTime(); //This Variable is used in order to find out which date is later of the two (if > 0 date > date1)
            long diffInMillies = Math.abs(date.getTime() - date2.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if (diff >= daysBc) {
                Backup bc=new Backup();
                bc.CreateBackupService();
                LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(0.30));
                Thread.sleep(500);
                bc.CreateBackupRefill();
                LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(0.35));
                Thread.sleep(500);
                bc.CreateBackupKteo();
                LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(0.40));
                Thread.sleep(500);
                bc.CreateBackupEmmision();
                LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(0.45));
                Thread.sleep(500);
                bc.CreateBackupRepair();
                FS.Write(chks[0]+"~"+chks[1]+"~"+chks[2]+"~"+chks[3]+"~"+chks[4]+"~"+chks[5]+"~"+sdf.format(date2));
                LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(0.50));
            }
        }
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(0.50));
        Thread.sleep(500);
        for (count=0;count<1100000;count++){
               if(count==0||count==100000||count==200000||count==300000||count==4000000||count==500000||count==600000||count==700000||count==800000||count==900000||count==1000000) {
                   pr=pr+0.05;
                   LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(pr));
                   Thread.sleep(500);
               }
       }
    }

    public static void main(String[] args) {
       //LauncherImpl.launchApplication(Main.class,PreLoader.class,args);
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

