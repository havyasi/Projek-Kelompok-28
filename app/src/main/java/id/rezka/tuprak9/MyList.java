package id.rezka.tuprak9;

import java.util.List;

import id.rezka.tuprak9.controller.DbManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyList {
    private static VBox list;
    // Metode createScene membuat dan mengembalikan sebuah Scene yang menampilkan daftar jadwal.
    public static Scene createScene(Stage primaryStage, App app) {
        
        // Membuat label untuk judul "My List"
        Label myListLabel = new Label("My List");
        myListLabel.setId("search-label");
    

        // nampilkan semua list schedule scroll panenya nanti tmpil kalau dtanya lewat dri ukuran stageny kita
        // pke sql


        // Membuat ScrollPane untuk menampung daftar jadwal. ScrollPane memungkinkan konten untuk digulir jika lebih besar dari ukuran tampilan.
        ScrollPane scroll= new ScrollPane();
        scroll.setId("scroll-pane");

        // Membuat VBox untuk menampung Label yang berisi jadwal. VBox akan menampilkan komponen secara vertikal dengan jarak 10 piksel.
        list = new VBox(10);
        list.setPadding(new Insets(10)); // Menetapkan padding di sekitar VBox
        list.setId("list-box"); // Menetapkan ID untuk keperluan styling dengan CSS

        // upadate data terbaru
        upadateList(primaryStage);

        // Menetapkan konten dari ScrollPane dengan VBox yang berisi daftar jadwal
        scroll.setContent(list);
        scroll.setFitToWidth(true); // Menetapkan agar konten sesuai dengan lebar ScrollPane

        // Membuat tombol kembali untuk kembali ke scene utama
        Button backButton = new Button("Back");
        backButton.setId("back-btn");
        backButton.setOnAction(e -> primaryStage.setScene(app.createMainScene(primaryStage)));

        // Mengatur layout menggunakan BorderPane
        BorderPane layout = new BorderPane(); 
        layout.setTop(myListLabel); // Menetapkan label "My List" di bagian atas
        layout.setCenter(scroll); // Menetapkan ScrollPane di bagian tengah
        layout.setBottom(backButton); // Menetapkan tombol kembali di bagian bawah
        BorderPane.setAlignment(myListLabel, Pos.TOP_CENTER);
        BorderPane.setAlignment(scroll, Pos.TOP_CENTER);
        BorderPane.setMargin(myListLabel, new Insets(20, 0, 20, 0));
        BorderPane.setAlignment(backButton, Pos.CENTER);
        BorderPane.setMargin(backButton, new Insets(0, 20, 20, 20));
        layout.setId("lyt-list");// Menetapkan ID untuk keperluan styling dengan CSS

         // Membuat Scene baru dengan layout yang sudah dibuat, dan menetapkan ukuran serta stylesheet
        Scene scene = new Scene(layout, 500, 600);
        scene.getStylesheets().add("/styles/stylesMyList.css");

        return scene;
        
    }

    public static void upadateList(Stage primaryStage){
        if (list != null) {
            list.getChildren().clear();
            // Mengambil semua jadwal dari database menggunakan DbManager
            List<String[]> allSchedule = DbManager.loadData();

            // Untuk setiap jadwal, buat Label yang menampilkan tanggal dan judul jadwal
            for (String[] schedule : allSchedule){
                Label scheduLabel = new Label("\t" + schedule[3] + "\t\t" + schedule[1]);
                scheduLabel.setPrefWidth(460);
                scheduLabel.setPrefHeight(40);
                scheduLabel.setId("schedule-label");

                // Menetapkan event handler untuk Label, ketika diklik, akan menampilkan detail dari jadwal tersebut
                scheduLabel.setOnMouseClicked(e -> {
                    Scene detailScene = DaftarPengingatHarian.detailScene(primaryStage, schedule, primaryStage.getScene());
                    primaryStage.setScene(detailScene);
                });
                list.getChildren().add(scheduLabel); // Menambahkan Label ke dalam VBox
            }
        }
    }
}