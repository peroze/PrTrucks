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
import java.util.Date;
import java.util.ResourceBundle;


public class LitersTotal implements Initializable {

    private double x_Offset = 0;
    private double y_Offset = 0;

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
    private Label Dist_Label;


    @FXML
    private Label Year_Label;

    @FXML
    private Label To_Label;

    @FXML
    private Label From_Label;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        To.setVisible(false);
        From.setVisible(false);
        Pyear.setVisible(false);
        Distance.setOnAction(e -> {
            Show_Rest();
        });
    }


    @FXML
    void Show_Button_Pr(ActionEvent event) {
        try {

            boolean flag1=false;
            Distance.setStyle(null);
            Dist_Label.setVisible(false);
            From.setStyle(null);
            From_Label.setVisible(false);
            To.setStyle(null);
            To_Label.setVisible(false);
            Pyear.setStyle(null);
            Year_Label.setVisible(false);
            if (Distance.getValue() == null) {
                Dist_Label.setVisible(true);
                Distance.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
                flag1=true;
            }else {
                if (Distance.getValue().toString().equals("Συγκεκριμένο Έτος") && Pyear.getText().equals("")) {
                    Year_Label.setText("Το έτος είναι κενό");
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
            ResultSet rs = null;
            Sql db = new Sql();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date today = new Date();
            String Today = sdf.format(today);
            String[] TableDay = Today.split("-");
            int Year = Integer.valueOf(TableDay[0]);
            int Month = Integer.valueOf(TableDay[1]);
            int Day = Integer.valueOf(TableDay[2]);
            String dist = "";
            ObservableList<ModelTotal> Data = FXCollections.observableArrayList();
            switch (Distance.getValue().toString()) {
                case "Τελευταία 5 Έτοι":
                    for (int i = 0; i < 6; i++) {
                        String temp = Year - i + "-01-01";
                        String temp2 = Year - i + "-12-31";
                        dist = String.valueOf(Year - i);
                        rs = db.Query_All_Refill_Date(temp, temp2);
                        ModelTotal model;
                        if(rs.getString("Total")==null){
                             model = new ModelTotal(dist, "0");
                        }
                        else {
                             model = new ModelTotal(dist, rs.getString("Total"));
                        }
                        Data.add(model);
                    }
                    String date2 = Year - 5 + "-01-01";
                    rs = db.Query_All_Refill_Date(date2, Today);
                    break;
                case "Πρωηγούμενο Έτος":
                    for (int i = 1; i < 13; i++) {
                        String temp, temp2;
                        if (i < 10) {
                            temp = Year - 1 + "-" + "0" + i + "-01";
                            temp2 = Year - 1 + "-" + "0" + i + "-31";
                        } else {
                            temp = Year - 1 + "-" + i + "-01";
                            temp2 = Year - 1 + "-" + i + "-31";
                        }
                        dist = String.valueOf(i)+"ος";
                        rs = db.Query_All_Refill_Date(temp, temp2);
                        ModelTotal model;
                        if(rs.getString("Total")==null){
                            model = new ModelTotal(dist, "0");
                        }
                        else {
                            model = new ModelTotal(dist, rs.getString("Total"));
                        }
                        Data.add(model);
                    }
                    String date3 = Year - 1 + "-01-01";
                    String date4 = Year - 1 + "-12-31";
                    rs = db.Query_All_Refill_Date(date3, date4);
                    break;
                case "Τρέχων Έτος":
                    for (int i = 1; i < Month+1; i++) {
                        String temp, temp2;
                        if (i < 10) {
                            temp = Year + "-" + "0" + i + "-01";
                            temp2 = Year + "-" + "0" + i + "-31";
                        } else {
                            temp = Year + "-" + i + "-01";
                            temp2 = Year + "-" + i + "-31";
                        }

                        dist = String.valueOf(i)+"ος";
                        rs = db.Query_All_Refill_Date(temp, temp2);
                        ModelTotal model;
                        if(rs.getString("Total")==null){
                            model = new ModelTotal(dist, "0");
                        }
                        else {
                            model = new ModelTotal(dist, rs.getString("Total"));
                        }
                        Data.add(model);
                    }
                    String date5 = Year + "-01-01";
                    rs = db.Query_All_Refill_Date(date5, Today);
                    break;

                case "Πρωηγούμενος Μήνας":
                    String date6 = Year+"-" + (Month - 1) + "-01";
                    String date7 = Year+"-" + (Month - 1) + "-31";
                    rs = db.Query_All_Refill_Date(date6, date7);
                    break;
                case "Τρέχων Μήνας":
                    String date8 = Year + "-" + Month + "-01";
                    rs = db.Query_All_Refill_Date(date8, Today);
                    break;
                case "Συγκεκριμένο Έτος":
                    String tempor =Pyear.getText();
                    String[] Array=tempor.split("-");
                    String top=Array[0]+"-01-01";
                    String end=Array[0]+"-12-31";
                    int flag=13;
                    if(Array[0].equals(String.valueOf(Year))) flag=Month+1;
                    for (int i = 1; i < flag; i++) {
                        String temp, temp2;
                        if (i < 10) {
                            temp = Integer.valueOf(Array[0])  + "-" + "0" + i + "-01";
                            temp2 = Integer.valueOf(Array[0]) + "-" + "0" + i + "-31";
                        } else {
                            temp = Integer.valueOf(Array[0])  + "-" + i + "-01";
                            temp2 = Integer.valueOf(Array[0]) + "-" + i + "-31";
                        }
                        dist = String.valueOf(i);
                        rs = db.Query_All_Refill_Date(temp, temp2);
                        ModelTotal model;
                        if(rs.getString("Total")==null){
                            model = new ModelTotal(dist, "0");
                        }
                        else {
                            model = new ModelTotal(dist, rs.getString("Total"));
                        }
                        Data.add(model);
                    }
                    rs = db.Query_All_Refill_Date(top,end);
                    break;
                case "Άλλο Διάστημα":
                    String from = From.getValue().toString();
                    String to = To.getValue().toString();
                    rs = db.Query_All_Refill_Date(from, to);
                    break;
            }
            while (rs.next()) {

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
                if(rs.getString("Total")==null){
                    model = new ModelTotal(dist, "0");
                }
                else {
                    model = new ModelTotal(dist, rs.getString("Total"));
                }
                Data.add(model);
                Table1.setItems(Data);
            }
            db.Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NumberFormatException e){
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
