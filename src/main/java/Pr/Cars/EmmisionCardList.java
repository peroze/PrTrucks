package Pr.Cars;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class is the controller for EmmisionCardList.fxml which shows the emmision cards of the cars
 *
 * @author peroze
 * @version 1.0 Alpha
 */
public class EmmisionCardList implements Initializable {


    RotateTransition rotate;

    @FXML
    private TableView<ModelEmmisionCard> Truck_Table;

    @FXML
    private TableColumn<ModelEmmisionCard, String> LisancePlate_Column;

    @FXML
    private TableColumn<ModelEmmisionCard, String> Date_Column;

    @FXML
    private TableColumn<ModelEmmisionCard, String> Kilometers_Column;


    @FXML
    private TableColumn<ModelEmmisionCard, String> Next_Column;


    @FXML
    private TableColumn<ModelEmmisionCard, String> KTEO_Column;

    @FXML
    private ImageView Import_Button;

    @FXML
    private Button Delete_Button;

    @FXML
    private TextField Search_Bar;

    private ObservableList<ModelEmmisionCard> Oblist;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Sql db = new Sql();
        ResultSet rs = db.Query_General_EmmisionCard();
        Oblist = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Oblist.add(new ModelEmmisionCard(db.GetLisxxFromId(rs.getString("id")), rs.getString("Kilometers"), rs.getString("Date"), db.BooleantoGreek(rs.getBoolean("WithKTEO")), rs.getString("NextDate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LisancePlate_Column.setCellValueFactory(new PropertyValueFactory<>("LiscPlate"));
        KTEO_Column.setCellValueFactory(new PropertyValueFactory<>("WithKTEO"));
        Date_Column.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Kilometers_Column.setCellValueFactory(new PropertyValueFactory<>("Kilometers"));
        Next_Column.setCellValueFactory(new PropertyValueFactory<>("Next"));
        FilteredList<ModelEmmisionCard> Filter = new FilteredList<>(Oblist, b -> true);

        Search_Bar.textProperty().addListener((observable, oldValue, newValue) -> {
            Filter.setPredicate(ModelEmmisionCard -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String LowerCase = newValue.toLowerCase();
                if (ModelEmmisionCard.getLiscPlate().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelEmmisionCard.getDate().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelEmmisionCard.getKilometers().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelEmmisionCard.getNext().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelEmmisionCard.getWithKTEO().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                }
                return false;
            });
        });
        SortedList<ModelEmmisionCard> sorted = new SortedList<>(Filter);
        sorted.comparatorProperty().bind(Truck_Table.comparatorProperty());
        Truck_Table.setItems(sorted);
        ContextMenu Cont = new ContextMenu();
        MenuItem Del = new MenuItem("Διαγραφή");
        Del.setOnAction(this::Delete_Button_Pressed);
        Cont.getItems().add(Del);
        Truck_Table.setContextMenu(Cont);
        db.Disconnect();
    }


    /**
     * This method is called when the import button is pressed and it adds a new Emmision Card in the database
     *
     * @param event The event
     */
    @FXML
    void Import_Button_Pressed(MouseEvent event) {
        Stage primaryStage = new Stage();
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("AddEmmisionCard.fxml"));
        try {
            Parent root = fxmlloader.load();
            primaryStage.setOnHidden(e -> {
                RenewTable();
            });
            primaryStage.setOnCloseRequest(e -> {
                RenewTable();
            });
            primaryStage.setTitle("PrTrucks");
            primaryStage.setScene(new Scene(root, 600, 580));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
            //fxmlloader.<Drawer>getController().setParent(this);

            //Border_Pane.setLeft(root2);
            //new FadeIn(root).play();
            //new FadeIn(root2).play();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is called when the delete button is pressed and it delletes the selected card from the database
     *
     * @param event The event
     */
    @FXML
    void Delete_Button_Pressed(ActionEvent event) {
        ModelEmmisionCard temp = Truck_Table.getSelectionModel().getSelectedItem();
        Sql sql = new Sql();
        try {
            if (!(temp == null)) {
                int tempKm = Integer.valueOf(temp.getKilometers());
                String tempLisc = temp.getLiscPlate();
                int km = sql.Query_Specific_NextServiceKmCurrentKm(sql.GetIdFromLisx(temp.getLiscPlate())).getInt("Kilometers");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Επιβαιβέωση");
                alert.setHeaderText("Διαγραφή στοιχείου");
                alert.setContentText("Είσαι σύγουρος ότι θέλεις να διαγράψεις την Κάρτα του οχήματος με πινακίδα " + temp.getLiscPlate() + ".");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    boolean fl = false;
                    if (tempKm == km) {
                        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                        alert1.setTitle("Επιβαιβέωση");
                        alert1.setHeaderText("Εγγράφη με τσ περισσότερα Χιλιομέτρα");
                        alert1.setContentText("Αυτή η εγγράφη  είναι η κατάχώρηση με τα περισότερα χιλίομετρα για το Όχημα.\nΘέλεις να αλλάχθούν και αυτά με τα αμέσως λιγότερα χιλιόμετρα ή να παραμείνουν όπως είναι ");          // Διαγραφή
                        Optional<ButtonType> result1 = alert1.showAndWait();
                        if (result1.get() == ButtonType.OK) {
                            fl = true;
                        }
                    }
                    int k = sql.DeleteEmissionCard(temp.getLiscPlate());
                    if (k == 0) {
                        throw new SQLException();
                    }
                    if (k == 1) {
                        if (fl == true) {
                            ResultSet rs1 = sql.Query_Specific_MaxKmAllMinusTrucks(sql.GetIdFromLisx(tempLisc));
                            int newKM = rs1.getInt("MAX(KM)");
                            ResultSet rs = sql.Query_Specific_Trucks(tempLisc);
                            ModelTruck repl = new ModelTruck(rs.getString("id"), rs.getString("LiscPlate"), rs.getString("Manufactor"), rs.getString("Model"), rs.getString("First_Date"), rs.getString("Plaisio"), rs.getString("Type"), rs.getString("Location"), String.valueOf(newKM), rs.getString("Data"), rs.getString("ServiceInKm"), rs.getString("KTEOIn"), rs.getString("GasIn"));
                            int i = sql.InsertCar(repl, -1, true);
                            if (i == 0) {
                                throw new SQLException();
                            }
                        }
                    }
                    RenewTable();
                }

            }
        } catch (SQLException e) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("Αποτυχία");
            alert2.setContentText("Η διαγραφή απέτυχε, προσπαθύστε ξανά");
            alert2.showAndWait();
        }
    }

