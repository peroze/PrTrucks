package Pr.Cars;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.FileReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;


public class Settings implements Initializable {

    @FXML
    private AnchorPane Totals_Pane;

    @FXML
    private Label Backup;

    @FXML
    private Slider Slider;

    @FXML
    private Label Day1;

    @FXML
    private Label Day7;

    @FXML
    private Label Day14;

    @FXML
    private Label Day30;

    @FXML
    private Slider onoffBack;

    @FXML
    private Slider onoffBackServ;

    @FXML
    private Slider onoffBackKteo;

    @FXML
    private Slider onoffBackGas;

    @FXML
    private Slider SliderNot;

    @FXML
    private Slider SliderNot1;

    @FXML
    private Slider SliderNot2;

    @FXML
    private Slider SliderNot3;

    @FXML
    private Label Day11;

    @FXML
    private Label Day71;

    @FXML
    private Label Day141;

    @FXML
    private Label Day301;

    @FXML
    private Label Day111;

    @FXML
    private Label Day711;

    @FXML
    private Label Day1411;

    @FXML
    private Label Day3011;

    @FXML
    private Label Day112;

    @FXML
    private Label Day712;

    @FXML
    private Label Day1412;

    @FXML
    private Label Day3012;

    @FXML
    private TextField OldPrice;

    @FXML
    private TextField NewPrice;

    @FXML
    private Button Save_Button;

    @FXML
    private Label Pr_Label;

    @FXML
    private Label SaveLabel;

    @FXML
    private Label BackupLabel;

    @FXML
    private Label Day113;

    @FXML
    private Label Day713;

    @FXML
    private Label Day1413;

    @FXML
    private Label Day3013;



    boolean changes;


    String SaveDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FileManagment FS1=new FileManagment();
        FS1.setFile("FuelPrice.txt","Fuel");
        OldPrice.setText(String.valueOf(FS1.ReadFuelPrice()));
        changes=false;
        FileManagment FS = new FileManagment();
        String[] fs = FS.Read();
        if (fs[0].equals("true")) {
            onoffBack.setValue(100);
        } else {
            onoffBack.setValue(0);
        }
        Slider.setValue(Double.valueOf(fs[1]));
        if (fs[2].equals("true")) {
            onoffBackServ.setValue(100);
        } else {
            onoffBackServ.setValue(0);
        }
        if (fs[4].equals("true")) {
            onoffBackKteo.setValue(100);
        } else {
            onoffBackKteo.setValue(0);
        }
        if (fs[6].equals("true")) {
            onoffBackGas.setValue(100);
        } else {
            onoffBackGas.setValue(0);
        }
        Slider.setValue(Double.valueOf(fs[1]));
        labelColour(Double.valueOf(fs[1]), 0);
        SliderNot.setValue(Double.valueOf(fs[3]));
        labelColour(Double.valueOf(fs[3]), 1);
        SliderNot1.setValue(Double.valueOf(fs[5]));
        labelColour(Double.valueOf(fs[5]), 2);
        SliderNot2.setValue(Double.valueOf(fs[7]));
        labelColour(Double.valueOf(fs[7]), 3);
        SaveDate = fs[8];
        Slider.valueProperty().addListener(numberChangeListenerSl);
        onoffBack.valueProperty().addListener(OnOff);
        onoffBackServ.valueProperty().addListener(OnOffSer);
        onoffBackKteo.valueProperty().addListener(OnOffKteo);
        onoffBackGas.valueProperty().addListener(OnOffGas);

