package id.rezka.tuprak9;

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
    public static Scene createScene(Stage primaryStage, App app, Scene sceneSebelumnya) {
        
        Label catatanLabel = new Label("Tambahkan Jadwal Baru");
        catatanLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
        
        Label judulLabel = new Label("Judul");
        judulLabel.setStyle("-fx-text-fill: white; -fx-font-size: 17;");
        
        TextField jadwalField = new TextField();
        jadwalField.setPromptText("Masukkan judul");
        jadwalField.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 15;");
        jadwalField.setPrefWidth(400);
        jadwalField.setPrefHeight(10);

        Label jPLabel = new Label("Jenis Prioritas");
        jPLabel.setStyle("-fx-text-fill: white; -fx-font-size: 17;");

        VBox labelBox = new VBox(5, catatanLabel, judulLabel, jadwalField, jPLabel);
        labelBox.setAlignment(Pos.CENTER_LEFT);

        Button rendahPrio = new Button("Rendah");
        rendahPrio.setPrefWidth(100);
        rendahPrio.setStyle("-fx-font-size: 12px;");

        Button sedangPrio = new Button("Sedang");
        sedangPrio.setPrefWidth(100);
        sedangPrio.setStyle("-fx-font-size: 12px;");

        Button tinggiPrio = new Button("Tinggi");
        tinggiPrio.setPrefWidth(100);
        tinggiPrio.setStyle("-fx-font-size: 12px;");

        HBox jPbutton = new HBox(10,rendahPrio, sedangPrio, tinggiPrio);
        jPbutton.setAlignment(Pos.CENTER);
        jPbutton.setSpacing(10);

        Label detailLabel = new Label("Detail");
        detailLabel.setStyle("-fx-text-fill: white; -fx-font-size: 17;");

        Button tambahWaktu = new Button("Tambahkan waktu");
        tambahWaktu.setPrefWidth(200);
        tambahWaktu.setStyle("-fx-font-size: 12px;");
        tambahWaktu.setOnAction(e ->{

        });
        Button tambahDeskrip = new Button("Tambahkan Deskripsi");
        tambahDeskrip.setPrefWidth(200);
        tambahDeskrip.setStyle("-fx-font-size: 12px;");
        tambahDeskrip.setOnAction(e ->{
            // dlm proses
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

        VBox layout = new VBox(10, labelBox, jPbutton, detailVBox, tombol);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setStyle("-fx-background-color: #888a8e ; -fx-font-weight: bold; -fx-font-size: 25px");
        layout.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        
        VBox notifBox = new VBox( layout);
        notifBox.setTranslateY(NotifInputJadwal.NOTIFICATION_HEIGHT);
        NotifInputJadwal.slideAtas(notifBox);

        saveButton.setOnAction(e -> {
            App.setJadwal(jadwalField.getText()); // otw sql
            NotifInputJadwal.slidekeBawah(notifBox);
        });
        
        backButton.setOnAction(e -> {
            NotifInputJadwal.slidekeBawah(notifBox);
        });


        StackPane mainLayoutAddSch = new StackPane();
        mainLayoutAddSch.getChildren().addAll(sceneSebelumnya.getRoot(), notifBox);
        

        return new Scene(mainLayoutAddSch, 500, 600);
    }
}
