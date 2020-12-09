package Pr.Cars;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;


public class ExpensesTotalByCar implements Initializable {

    private double x_Offset = 0;
    private double y_Offset = 0;


    @FXML
    private ComboBox Type;

    @FXML
    private HBox Top_Bar;

    @FXML
    private ImageView Minimize_Button;

    @FXML
    private ImageView X_Button;

    @FXML
    private AnchorPane Totals_Pane;

    @FXML
    private ComboBox Distance;

    @FXML
    private TableView<ModelTotal> Table1;

    @FXML
    private TableColumn<ModelTotal, String> Date;

    @FXML
    private TableColumn<ModelTotal, String> Amount;

    @FXML
    private DatePicker From;

    @FXML
    private DatePicker To;

    @FXML
    private TextField Pyear;

    @FXML
    private ComboBox Lisc;

    @FXML
    private Label Lisc_Label;

    @FXML
    private Label Dist_Label;

    @FXML
    private Label Type_Label;

    @FXML
    private Label Year_Label;

    @FXML
    private Label To_Label;

    @FXML
    private Label From_Label;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Sql db = new Sql();
        ObservableList<String> oblist = FXCollections.observableArrayList();
        Date.setCellValueFactory(new PropertyValueFactory<>("Period"));
        Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        oblist.add("Τελευταία 5 Έτοι");
        oblist.add("Πρωηγούμενο Έτος");
        oblist.add("Τρέχων Έτος");
        oblist.add("Συγκεκριμένο Έτος");
        oblist.add("Πρωηγούμενος Μήνας");
        oblist.add("Τρέχων Μήνας");
        oblist.add("Άλλο Διάστημα");
        Distance.setItems(oblist);
        ObservableList<String> oblist2 = FXCollections.observableArrayList();
        oblist2.add("Service");
        oblist2.add("Eπισκευές");
        oblist2.add("ΚΤΕΟ");
        oblist2.add("Καύσιμα");
        oblist2.add("Όλα");
        Type.setItems(oblist2);
        ResultSet rs = db.Query_All_Lisc();
        ObservableList<String> oblist3 = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                oblist3.add(rs.getString("LiscPlate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Lisc.setItems(oblist3);
        To.setVisible(false);
        From.setVisible(false);
        Pyear.setVisible(false);
        Distance.setOnAction(e -> {
            Show_Rest();
        });
        ObservableList<ModelTotal> tempPlaceboo=FXCollections.observableArrayList();
        tempPlaceboo.add(new ModelTotal("","",""));
        Table1.setItems(tempPlaceboo);
    }


    @FXML
    void Show_Button_Pr(ActionEvent event) {

        try {
            boolean flag1=false;
            Type.setStyle(null);
            Type_Label.setVisible(false);
            Lisc.setStyle(null);
            Lisc_Label.setVisible(false);
            Distance.setStyle(null);
            Dist_Label.setVisible(false);
            From.setStyle(null);
            From_Label.setVisible(false);
            To.setStyle(null);
            To_Label.setVisible(false);
            Pyear.setStyle(null);
            Year_Label.setVisible(false);
            if (Type.getValue() == null) {
                Type_Label.setVisible(true);
                Type.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                flag1=true;
            }
            if (Lisc.getValue() == null) {
                Lisc_Label.setVisible(true);
                Lisc.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");

                flag1=true;
            }
            if (Distance.getValue() == null) {
                Dist_Label.setVisible(true);
                Distance.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                flag1=true;
            }else{
                if (Distance.getValue().toString().equals("Συγκεκριμένο Έτος") && Pyear.getText().equals("")) {
                    Year_Label.setText("Το Έτος είναι κενό");
                    Year_Label.setVisible(true);
                    Pyear.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                    flag1=true;
                }
                else if(Distance.getValue().toString().equals("Συγκεκριμένο Έτος")){
                    Integer.valueOf(Pyear.getText());
                }
                if (Distance.getValue().toString().equals("Άλλο Διάστημα") && (From.getValue() == null)) {
                    From_Label.setVisible(true);
                    From.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                    flag1=true;
                }
                if (Distance.getValue().toString().equals("Άλλο Διάστημα") && (To.getValue() == null)) {
                    To_Label.setVisible(true);
                    To.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                    flag1=true;
                }
            }
            if(flag1){
                return;
            }
            String LiscPlate = Lisc.getValue().toString();
            ArrayList<ResultSet> rs = new ArrayList<>();
            Sql db = new Sql();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date today = new Date();
            String Today = sdf.format(today);
            String[] TableDay = Today.split("-");
            int Year = Integer.valueOf(TableDay[0]);
            int Month = Integer.valueOf(TableDay[1]);
            int Day = Integer.valueOf(TableDay[2]);
            String dist = "";
            ArrayList<String> Table = new ArrayList<>();
            ObservableList<ModelTotal> Data = FXCollections.observableArrayList();
            switch (Distance.getValue().toString()) {
                case "Τελευταία 5 Έτοι":
                    switch (Type.getValue().toString()) {
                        case "Service":
                            Table.add("Service");
                            break;
                        case "Eπισκευές":
                            Table.add("Repairs");
                        case "ΚΤΕΟ":
                            Table.add("KTEO");
                            break;
                        case "Καύσιμα":
                            Table.add("Refill");
                            break;
                        case ("Όλα"):
                            Table.add("Service");
                            Table.add("Repairs");
                            Table.add("KTEO");
                            Table.add("Refill");
                            break;
                    }
                    for (int i = 0; i < 6; i++) {
                        String temp = Year - i + "-01-01";
                        String temp2 = Year - i + "-12-31";
                        dist = String.valueOf(Year - i);
                        for (int k = 0; k < Table.size(); k++) {
                            rs.add(db.Query_Car_Price_Date(Table.get(k), LiscPlate, temp, temp2));
                        }
                        ModelTotal model;
                        double total = 0;
                        for (int l = 0; l < rs.size(); l++) {
                            String temps = rs.get(l).getString(("Total"));
                            if (temps == null) {
                                continue;
                            }
                            total = Double.valueOf(temps) + total;
                        }
                        rs = new ArrayList<>();
                        model = new ModelTotal(dist, String.valueOf(total));
                        Data.add(model);
                    }
                    String date2 = Year - 5 + "-01-01";
                    for (int k = 0; k < Table.size(); k++) {
                        rs.add(db.Query_Car_Price_Date(Table.get(k), LiscPlate, date2, Today));
                    }
                    break;
                case "Πρωηγούμενο Έτος":
                    switch (Type.getValue().toString()) {
                        case "Service":
                            Table.add("Service");
                            break;
                        case "Eπισκευές":
                            Table.add("Repairs");
                        case "ΚΤΕΟ":
                            Table.add("KTEO");
                            break;
                        case "Καύσιμα":
                            Table.add("Refill");
                            break;
                        case ("Όλα"):
                            Table.add("Service");
                            Table.add("Repairs");
                            Table.add("KTEO");
                            Table.add("Refill");
                            break;
                    }
                    for (int i = 1; i < 13; i++) {
                        String temp, temp2;
                        if (i < 10) {
                            temp = Year - 1 + "-" + "0" + i + "-01";
                            temp2 = Year - 1 + "-" + "0" + i + "-31";
                        } else {
                            temp = Year - 1 + "-" + i + "-01";
                            temp2 = Year - 1 + "-" + i + "-31";
                        }
                        dist = String.valueOf(i) + "ος";
                        for (int k = 0; k < Table.size(); k++) {
                            rs.add(db.Query_Car_Price_Date(Table.get(k), LiscPlate, temp, temp2));
                        }
                        ModelTotal model;
                        double total = 0;
                        for (int l = 0; l < rs.size(); l++) {
                            String temps = rs.get(l).getString(("Total"));
                            if (temps == null) {
                                continue;
                            }
                            total = Double.valueOf(temps) + total;
                        }
                        rs = new ArrayList<>();
                        model = new ModelTotal(dist, String.valueOf(total));
                        Data.add(model);
                    }
                    String date3 = Year - 1 + "-01-01";
                    String date4 = Year - 1 + "-12-31";
                    for (int k = 0; k < Table.size(); k++) {
                        rs.add(db.Query_Car_Price_Date(Table.get(k), LiscPlate, date3, date4));
                    }
                    break;
                case "Τρέχων Έτος":
                    switch (Type.getValue().toString()) {
                        case "Service":
                            Table.add("Service");
                            break;
                        case "Eπισκευές":
                            Table.add("Repairs");
                        case "ΚΤΕΟ":
                            Table.add("KTEO");
                            break;
                        case "Καύσιμα":
                            Table.add("Refill");
                            break;
                        case ("Όλα"):
                            Table.add("Service");
                            Table.add("Repairs");
                            Table.add("KTEO");
                            Table.add("Refill");
                            break;
                    }
                    for (int i = 1; i < Month + 1; i++) {
                        String temp, temp2;
                        if (i < 10) {
                            temp = Year + "-" + "0" + i + "-01";
                            temp2 = Year + "-" + "0" + i + "-31";
                        } else {
                            temp = Year + "-" + i + "-01";
                            temp2 = Year + "-" + i + "-31";
                        }

                        dist = String.valueOf(i) + "ος";
                        for (int k = 0; k < Table.size(); k++) {
                            rs.add(db.Query_Car_Price_Date(Table.get(k), LiscPlate, temp, temp2));
                        }
                        ModelTotal model;
                        double total = 0;
                        for (int l = 0; l < rs.size(); l++) {
                            String temps = rs.get(l).getString(("Total"));
                            if (temps == null) {
                                continue;
                            }
                            total = Double.valueOf(temps) + total;
                        }
                        rs = new ArrayList<>();
                        model = new ModelTotal(dist, String.valueOf(total));
                        Data.add(model);
                    }
                    String date5 = Year + "-01-01";
                    for (int k = 0; k < Table.size(); k++) {
                        rs.add(db.Query_Car_Price_Date(Table.get(k), LiscPlate, date5, Today));
                    }
                    break;

                case "Πρωηγούμενος Μήνας":
                    switch (Type.getValue().toString()) {
                        case "Service":
                            Table.add("Service");
                            break;
                        case "Eπισκευές":
                            Table.add("Repairs");
                        case "ΚΤΕΟ":
                            Table.add("KTEO");
                            break;
                        case "Καύσιμα":
                            Table.add("Refill");
                            break;
                        case ("Όλα"):
                            Table.add("Service");
                            Table.add("Repairs");
                            Table.add("KTEO");
                            Table.add("Refill");
                            break;
                    }
                    String date6 = Year + "-" + (Month - 1) + "-01";
                    String date7 = Year + "-" + (Month - 1) + "-31";
                    for (int k = 0; k < Table.size(); k++) {
                        rs.add(db.Query_Car_Price_Date(Table.get(k), LiscPlate, date6, date7));
                    }
                    break;
                case "Τρέχων Μήνας":
                    switch (Type.getValue().toString()) {
                        case "Service":
                            Table.add("Service");
                            break;
                        case "Eπισκευές":
                            Table.add("Repairs");
                        case "ΚΤΕΟ":
                            Table.add("KTEO");
                            break;
                        case "Καύσιμα":
                            Table.add("Refill");
                            break;
                        case ("Όλα"):
                            Table.add("Service");
                            Table.add("Repairs");
                            Table.add("KTEO");
                            Table.add("Refill");
                            break;
                    }
                    String date8 = Year + "-" + Month + "-01";
                    for (int k = 0; k < Table.size(); k++) {
                        rs.add(db.Query_Car_Price_Date(Table.get(k), LiscPlate, date8, Today));
                    }
                    break;
                case "Συγκεκριμένο Έτος":
                    switch (Type.getValue().toString()) {
                        case "Service":
                            Table.add("Service");
                            break;
                        case "Eπισκευές":
                            Table.add("Repairs");
                        case "ΚΤΕΟ":
                            Table.add("KTEO");
                            break;
                        case "Καύσιμα":
                            Table.add("Refill");
                            break;
                        case ("Όλα"):
                            Table.add("Service");
                            Table.add("Repairs");
                            Table.add("KTEO");
                            Table.add("Refill");
                            break;
                    }
                    String tempor = Pyear.getText();
                    String[] Array = tempor.split("-");
                    String top = Array[0] + "-01-01";
                    String end = Array[0] + "-12-31";
                    int flag = 13;
                    if (Array[0].equals(String.valueOf(Year))) flag = Month + 1;
                    for (int i = 1; i < flag; i++) {
                        String temp, temp2;
                        if (i < 10) {
                            temp = Integer.valueOf(Array[0]) + "-" + "0" + i + "-01";
                            temp2 = Integer.valueOf(Array[0]) + "-" + "0" + i + "-31";
                        } else {
                            temp = Integer.valueOf(Array[0]) + "-" + i + "-01";
                            temp2 = Integer.valueOf(Array[0]) + "-" + i + "-31";
                        }
                        dist = String.valueOf(i);
                        for (int k = 0; k < Table.size(); k++) {
                            rs.add(db.Query_Car_Price_Date(Table.get(k), LiscPlate, temp, temp2));
                        }
                        ModelTotal model;
                        double total = 0;
                        for (int l = 0; l < rs.size(); l++) {
                            String temps = rs.get(l).getString(("Total"));
                            if (temps == null) {
                                continue;
                            }
                            total = Double.valueOf(temps) + total;
                        }
                        rs = new ArrayList<>();
                        model = new ModelTotal(dist, String.valueOf(total));
                        Data.add(model);
                    }
                    for (int k = 0; k < Table.size(); k++) {
                        rs.add(db.Query_Car_Price_Date(Table.get(k), LiscPlate, top, end));
                    }
                    break;
                case "Άλλο Διάστημα":
                    switch (Type.getValue().toString()) {
                        case "Service":
                            Table.add("Service");
                            break;
                        case "Eπισκευές":
                            Table.add("Repairs");
                        case "ΚΤΕΟ":
                            Table.add("KTEO");
                            break;
                        case "Καύσιμα":
                            Table.add("Refill");
                            break;
                        case ("Όλα"):
                            Table.add("Service");
                            Table.add("Repairs");
                            Table.add("KTEO");
                            Table.add("Refill");
                            break;
                    }
                    String from = From.getValue().toString();
                    String to = To.getValue().toString();
                    for (int k = 0; k < Table.size(); k++) {
                        rs.add(db.Query_Car_Price_Date(Table.get(k), LiscPlate, from, to));
                    }
                    break;
            }
            for (int m = 0; m < rs.size(); m++) {
                double total = 0;
                for (int l = 0; l < rs.size(); l++) {

                    String temps = rs.get(l).getString(("Total"));
                    if (temps == null) {
                        continue;
                    }
                    total = Double.valueOf(temps) + total;
                }
                if (Distance.getValue().toString().equals("Άλλο Διάστημα")) {
                    dist = From.getValue().toString() + " έως " + To.getValue().toString();
                } else if (Distance.getValue().toString().equals("Τρέχων Μήνας")) {
                    dist = "Τρέχων Μήνας";
                } else if (Distance.getValue().toString().equals("Πρωηγούμενος Μήνας")) {
                    dist = "Πρωηγούμενος Μήνας";
                } else {
                    dist = "Σύνολο";
                }
                ModelTotal model;
                model = new ModelTotal(dist, String.valueOf(total));
                Data.add(model);
                Table1.setItems(Data);
            }
            db.Disconnect();
        } catch (
                SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            Year_Label.setText("Το έτος πρέπει να είναι ακέραιος");
            Year_Label.setVisible(true);
            Pyear.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");

        }

    }

    void Show_Rest() {
        if (Distance.getValue().toString().equals("Άλλο Διάστημα")) {
            To.setVisible(true);
            From.setVisible(true);
            Pyear.setVisible(false);
        } else if (Distance.getValue().toString().equals("Συγκεκριμένο Έτος")) {
            Pyear.setVisible(true);
            To.setVisible(false);
            From.setVisible(false);
        } else {
            To.setVisible(false);
            From.setVisible(false);
            Pyear.setVisible(false);
        }
    }


}
