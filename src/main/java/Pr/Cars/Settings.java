package Pr.Cars;

import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    private Label Day11;

    @FXML
    private Label Day71;

    @FXML
    private Label Day141;

    @FXML
    private Label Day301;

    @FXML
    private Button Save_Button;


    String SaveDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        if (fs[3].equals("true")) {
            onoffBackKteo.setValue(100);
        } else {
            onoffBackKteo.setValue(0);
        }
        if (fs[4].equals("true")) {
            onoffBackGas.setValue(100);
        } else {
            onoffBackGas.setValue(0);
        }
        Slider.setValue(Double.valueOf(fs[1]));
        labelColour(Double.valueOf(fs[1]), 0);
        SliderNot.setValue(Double.valueOf(fs[5]));
        labelColour(Double.valueOf(fs[5]), 1);
        SaveDate = fs[6];
        Slider.valueProperty().addListener(numberChangeListenerSl);
        onoffBack.valueProperty().addListener(OnOff);
        onoffBackServ.valueProperty().addListener(OnOffSer);
        onoffBackKteo.valueProperty().addListener(OnOffKteo);
        onoffBackGas.valueProperty().addListener(OnOffGas);

        SliderNot.valueProperty().addListener(numberChangeListenerSlN);
    }

    final ChangeListener<Number> numberChangeListenerSl = (obs, old, val) -> {
        double roundedValue = Math.round(val.doubleValue() / 33.3) * 33.3;
        if (roundedValue > 99) {
            roundedValue = 100;
        }
        labelColour(roundedValue, 0);
        Slider.valueProperty().set(roundedValue);

    };

    final ChangeListener<Number> numberChangeListenerSlN = (obs, old, val) -> {
        double roundedValue = Math.round(val.doubleValue() / 33.3) * 33.3;
        if (roundedValue > 99) {
            roundedValue = 100;
        }
        labelColour(roundedValue, 1);
        SliderNot.valueProperty().set(roundedValue);

    };

    final ChangeListener<Number> OnOff = (obs, old, val) -> {
        double roundedValue = Math.round(val.doubleValue() / 100) * 100;
        onoffBack.valueProperty().set(roundedValue);
    };

    final ChangeListener<Number> OnOffSer = (obs, old, val) -> {
        double roundedValue = Math.round(val.doubleValue() / 100) * 100;
        onoffBackServ.valueProperty().set(roundedValue);
    };

    final ChangeListener<Number> OnOffKteo = (obs, old, val) -> {
        double roundedValue = Math.round(val.doubleValue() / 100) * 100;
        onoffBackKteo.valueProperty().set(roundedValue);
    };

    final ChangeListener<Number> OnOffGas = (obs, old, val) -> {
        double roundedValue = Math.round(val.doubleValue() / 100) * 100;
        onoffBackGas.valueProperty().set(roundedValue);
    };


    public void labelColour(double i, int sl) {
        if (sl == 0) {
            if (i == 0) {
                Day1.setStyle("-fx-text-fill: #FA8072");
                Day7.setStyle(null);
                Day14.setStyle(null);
                Day30.setStyle(null);
            } else if (i == 33.3) {
                Day1.setStyle(null);
                Day7.setStyle("-fx-text-fill: #FA8072");
                Day14.setStyle(null);
                Day30.setStyle(null);
            } else if (i == 100) {
                Day1.setStyle(null);
                Day7.setStyle(null);
                Day14.setStyle(null);
                Day30.setStyle("-fx-text-fill: #FA8072");
            } else {
                Day1.setStyle(null);
                Day7.setStyle(null);
                Day14.setStyle("-fx-text-fill: #FA8072");
                Day30.setStyle(null);
            }
        } else {
            if (i == 0) {
                Day11.setStyle("-fx-text-fill: #FA8072");
                Day71.setStyle(null);
                Day141.setStyle(null);
                Day301.setStyle(null);
            } else if (i == 33.3) {
                Day11.setStyle(null);
                Day71.setStyle("-fx-text-fill: #FA8072");
                Day141.setStyle(null);
                Day301.setStyle(null);
            } else if (i == 100) {
                Day11.setStyle(null);
                Day71.setStyle(null);
                Day141.setStyle(null);
                Day301.setStyle("-fx-text-fill: #FA8072");
            } else {
                Day11.setStyle(null);
                Day71.setStyle(null);
                Day141.setStyle("-fx-text-fill: #FA8072");
                Day301.setStyle(null);
            }
        }
    }

    @FXML
    void Save_Button_Pr(ActionEvent event) {
        String ForFile;
        if (onoffBack.getValue() > 60) {
            ForFile = "true";
        } else {
            ForFile = "False";
        }
        ForFile = ForFile + "~" + Slider.getValue();
        if (onoffBackServ.getValue() > 60) {
            ForFile = ForFile + "~" + "true";
        } else {
            ForFile = ForFile + "~" + "false";
        }
        if (onoffBackKteo.getValue() > 60) {
            ForFile = ForFile + "~" + "true";
        } else {
            ForFile = ForFile + "~" + "false";
        }
        if (onoffBackGas.getValue() > 60) {
            ForFile = ForFile + "~" + "true";
        } else {
            ForFile = ForFile + "~" + "false";
        }
        ForFile = ForFile + "~" + SliderNot.getValue();
        ForFile = ForFile + "~" + SaveDate;
        FileManagment FS = new FileManagment();
        FS.Write(ForFile);
    }

    @FXML
    void Backup_Pre(MouseEvent event) {

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


    }


}
