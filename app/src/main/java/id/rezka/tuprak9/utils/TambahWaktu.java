package id.rezka.tuprak9.utils;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TambahWaktu {
    private static DatePicker datePicker;
    private static LocalDate tanggal;
    private static LocalTime waktu;

    public static Stage tambahWaktuTanggal(Stage primaryStage){

        Stage popUpStage = new Stage();
        popUpStage.setTitle("Add Time");
        popUpStage.initOwner(primaryStage);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initStyle(StageStyle.UTILITY);

        datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());

        Spinner<Integer> jamSpinner = new Spinner<>();
        Spinner<Integer> menitSpinner = new Spinner<>();

        jamSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, LocalTime.now().getHour()));
        menitSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, LocalTime.now().getMinute()));

        HBox jam = new HBox(5, jamSpinner, menitSpinner);

        Label pilihTgl = new Label("Set the date: ");
        pilihTgl.setId("tanggal-waktu");

        Label pilihwktu = new Label("Set the time: ");
        pilihwktu.setId("tanggal-waktu");

        Button simpan = new Button("Save");
        simpan.setId("save-btn");
        simpan.setPrefWidth(100);
        simpan.setOnAction(e->{
            tanggal = datePicker.getValue();
            waktu = LocalTime.of(jamSpinner.getValue(), menitSpinner.getValue());

            popUpStage.close();
        });

        Button batalkan = new Button("Back");
        batalkan.setId("back-btn");
        batalkan.setPrefWidth(100);
        batalkan.setOnAction(e-> popUpStage.close());

        HBox tombolWkt = new HBox(100, batalkan, simpan );
        tombolWkt.setAlignment(Pos.CENTER);

        VBox tambahTglWkt = new VBox(10, pilihTgl,datePicker, pilihwktu, jam, tombolWkt);
        tambahTglWkt.setSpacing(10);
        tambahTglWkt.setPadding(new Insets(10));
        tambahTglWkt.setId("tambahTgl-wkt");

        Scene tambahWaktuScene = new Scene(tambahTglWkt, 300, 180);
        tambahWaktuScene.getStylesheets().add("/styles/stylesTambahWaktu.css");
        popUpStage.setScene(tambahWaktuScene);

        return popUpStage ;
    }
    
    public static LocalDate getTanggal() {
        return tanggal;
    }
    
    public static LocalTime getWaktu() {
        return waktu;
    }
}
