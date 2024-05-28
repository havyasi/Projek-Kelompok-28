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
        
        Label catatanLabel = new Label("Tambahkan Jadwal Baru");
        catatanLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20px;");
        
        Label judulLabel = new Label("Judul");
        judulLabel.setStyle("-fx-text-fill: black; -fx-font-size: 17;");
        
        TextField jadwalField = new TextField();
        jadwalField.setPromptText("Masukkan judul");
        jadwalField.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 15;");
        jadwalField.setPrefWidth(400);
        jadwalField.setPrefHeight(10);

        Label jPLabel = new Label("Jenis Prioritas");
        jPLabel.setStyle("-fx-text-fill: black; -fx-font-size: 17;");

        VBox labelBox = new VBox(5, catatanLabel, judulLabel, jadwalField, jPLabel);
        labelBox.setAlignment(Pos.CENTER_LEFT);

        rendahPrio = new Button("Rendah");
        rendahPrio.setPrefWidth(100);
        rendahPrio.setStyle("-fx-font-size: 12px; -fx-background-color: #a5d6a7; -fx-text-fill: black; -fx-background-radius: 5;");
        rendahPrio.setOnMouseClicked(e -> {
            rendahPrio.setStyle("-fx-font-size: 12px; -fx-background-color: #66bb64; -fx-text-fill: white; -fx-background-radius: 5;");
            sedangPrio.setStyle("-fx-font-size: 12px; -fx-background-color: #ffe082; -fx-text-fill: black; -fx-background-radius: 5;");
            tinggiPrio.setStyle("-fx-font-size: 12px; -fx-background-color: #ffab91; -fx-text-fill: black; -fx-background-radius: 5;");
        });
        rendahPrio.setOnAction(e -> {
            jenisPrioritas = "rendah";
        });

        sedangPrio = new Button("Sedang");
        sedangPrio.setPrefWidth(100);
        sedangPrio.setStyle("-fx-font-size: 12px; -fx-background-color: #ffe082; -fx-text-fill: black; -fx-background-radius: 5;");
        sedangPrio.setOnMouseClicked(e -> {
            rendahPrio.setStyle("-fx-font-size: 12px; -fx-background-color: #a5d6a7; -fx-text-fill: black; -fx-background-radius: 5;");
            sedangPrio.setStyle("-fx-font-size: 12px; -fx-background-color: #ffca28; -fx-text-fill: white; -fx-background-radius: 5;");
            tinggiPrio.setStyle("-fx-font-size: 12px; -fx-background-color: #ffab91; -fx-text-fill: black; -fx-background-radius: 5;");
        });
        sedangPrio.setOnAction(e -> {
            jenisPrioritas = "sedang";
        });

        tinggiPrio = new Button("Tinggi");
        tinggiPrio.setPrefWidth(100);
        tinggiPrio.setStyle("-fx-font-size: 12px; -fx-background-color: #ffab91; -fx-text-fill: black; -fx-background-radius: 5;");
        tinggiPrio.setOnMouseClicked(e -> {
            rendahPrio.setStyle("-fx-font-size: 12px; -fx-background-color: #a5d6a7; -fx-text-fill: black; -fx-background-radius: 5;");
            sedangPrio.setStyle("-fx-font-size: 12px; -fx-background-color: #ffe082; -fx-text-fill: black; -fx-background-radius: 5;");
            tinggiPrio.setStyle("-fx-font-size: 12px; -fx-background-color: #ff7043; -fx-text-fill: white; -fx-background-radius: 5;");
        });
        tinggiPrio.setOnAction(e -> {
            jenisPrioritas = "tinggi";
        });

        HBox jPbutton = new HBox(10,rendahPrio, sedangPrio, tinggiPrio);
        jPbutton.setAlignment(Pos.CENTER);
        jPbutton.setSpacing(10);

        Label detailLabel = new Label("Detail");
        detailLabel.setStyle("-fx-text-fill: black; -fx-font-size: 17;");

        Button tambahWaktu = new Button("Tambahkan waktu");
        tambahWaktu.setPrefWidth(500);
        tambahWaktu.setStyle("-fx-font-size: 12px;");

        tambahWaktu.setOnAction(e -> {
            TambahWaktu.tambahWaktuTanggal(primaryStage).showAndWait();
        });

        Button tambahDeskrip = new Button("Tambahkan Deskripsi");
        tambahDeskrip.setPrefWidth(500);
        tambahDeskrip.setStyle("-fx-font-size: 12px;");
        tambahDeskrip.setOnAction(e ->{
            TambahDeskripsi.tambahDeskrip(primaryStage).showAndWait();
        });        

        VBox detailVBox = new VBox(10, detailLabel,tambahWaktu ,tambahDeskrip);

        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-font-size: 12px;");
        saveButton.setPrefWidth(80);

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 12px;");
        backButton.setPrefWidth(80);
        
        HBox tombol = new HBox(340, backButton, saveButton);
        tombol.setAlignment(Pos.CENTER);

        VBox layout = new VBox(12, labelBox, jPbutton, detailVBox, tombol);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setStyle("-fx-background-color: #90caf9 ; -fx-font-weight: bold; -fx-font-size: 25px");
        layout.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        
        VBox notifBox = new VBox( layout);
        notifBox.setTranslateY(NotifInputJadwal.NOTIFICATION_HEIGHT);
        NotifInputJadwal.slideAtas(notifBox, sceneSebelumnya);

        saveButton.setOnAction(e ->{
            String judul = jadwalField.getText();
            LocalDate tanggal = TambahWaktu.getTanggal();
            LocalTime waktu = TambahWaktu.getWaktu();
            String deskripsi = TambahDeskripsi.getDeskripsi();

            String pesanError = null;
            if (judul.isEmpty() && jenisPrioritas == null && tanggal == null && waktu == null) {
                pesanError = "Judul, Jenis Prioritas, Tanggal dan Waktu tidak boleh kosong.";
            } else if (judul.isEmpty()) {
                pesanError = "Judul tidak boleh kosong.";
            } else if (jenisPrioritas == null) {
                pesanError = "Jenis Prioritas tidak boleh kosong.";
            } else if (tanggal == null && waktu == null) {
                pesanError = "Tanggal dan Waktu tidak boleh kosong.";
            }

            if (pesanError != null) {
                NotifInputJadwal.showErrorPopup(primaryStage, pesanError);
            } else {
                DbManager.saveData(judul, jenisPrioritas, tanggal, waktu, deskripsi);
                NotifInputJadwal.slidekeBawah(notifBox, sceneSebelumnya);
            }
        });
        
        backButton.setOnAction(e -> {
            NotifInputJadwal.slidekeBawah(notifBox,sceneSebelumnya);
        });

        StackPane rootSebelumnya = (StackPane) sceneSebelumnya.getRoot();
        rootSebelumnya.getChildren().forEach(node -> node.setDisable(true));

        StackPane mainLayoutAddSch = new StackPane();
        mainLayoutAddSch.getChildren().addAll(sceneSebelumnya.getRoot(), notifBox);
        

        return new Scene(mainLayoutAddSch, 500, 600);
    }
}