    /**
     * This method is used to activate the animation of the add button when the user hovers over it
     *
     * @param event The event
     */
    @FXML
    void Import_Button_Hover(MouseEvent event) {

        rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(360);
        rotate.setCycleCount(1);
        rotate.setDuration(Duration.millis(1000));
        rotate.setAutoReverse(true);
        rotate.setNode(Import_Button);
        rotate.play();
        rotate.statusProperty().addListener(new ChangeListener<Animation.Status>() {
            @Override
            public void changed(ObservableValue<? extends Animation.Status> observable, Animation.Status oldValue, Animation.Status newValue) {
                if (newValue == Animation.Status.STOPPED) {
                    if (Import_Button.getRotate() != 0 || Import_Button.getRotate() != 360) {
                        RotateTransition transition = new RotateTransition(Duration.seconds(1), Import_Button);
                        transition.setFromAngle(Import_Button.getRotate());
                        transition.setToAngle(0);
                        transition.setCycleCount(1);
                        transition.setAutoReverse(true);
                        transition.play();
                    }
                }
            }
        });
    }

    /**
     * This method renews the table
     */
    public void RenewTable() {
        Sql db = new Sql();
        ResultSet rs = db.Query_General_EmmisionCard();
        Oblist = FXCollections.observableArrayList();
        try {
            while (rs.next()) {
                Oblist.add(new ModelEmmisionCard(db.GetLisxxFromId(rs.getString("id")), rs.getString("Kilometers"), rs.getString("Date"), db.BooleantoGreek(rs.getBoolean("WithKTEO")), rs.getString("NextDate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LisancePlate_Column.setCellValueFactory(new PropertyValueFactory<>("LiscPlate"));
        KTEO_Column.setCellValueFactory(new PropertyValueFactory<>("WithKTEO"));
        Date_Column.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Kilometers_Column.setCellValueFactory(new PropertyValueFactory<>("Kilometers"));
        Next_Column.setCellValueFactory(new PropertyValueFactory<>("Next"));
        FilteredList<ModelEmmisionCard> Filter = new FilteredList<>(Oblist, b -> true);

        Search_Bar.textProperty().addListener((observable, oldValue, newValue) -> {
            Filter.setPredicate(ModelEmmisionCard -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String LowerCase = newValue.toLowerCase();
                if (ModelEmmisionCard.getLiscPlate().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelEmmisionCard.getDate().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelEmmisionCard.getKilometers().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelEmmisionCard.getNext().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                } else if (ModelEmmisionCard.getWithKTEO().toLowerCase().indexOf(LowerCase) != -1) {
                    return true;
                }
                return false;
            });
        });
        SortedList<ModelEmmisionCard> sorted = new SortedList<>(Filter);
        sorted.comparatorProperty().bind(Truck_Table.comparatorProperty());
        Truck_Table.setItems(sorted);
        db.Disconnect();
    }
}

