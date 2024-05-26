package id.rezka.tuprak9;

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
        popUpStage.setTitle("Tambahkan Waktu");
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

        Label pilihTgl = new Label("Atur Tanggal: ");
        pilihTgl.setStyle("-fx-font-size: 15px;");

        Label pilihwktu = new Label("Atur Waktu: ");
        pilihTgl.setStyle("-fx-font-size: 15px;");

        Button simpan = new Button("Save");
        simpan.setStyle("-fx-font-size: 12px;");
        simpan.setPrefWidth(100);
        simpan.setOnAction(e->{
            tanggal = datePicker.getValue();
            waktu = LocalTime.of(jamSpinner.getValue(), menitSpinner.getValue());

            popUpStage.close();
        });

        Button batalkan = new Button("Back");
        batalkan.setStyle("-fx-font-size: 12px;");
        batalkan.setPrefWidth(100);
        batalkan.setOnAction(e-> popUpStage.close());

        HBox tombolWkt = new HBox(100, batalkan, simpan );
        tombolWkt.setAlignment(Pos.CENTER);

        VBox tambahTglWkt = new VBox(10, pilihTgl,datePicker, pilihwktu, jam, tombolWkt);
        tambahTglWkt.setSpacing(10);
        tambahTglWkt.setPadding(new Insets(10));
        tambahTglWkt.setStyle("-fx-background-color: #e3f2fd ;");

        Scene tambahWaktuScene = new Scene(tambahTglWkt, 300, 180);
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
