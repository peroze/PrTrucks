package sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private TableView<ModelTotalFuel> Table1;

    @FXML
    private TableColumn<ModelTotalFuel, String> Date;

    @FXML
    private TableColumn<ModelTotalFuel, String> Amount;

    @FXML
    private DatePicker From;

    @FXML
    private DatePicker To;


    @FXML
    private ComboBox Lisc;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> oblist= FXCollections.observableArrayList();
        Date.setCellValueFactory(new PropertyValueFactory<>("Period"));
        Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        oblist.add("Τελευταία 5 Έτοι");
        oblist.add("Πρωηγούμενο Έτος");
        oblist.add("Τρέχων Έτος");
        oblist.add("Πρωηγούμενος Μήνας");
        oblist.add("Τρέχων Μήνας");
        oblist.add("Άλλο Διάστημα");
        Distance.setItems(oblist);
        To.setVisible(false);
        From.setVisible(false);
        Distance.setOnAction(e->{
            Show_Rest();
        } );
    }



    @FXML
    void Show_Button_Pr(ActionEvent event) {
        ResultSet rs=null;
        Sql db=new Sql();
        switch (Distance.getValue().toString()){
            case "Τελευταία 5 Έτοι" :
                break;
            case "Πρωηγούμενο Έτος":
                break;
            case "Τρέχων Έτος":
                break;
            case "Πρωηγούμενος Μήνας":
                break;
            case "Τρέχων Μήνας":
                break;
            case "Άλλο Διάστημα" :
                String from=From.getValue().toString();
                String to=To.getValue().toString();
                rs=db.Querry_All_Refill_Date(from,to);
                break;
        }
        try {
            ObservableList<ModelTotalFuel> Data=FXCollections.observableArrayList();
            while (rs.next()) {
                String dist="";
                switch (Distance.getValue().toString()){
                    case "Τελευταία 5 Έτοι" :
                        break;
                    case "Πρωηγούμενο Έτος":
                        break;
                    case "Τρέχων Έτος":
                        break;
                    case "Πρωηγούμενος Μήνας":
                        break;
                    case "Τρέχων Μήνας":
                        break;
                    case "Άλλο Διάστημα" :
                        dist=From.getValue().toString()+" έως "+To.getValue().toString();
                        break;
                }
                ModelTotalFuel temp=new ModelTotalFuel(dist,rs.getString("Total"));
                Data.add(temp);
                Table1.setItems(Data);
            }
            db.Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void Show_Rest(){
        if(Distance.getValue().toString().equals("Άλλο Διάστημα")){
         To.setVisible(true);
         From.setVisible(true);
        }
        else{
            To.setVisible(false);
            From.setVisible(false);
        }
    }



}
