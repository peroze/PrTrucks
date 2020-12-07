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
    private int[] days;
    int kmBs;

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("Drawer.fxml"));
        Parent root  = (Parent) fxmlloader.load();
        fxmlloader.<Drawer>getController().setActive("Kteo");
        fxmlloader.<Drawer>getController().changeBg();
        fxmlloader.<Drawer>getController().Trucks_Button_Pressed(null);
        primaryStage.setTitle("PrTrucks");
        primaryStage.setScene(new Scene(root, 1064, 675));
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
        days=new int[3];
        int flg=3;
        int arPos=0;
        while(flg<8) {
            if (chks[flg].equals("0.0")) {
                days[arPos] = 1;
            } else if (chks[flg].equals("33.3")) {
                days[arPos] = 7;
            } else if (chks[flg].equals("66.6")) {
                days[arPos] = 14;
            } else {
                days[arPos] = 30;
            }
            flg=flg+2;
            arPos++;
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

        switch (chks[8]){
            case "0.0":
                kmBs=1000;
                break;
            case "33.3":
                kmBs=2000;
                break;
            case "66.6":
                kmBs=4000;
                break;
            case "100.0":
                kmBs=8000;
                break;
        }
        System.out.println(chks[8]);
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(0.15));
        Thread.sleep(500);
        if (chks[4].equals("true")) {
            rs = sql.Query_General_KTEO();
            Tray(rs, "KTEO",sql);
        }
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(0.20));
        Thread.sleep(500);
        if (chks[6].equals("true")) {
            rs = sql.Query_General_EmmisionCard();
            Tray(rs, "Emmision",sql);
        }
        if (chks[2].equals("true")) {
            rs = sql.Query_Group_Service();
            Tray(rs, "Service",sql);
            sql.Disconnect();
        }
        LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(0.25));
        Thread.sleep(500);
        if (chks[0].equals("true")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(chks[9]);
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
                FS.Write(chks[0]+"~"+chks[1]+"~"+chks[2]+"~"+chks[3]+"~"+chks[4]+"~"+chks[5]+"~"+chks[6]+"~"+chks[7]+"~"+chks[8]+"~"+sdf.format(date2));
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
        LauncherImpl.launchApplication(Main.class,PreLoader.class,args);
        //launch(args);
    }

    public void Tray(ResultSet rs, String Type,Sql db) {
        ArrayList<String> LiscsToDo = new ArrayList<>();
        ArrayList<String> LiscsExp = new ArrayList<>();
        ArrayList<String> LiscKmToDo= new ArrayList<>();
        ArrayList<String> LiscKmExp=new ArrayList<>();
        int corDays=-1;
        try {
            while (rs.next()) {
                String next = "";
                rs.getString("id");
                if (Type.equals("Service")) {
                    corDays=days[0];
                    next = rs.getString("Next_Date"); // The collumn of service is different because each car have many services stored but we only need the latest
                } else if (Type.equals("KTEO")) {
                    corDays=days[1];
                    next = rs.getString("DateNext");
                } else {
                    corDays=days[2];
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
                if (diff < corDays) {
                    LiscsToDo.add(sql.GetLisxxFromId(Lisc));
                }
                if (multi == -1) {
                    LiscsExp.add(sql.GetLisxxFromId(Lisc));
                }
                if(Type.equals("Service")){
                    ResultSet rs1=db.Query_Specific_NextServiceKmCurrentKm(Lisc);
                    int CurrKm=rs1.getInt("Kilometers");
                    int ServKm=rs1.getInt("MAX(Next_Kilometers)");
                    if(ServKm-CurrKm<kmBs&&!(ServKm<CurrKm)){
                        System.out.println(Lisc);
                        LiscKmToDo.add(sql.GetLisxxFromId(Lisc));
                    }
                    else if(ServKm<CurrKm){
                        LiscKmExp.add(sql.GetLisxxFromId(Lisc));
                    }
                }
            }

            if (SystemTray.isSupported()) {
                try {
                    if (!(LiscsToDo.isEmpty() || LiscsToDo == null)) {
                        this.displayTray(LiscsToDo, Type, 1,corDays);
                    }
                    if (!(LiscsExp.isEmpty() || LiscsExp == null)) {
                        this.displayTray(LiscsExp, Type, -1,-1);
                    }
                    if (!(LiscKmToDo.isEmpty() || LiscKmToDo == null)) {
                        this.displayTray(LiscKmToDo, Type+"Km", 1,corDays);
                    }
                    if (!(LiscKmExp.isEmpty() || LiscKmExp == null)) {
                        this.displayTray(LiscsExp, Type+"Km", -1,-1);
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

    public void displayTray(ArrayList<String> Liscs, String Type, int error,int Days) throws AWTException, MalformedURLException {

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
        String text1;
        String text2;
        if(Type.equals("ServiceKm")){
             text1="σε λιγότερες από "+Days+"μέρες:\n";
             text2="Προσοχή εχεί περάσθει το όριο Χιλιομέτρων για ";
             Type="Service";
        }
        else{
            text1=" σε λιγότερες από "+Days+" μέρες:\n";
            text2="Πρόσοχη εχεί περάσει η ημερομινία για ";
        }
        if (error == 1) {
            trayIcon.displayMessage("Ειδοποίηση " + Type, "Τα Ακόλουθα οχήματα πρέπει να κάνουν " + Type + text1 + Cars, TrayIcon.MessageType.INFO);
        } else {
            trayIcon.displayMessage("Ειδοποίηση " + Type, text2 + Type + " στα ακόλουθα οχήματα:\n" + Cars, TrayIcon.MessageType.ERROR);
        }
    }
}

