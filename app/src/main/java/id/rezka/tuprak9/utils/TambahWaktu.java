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

    // Metode untuk menampilkan pop-up menambahkan waktu dan tanggal
    public static Stage tambahWaktuTanggal(Stage primaryStage) {
        // Membuat stage baru untuk pop-up
        Stage popUpStage = new Stage();
        // Mengatur judul dari pop-up
        popUpStage.setTitle("Add Time");
        // Mengatur owner dari pop-up
        popUpStage.initOwner(primaryStage);
        // Mengatur modality agar pop-up bersifat modal
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        // Mengatur style dari pop-up
        popUpStage.initStyle(StageStyle.UTILITY);

        // Membuat DatePicker untuk memilih tanggal
        datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());

        // Membuat Spinner untuk memilih jam dan menit
        Spinner<Integer> jamSpinner = new Spinner<>();
        Spinner<Integer> menitSpinner = new Spinner<>();

        // Mengatur nilai awal dan rentang nilai untuk Spinner jam dan menit
        jamSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, LocalTime.now().getHour()));
        menitSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, LocalTime.now().getMinute()));

        // Mengatur layout untuk Spinner jam dan menit
        HBox jam = new HBox(5, jamSpinner, menitSpinner);

        // Membuat label untuk instruksi memilih tanggal
        Label pilihTgl = new Label("Set the date: ");
        pilihTgl.setId("tanggal-waktu");

        // Membuat label untuk instruksi memilih waktu
        Label pilihwktu = new Label("Set the time: ");
        pilihwktu.setId("tanggal-waktu");

        // Membuat tombol Save
        Button simpan = new Button("Save");
        simpan.setId("save-btn");
        simpan.setPrefWidth(100);
        // Mengatur aksi tombol Save
        simpan.setOnAction(e -> {
            // Menyimpan nilai tanggal dan waktu yang dipilih
            tanggal = datePicker.getValue();
            waktu = LocalTime.of(jamSpinner.getValue(), menitSpinner.getValue());

            // Menutup pop-up
            popUpStage.close();
        });

        // Membuat tombol Back
        Button batalkan = new Button("Back");
        batalkan.setId("back-btn");
        batalkan.setPrefWidth(100);
        // Mengatur aksi tombol Back
        batalkan.setOnAction(e -> popUpStage.close());

        // Mengatur layout untuk tombol Save dan Back
        HBox tombolWkt = new HBox(100, batalkan, simpan);
        tombolWkt.setAlignment(Pos.CENTER);

        // Mengatur layout utama untuk pop-up
        VBox tambahTglWkt = new VBox(10, pilihTgl, datePicker, pilihwktu, jam, tombolWkt);
        tambahTglWkt.setSpacing(10);
        tambahTglWkt.setPadding(new Insets(10));
        tambahTglWkt.setId("tambahTgl-wkt");

        // Membuat scene untuk pop-up 
        Scene tambahWaktuScene = new Scene(tambahTglWkt, 300, 180);
        // Menambahkan stylesheet untuk styling
        tambahWaktuScene.getStylesheets().add("/styles/stylesTambahWaktu.css");
        // Mengatur scene dari stage pop-up
        popUpStage.setScene(tambahWaktuScene);

        // Mengembalikan objek stage pop-up
        return popUpStage;
    }

    // Metode untuk mendapatkan tanggal yang tersimpan
    public static LocalDate getTanggal() {
        return tanggal;
    }

    // Metode untuk mendapatkan waktu yang tersimpan
    public static LocalTime getWaktu() {
        return waktu;
    }

    // Metode untuk mereset nilai tanggal dan waktu yang tersimpan
    public static void reset() {
        tanggal = null;
        waktu = null;
    }
}
