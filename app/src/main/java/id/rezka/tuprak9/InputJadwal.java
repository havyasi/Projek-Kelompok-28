package id.rezka.tuprak9;

import java.time.LocalDate;
import java.time.LocalTime;

import org.checkerframework.checker.units.qual.s;

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
        
        Label catatanLabel = new Label("Add New Schedule");
        catatanLabel.setId("cttn-label");
        
        Label judulLabel = new Label("Title");
        judulLabel.setId("judul-label;");
        
        TextField jadwalField = new TextField();
        jadwalField.setPromptText("Enter The Title");
        jadwalField.setId("jdwl-field");
        jadwalField.setPrefWidth(400);
        jadwalField.setPrefHeight(10);

        jadwalField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 50) {
                jadwalField.setText(oldValue);
            }
        });

        Label jPLabel = new Label("Type Of Priority");
        jPLabel.setId("prioritas-label");

        VBox labelBox = new VBox(5, catatanLabel, judulLabel, jadwalField, jPLabel);
        labelBox.setAlignment(Pos.CENTER_LEFT);

        rendahPrio = new Button("Low");
        rendahPrio.setPrefWidth(100);
        rendahPrio.setId("btn-rendah");
        rendahPrio.setOnMouseClicked(e -> {
            rendahPrio.setId("click-rendah");
            sedangPrio.setId("click-sedang");
            tinggiPrio.setId("click-tinggi");
        });
        rendahPrio.setOnAction(e -> {
            jenisPrioritas = "rendah";
        });

        sedangPrio = new Button("Medium");
        sedangPrio.setPrefWidth(100);
        sedangPrio.setId("btn-sedang");
        sedangPrio.setOnMouseClicked(e -> {
            rendahPrio.setId("click2-rendah");
            sedangPrio.setId("click2-sedang");
            tinggiPrio.setId("click2-tinggi");
        });
        sedangPrio.setOnAction(e -> {
            jenisPrioritas = "sedang";
        });

        tinggiPrio = new Button("High");
        tinggiPrio.setPrefWidth(100);
        tinggiPrio.setId("btn-tinggi");
        tinggiPrio.setOnMouseClicked(e -> {
            rendahPrio.setId("click3-rendah");
            sedangPrio.setId("click3-sedang");
            tinggiPrio.setId("click3-tinggi");
        });
        tinggiPrio.setOnAction(e -> {
            jenisPrioritas = "tinggi";
        });

        HBox jPbutton = new HBox(10,rendahPrio, sedangPrio, tinggiPrio);
        jPbutton.setAlignment(Pos.CENTER);
        jPbutton.setSpacing(10);

        Label detailLabel = new Label("Details");
        detailLabel.setId("detail-label");

        Button tambahWaktu = new Button("Add Time");
        tambahWaktu.setPrefWidth(500);
        tambahWaktu.setId("btn-tmbhwkt");

        tambahWaktu.setOnAction(e -> {
            TambahWaktu.tambahWaktuTanggal(primaryStage).showAndWait();
        });

        Button tambahDeskrip = new Button("Add a description");
        tambahDeskrip.setPrefWidth(500);
        tambahDeskrip.setId("btn-deskripsi");
        tambahDeskrip.setOnAction(e ->{
            TambahDeskripsi.tambahDeskrip(primaryStage).showAndWait();
        });        

        VBox detailVBox = new VBox(10, detailLabel,tambahWaktu ,tambahDeskrip);

        Button saveButton = new Button("Save");
        saveButton.setId("btn-save");
        saveButton.setPrefWidth(80);

        Button backButton = new Button("Back");
        backButton.setId("btn-back");
        backButton.setPrefWidth(80);
        
        HBox tombol = new HBox(340, backButton, saveButton);
        tombol.setAlignment(Pos.CENTER);

        VBox layout = new VBox(12, labelBox, jPbutton, detailVBox, tombol);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setId("lyt-jadwal");
        layout.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        
        VBox notifBox = new VBox( layout);
        notifBox.setTranslateY(NotifInputJadwal.NOTIFICATION_HEIGHT);
        NotifInputJadwal.slideAtas(notifBox, sceneSebelumnya);

        saveButton.setOnAction(e ->{
            String judul = jadwalField.getText().trim();
            LocalDate tanggal = TambahWaktu.getTanggal();
            LocalTime waktu = TambahWaktu.getWaktu();
            String deskripsi = TambahDeskripsi.getDeskripsi();

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
        

        Scene scene = new Scene(mainLayoutAddSch, 500, 600);
        scene.getStylesheets().add("/styles/stylesInputJadwal.css");
        return scene;
    }
}
