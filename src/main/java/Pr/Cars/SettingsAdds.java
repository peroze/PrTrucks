package Pr.Cars;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;


public class SettingsAdds implements Initializable {

    @FXML
    private AnchorPane Totals_Pane;

    @FXML
    private TableView<StringsForTables> Location_Table;

    @FXML
    private TableColumn<StringsForTables, String> Location_Column;

    @FXML
    private TableView<StringsForTables> Type_Table;

    @FXML
    private TableColumn<StringsForTables, String> Type_Column;

    @FXML
    private TextField Location_Text;

    @FXML
    private Button Location_button;

    @FXML
    private TextField Type_Text;

    @FXML
    private Button Type_button;

    ObservableList<StringsForTables> oblist;
    ObservableList<StringsForTables> oblist2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Location_Column.setCellValueFactory(new PropertyValueFactory<>("string"));
            Type_Column.setCellValueFactory(new PropertyValueFactory<>("string"));
            Sql db = new Sql();
            ResultSet locs = db.Query_General_Locations();
            oblist = FXCollections.observableArrayList();
            while (locs.next()) {
                oblist.add(new StringsForTables(locs.getString("City")));
            }
            Location_Table.setItems(oblist);
            ContextMenu Cont1 = new ContextMenu();
            MenuItem Del1 = new MenuItem("Διαγραφή");
            Del1.setOnAction(this::Del_Loc);
            Cont1.getItems().add(Del1);
            Location_Table.setContextMenu(Cont1);
            oblist2 = FXCollections.observableArrayList();
            ResultSet typs = db.Query_General_Types();
            while (typs.next()) {
                oblist2.add(new StringsForTables(typs.getString("Type")));
            }
            Type_Table.setItems(oblist2);
            ContextMenu Cont = new ContextMenu();
            MenuItem Del = new MenuItem("Διαγραφή");
            Del.setOnAction(this::Del_Type);
            Cont.getItems().add(Del);
            Type_Table.setContextMenu(Cont);
            db.Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Del_Type(ActionEvent e) {
        StringsForTables del = Type_Table.getSelectionModel().getSelectedItem();
        Sql db = new Sql();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Επιβαιβέωση");
        alert.setHeaderText("Διαγραφή στοιχείου");
        alert.setContentText("Είσαι σύγουρος ότι θέλεις να διαγράψεις τον τύπο " + del.getString() + "? \nΠΡΟΣΟΧΗ!!!  Όσα όχηματα είναι καταχωρημένα με αυτόν τον τύπο θα διαγράφουν, μάζι με τις καταχωρήσεις τους.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            int i = db.DeleteSecondary(del.getString(), "Types");
            if (i != 1) {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Διαγραφή Απέτυχε");
                alert1.setContentText("Ο τύπος οχήματος δεν κατατάφερε να διαγραφεί, δοκιμάστε ξανά");
                alert1.showAndWait();
            }
        }
        RenewTables();
        db.Disconnect();
    }

    public void Del_Loc(ActionEvent e) {
        System.out.println("Mpike");
        StringsForTables del = Location_Table.getSelectionModel().getSelectedItem();
        Sql db = new Sql();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Επιβαιβέωση");
        alert.setHeaderText("Διαγραφή στοιχείου");
        alert.setContentText("Είσαι σύγουρος ότι θέλεις να διαγράψεις την Τοποθεσία:  " + del.getString() + "  ? \nΠΡΟΣΟΧΗ!!!  Όσα όχηματα είναι καταχωρημένα με αυτην την τοποθεσία θα διαγράφουν, μάζι με τις καταχωρήσεις τους.");
        Optional<ButtonType> result = alert.showAndWait();
        int i = db.DeleteSecondary(del.getString(), "Locations");
        if (i != 1) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Διαγραφή Απέτυχε");
            alert1.setContentText("Ο τύπος οχήματος δεν κατατάφερε να διαγραφεί, δοκιμάστε ξανά");
            alert1.showAndWait();
        }
        RenewTables();
        db.Disconnect();
    }

    @FXML
    void Loc_Add(ActionEvent event) {
        if (!Location_Text.getText().equals("")) {
            Sql db = new Sql();
            int i = db.InsertSecondary(Location_Text.getText(), "Locations");
            if (i != 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Εισαγωγή Απέτυχε");
                alert.setContentText("Η τοποθεσία δεν κατατάφερε να ενταχθεί, δοκιμάστε ξανά");
                alert.showAndWait();
            }
            RenewTables();
            db.Disconnect();
        }
    }


    @FXML
    void Type_Add(ActionEvent event) {
        if (!Type_Text.getText().equals("")) {
            Sql db = new Sql();
            int i = db.InsertSecondary(Type_Text.getText(), "Types");
            if (i != 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Εισαγωγή Απέτυχε");
                alert.setContentText("Ο τύπος οχήματος δεν κατατάφερε να ενταχθεί, δοκιμάστε ξανά");
                alert.showAndWait();
            }
            RenewTables();
            db.Disconnect();
        }
    }

    public void RenewTables(){
        try {
            Sql db = new Sql();
            ResultSet locs = db.Query_General_Locations();
            oblist = FXCollections.observableArrayList();
            while (locs.next()) {
                oblist.add(new StringsForTables(locs.getString("City")));
            }
            Location_Table.setItems(oblist);
            oblist2 = FXCollections.observableArrayList();
            ResultSet typs = db.Query_General_Types();
            while (typs.next()) {
                oblist2.add(new StringsForTables(typs.getString("Type")));
            }
            Type_Table.setItems(oblist2);
            db.Disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