        SliderNot.valueProperty().addListener(numberChangeListenerSlN);
        SliderNot1.valueProperty().addListener(numberChangeListenerSlN1);
        SliderNot2.valueProperty().addListener(numberChangeListenerSlN2);
        SliderNot3.valueProperty().addListener(numberChangeListenerSlN3);
    }

    final ChangeListener<Number> numberChangeListenerSl = (obs, old, val) -> {
        changes=true;
        double roundedValue = Math.round(val.doubleValue() / 33.3) * 33.3;
        if (roundedValue > 99) {
            roundedValue = 100;
        }
        labelColour(roundedValue, 0);
        Slider.valueProperty().set(roundedValue);

    };

    final ChangeListener<Number> numberChangeListenerSlN = (obs, old, val) -> {
        changes=true;
        double roundedValue = Math.round(val.doubleValue() / 33.3) * 33.3;
        if (roundedValue > 99) {
            roundedValue = 100;
        }
        labelColour(roundedValue, 1);
        SliderNot.valueProperty().set(roundedValue);

    };

    final ChangeListener<Number> numberChangeListenerSlN1 = (obs, old, val) -> {
        changes=true;
        double roundedValue = Math.round(val.doubleValue() / 33.3) * 33.3;
        if (roundedValue > 99) {
            roundedValue = 100;
        }
        labelColour(roundedValue, 2);
        SliderNot1.valueProperty().set(roundedValue);

    };

    final ChangeListener<Number> numberChangeListenerSlN2 = (obs, old, val) -> {
        changes=true;
        double roundedValue = Math.round(val.doubleValue() / 33.3) * 33.3;
        if (roundedValue > 99) {
            roundedValue = 100;
        }
        labelColour(roundedValue, 3);
        SliderNot2.valueProperty().set(roundedValue);

    };

    final ChangeListener<Number> numberChangeListenerSlN3 = (obs, old, val) -> {
        changes=true;
        double roundedValue = Math.round(val.doubleValue() / 33.3) * 33.3;
        if (roundedValue > 99) {
            roundedValue = 100;
        }
        labelColour(roundedValue, 4);
        SliderNot3.valueProperty().set(roundedValue);

    };

    final ChangeListener<Number> OnOff = (obs, old, val) -> {
        changes=true;
        double roundedValue = Math.round(val.doubleValue() / 100) * 100;
        onoffBack.valueProperty().set(roundedValue);
    };

    final ChangeListener<Number> OnOffSer = (obs, old, val) -> {
        changes=true;
        double roundedValue = Math.round(val.doubleValue() / 100) * 100;
        onoffBackServ.valueProperty().set(roundedValue);
    };

    final ChangeListener<Number> OnOffKteo = (obs, old, val) -> {
        changes=true;
        double roundedValue = Math.round(val.doubleValue() / 100) * 100;
        onoffBackKteo.valueProperty().set(roundedValue);
    };

    final ChangeListener<Number> OnOffGas = (obs, old, val) -> {
        changes=true;
        double roundedValue = Math.round(val.doubleValue() / 100) * 100;
        onoffBackGas.valueProperty().set(roundedValue);
    };


    public void labelColour(double i, int sl) {
        if (sl == 0) {
            Day1.setStyle(null);
            Day7.setStyle(null);
            Day14.setStyle(null);
            Day30.setStyle(null);
            if (i == 0) {
                Day1.setStyle("-fx-text-fill: #ff6542");
            } else if (i == 33.3) {
                Day7.setStyle("-fx-text-fill: #ff6542");
            } else if (i == 100) {
                Day30.setStyle("-fx-text-fill: #ff6542");
            } else {
                Day14.setStyle("-fx-text-fill: #ff6542");
            }
        } else if(sl==1) {
            Day11.setStyle(null);
            Day71.setStyle(null);
            Day141.setStyle(null);
            Day301.setStyle(null);
            if (i == 0) {
                Day11.setStyle("-fx-text-fill: #ff6542");
            } else if (i == 33.3) {
                Day71.setStyle("-fx-text-fill: #ff6542");
            } else if (i == 100) {
                Day301.setStyle("-fx-text-fill: #ff6542");
            } else {
                Day141.setStyle("-fx-text-fill: #ff6542");
            }
        }
        else if(sl==2){
            Day111.setStyle(null);
            Day711.setStyle(null);
            Day1411.setStyle(null);
            Day3011.setStyle(null);
            if (i == 0) {
                Day111.setStyle("-fx-text-fill: #ff6542");
            } else if (i == 33.3) {
                Day711.setStyle("-fx-text-fill: #ff6542");
            } else if (i == 100) {
                Day3011.setStyle("-fx-text-fill: #ff6542");
            } else {
                Day1411.setStyle("-fx-text-fill: #ff6542");
            }
        }
        else if(sl==3){
            Day112.setStyle(null);
            Day712.setStyle(null);
            Day1412.setStyle(null);
            Day3012.setStyle(null);
            if (i == 0) {
                Day112.setStyle("-fx-text-fill: #ff6542");
            } else if (i == 33.3) {
                Day712.setStyle("-fx-text-fill: #ff6542");
            } else if (i == 100) {
                Day3012.setStyle("-fx-text-fill: #ff6542");
            } else {
                Day1412.setStyle("-fx-text-fill: #ff6542");
            }
        }
        else if(sl==4){

             Day113.setStyle(null);
             Day713.setStyle(null);
             Day1413.setStyle(null);
             Day3013.setStyle(null);
            if (i == 0) {
                Day113.setStyle("-fx-text-fill: #ff6542");
            } else if (i == 33.3) {
                Day713.setStyle("-fx-text-fill: #ff6542");
            } else if (i == 100) {
                Day3013.setStyle("-fx-text-fill: #ff6542");
            } else {
                Day1413.setStyle("-fx-text-fill: #ff6542");
            }
        }
    }

    @FXML
    void Save_Button_Pr(ActionEvent event) {
        Pr_Label.setVisible(false);
        NewPrice.setStyle(null);
        FileManagment FS = new FileManagment();
        if(changes==true) {
            String ForFile;
            if (onoffBack.getValue() > 50) {
                ForFile = "true";
            } else {
                ForFile = "False";
            }
            ForFile = ForFile + "~" + Slider.getValue();
            if (onoffBackServ.getValue() > 50) {
                ForFile = ForFile + "~" + "true";
            } else {
                ForFile = ForFile + "~" + "false";
            }
            ForFile = ForFile + "~" + SliderNot.getValue();
            if (onoffBackKteo.getValue() > 50) {
                ForFile = ForFile + "~" + "true";
            } else {
                ForFile = ForFile + "~" + "false";
            }
            ForFile = ForFile + "~" + SliderNot1.getValue();
            if (onoffBackGas.getValue() > 50) {
                ForFile = ForFile + "~" + "true";
            } else {
                ForFile = ForFile + "~" + "false";
            }
            ForFile = ForFile + "~" + SliderNot2.getValue();
            ForFile = ForFile + "~" + SliderNot3.getValue();
            ForFile = ForFile + "~" + SaveDate;

            FS.Write(ForFile);
            SaveLabel.setVisible(true);
        }
        try {
            if(NewPrice.getText()!="") {
                Double.valueOf(NewPrice.getText());
            }
        }
        catch(NumberFormatException e){
            Pr_Label.setVisible(true);
            Pr_Label.setText("Η Τιμή πρέπει να είναι αριθμός");
            NewPrice.setStyle(" -fx-background-color: #383838;-fx-border-width: 0px 0px 1px 0px;-fx-border-color:red;-fx-text-fill: white;");
            return;
        }
        if(!NewPrice.getText().equals("")){
            FS.setFile("FuelPrice.txt","Fuel");
            FS.Write(NewPrice.getText());
            SaveLabel.setVisible(true);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Η αποθήκευση της τιμής Απέτυχε δοκιμάστε ξανά");
            alert.setContentText("Το αυτοκίνητο δεν κατατάφερε να ενταχθεί, δοκιμάστε ξανά");
            alert.showAndWait();
        }

    }

    @FXML
    void Backup_Pre(MouseEvent event) {
        Backup bc=new Backup();
        bc.CreateBackupService();
        bc.CreateBackupRefill();
        bc.CreateBackupKteo();
        bc.CreateBackupEmmision();
        bc.CreateBackupRepair();
        FileManagment FS = new FileManagment();
        String[] tmp = FS.Read();
        String pr = tmp[0];
        for (int i = 1; i < tmp.length - 1; i++) {
            pr = pr + "~" + tmp[i];
        }
        Date date2 = new Date();
        date2.toString();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SaveDate = dateFormat.format(date2);
        pr = pr +"~"+ dateFormat.format(date2);
        FS.Write(pr);
        BackupLabel.setVisible(true);

    }


}
