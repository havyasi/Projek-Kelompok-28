package id.rezka.tuprak9;

import java.time.LocalDate;
import java.time.LocalTime;


import id.rezka.tuprak9.controller.DbManager;
import id.rezka.tuprak9.utils.NotifInputJadwal;
import id.rezka.tuprak9.utils.TambahDeskripsi;
import id.rezka.tuprak9.utils.TambahWaktu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class InputJadwal {
    private static String jenisPrioritas = null;
    private static Button rendahPrio;
    private static Button sedangPrio;
    private static Button tinggiPrio;

    public static Scene createScene(Stage primaryStage, App app, Scene sceneSebelumnya) {
        jenisPrioritas = null;
        TambahWaktu.reset();
        
        // Label untuk judul form "Add New Schedule"
        Label catatanLabel = new Label("Add New Schedule");
        catatanLabel.setId("cttn-label");
        
        // Label untuk "Title"
        Label judulLabel = new Label("Title");
        judulLabel.setId("judul-label;");
        
        // TextField untuk memasukkan judul jadwal
        TextField jadwalField = new TextField();
        jadwalField.setPromptText("Enter The Title");
        jadwalField.setId("jdwl-field");
        jadwalField.setPrefWidth(400);
        jadwalField.setPrefHeight(10);

        // Membatasi panjang input judul hingga 50 karakter
        jadwalField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 50) {
                jadwalField.setText(oldValue);
            }
        });

        // Label untuk "Type Of Priority"
        Label jPLabel = new Label("Type Of Priority");
        jPLabel.setId("prioritas-label");

        // VBox untuk menampung label dan input judul
        VBox labelBox = new VBox(5, catatanLabel, judulLabel, jadwalField, jPLabel);
        labelBox.setAlignment(Pos.CENTER_LEFT);

        // Button untuk prioritas rendah
        rendahPrio = new Button("Low");
        rendahPrio.setPrefWidth(100);
        rendahPrio.setId("btn-rendah");
        rendahPrio.setOnMouseClicked(e -> {
            rendahPrio.setId("click-rendah");
            sedangPrio.setId("btn-sedang");
            tinggiPrio.setId("btn-tinggi");
        });
        rendahPrio.setOnAction(e -> {
            jenisPrioritas = "Low";
        });

        // Button untuk prioritas sedang
        sedangPrio = new Button("Medium");
        sedangPrio.setPrefWidth(100);
        sedangPrio.setId("btn-sedang");
        sedangPrio.setOnMouseClicked(e -> {
            rendahPrio.setId("btn-rendah");
            sedangPrio.setId("click2-sedang");
            tinggiPrio.setId("btn-tinggi");
        });
        sedangPrio.setOnAction(e -> {
            jenisPrioritas = "Medium";
        });

        // Button untuk prioritas tinggi        
        tinggiPrio = new Button("High");
        tinggiPrio.setPrefWidth(100);
        tinggiPrio.setId("btn-tinggi");
        tinggiPrio.setOnMouseClicked(e -> {
            rendahPrio.setId("btn-rendah");
            sedangPrio.setId("btn-sedang");
            tinggiPrio.setId("click3-tinggi");
        });
        tinggiPrio.setOnAction(e -> {
            jenisPrioritas = "High";
        });

        // HBox untuk menampung tombol prioritas
        HBox jPbutton = new HBox(10,rendahPrio, sedangPrio, tinggiPrio);
        jPbutton.setAlignment(Pos.CENTER);
        jPbutton.setSpacing(10);

        // Label untuk "Details"
        Label detailLabel = new Label("Details");
        detailLabel.setId("detail-label");

        // Button untuk menambahkan waktu
        Button tambahWaktu = new Button("Add Time");
        tambahWaktu.setPrefWidth(500);
        tambahWaktu.setId("btn-tmbhwkt");

        tambahWaktu.setOnAction(e -> {
            TambahWaktu.tambahWaktuTanggal(primaryStage).showAndWait();
        });

        // Button untuk menambahkan deskripsi
        Button tambahDeskrip = new Button("Add a description");
        tambahDeskrip.setPrefWidth(500);
        tambahDeskrip.setId("btn-deskripsi");
        tambahDeskrip.setOnAction(e ->{
            TambahDeskripsi.tambahDeskrip(primaryStage).showAndWait();
        });        

        // VBox untuk menampung detail label, tambah waktu, dan tambah deskripsi
        VBox detailVBox = new VBox(10, detailLabel,tambahWaktu ,tambahDeskrip);

        // Button untuk menyimpan jadwal
        Button saveButton = new Button("Save");
        saveButton.setId("btn-save");
        saveButton.setPrefWidth(80);

        // Button untuk kembali ke scene sebelumnya
        Button backButton = new Button("Back");
        backButton.setId("btn-back");
        backButton.setPrefWidth(80);
        
        // HBox untuk menampung tombol kembali dan tombol simpan
        HBox tombol = new HBox(340, backButton, saveButton);
        tombol.setAlignment(Pos.CENTER);

        // VBox untuk mengatur seluruh layout
        VBox layout = new VBox(12, labelBox, jPbutton, detailVBox, tombol);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setId("lyt-jadwal");
        layout.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        
        // VBox untuk menampilkan notifikasi
        VBox notifBox = new VBox( layout);
        notifBox.setTranslateY(NotifInputJadwal.NOTIFICATION_HEIGHT);
        NotifInputJadwal.slideAtas(notifBox, sceneSebelumnya);

        // Event handler untuk tombol save
        saveButton.setOnAction(e ->{
            String judul = jadwalField.getText().trim();
            LocalDate tanggal = TambahWaktu.getTanggal();
            LocalTime waktu = TambahWaktu.getWaktu();
            String deskripsi = TambahDeskripsi.getDeskripsi();

            // Validasi input
            String pesanError = null;
            if (judul.isEmpty() && jenisPrioritas == null && tanggal == null && waktu == null) {
                pesanError = "Title, Priority Type, Date and Time must not be empty.";
            } else if (judul.isEmpty()) {
                pesanError = "The Title cannot be empty.";
            } else if (jenisPrioritas == null) {
                pesanError = "Priority type cannot be empty.";
            } else if (tanggal == null && waktu == null) {
                pesanError = "Date and Time cannot be empty.";
            }

            // Menampilkan pesan error atau menyimpan data ke database
            if (pesanError != null) {
                NotifInputJadwal.showErrorPopup(primaryStage, pesanError);
            } else {
                DbManager.saveData(judul, jenisPrioritas, tanggal, waktu, deskripsi);
                NotifInputJadwal.slidekeBawah(notifBox, sceneSebelumnya);
            }
        });
        
         // Event handler untuk tombol back
        backButton.setOnAction(e -> {
            NotifInputJadwal.slidekeBawah(notifBox,sceneSebelumnya);
        });

         // Menonaktifkan elemen-elemen di scene sebelumnya
        StackPane rootSebelumnya = (StackPane) sceneSebelumnya.getRoot();
        rootSebelumnya.getChildren().forEach(node -> node.setDisable(true));

        // StackPane untuk menampung scene sebelumnya dan notifikasi
        StackPane mainLayoutAddSch = new StackPane();
        mainLayoutAddSch.getChildren().addAll(sceneSebelumnya.getRoot(), notifBox);
        

        // Membuat scene baru dengan layout yang sudah diatur dan menambahkan stylesheet
        Scene scene = new Scene(mainLayoutAddSch, 500, 600);
        scene.getStylesheets().add("/styles/stylesInputJadwal.css");
        return scene;
    }
}
