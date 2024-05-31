package id.rezka.tuprak9.utils;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class NotifInputJadwal {

    // Deklarasi untuk ketinggian notifikasi
    public static final double NOTIFICATION_HEIGHT = 500;

    // Metode untuk menggeser kotak notifikasi ke atas
    public static void slideAtas(VBox notificationBox, Scene sceneSebelumnya) {
        // Membuat animasi transisi untuk menggeser ke atas selama 0.5 detik
        TranslateTransition slideAtas = new TranslateTransition(Duration.seconds(0.5), notificationBox);
        // Menentukan posisi akhir animasi
        slideAtas.setToY(NOTIFICATION_HEIGHT / 2);
        // Menambahkan aksi yang dijalankan setelah animasi selesai
        slideAtas.setOnFinished(e -> {
            // Mendapatkan root dari scene sebelumnya dan menonaktifkan semua tombol di dalamnya
            StackPane rootSebelumnya = (StackPane) sceneSebelumnya.getRoot();
            for (Node tombol : rootSebelumnya.getChildren()) {
                tombol.setDisable(true);
            }
        });
        // Memulai animasi
        slideAtas.play();
    }

    // Metode untuk menggeser kotak notifikasi ke bawah
    public static void slidekeBawah(VBox notificationBox, Scene sceneSebelumnya) {
        // Membuat animasi transisi untuk menggeser ke bawah selama 0.5 detik
        TranslateTransition slidekeBawah = new TranslateTransition(Duration.seconds(0.5), notificationBox);
        // Menentukan posisi akhir animasi 
        slidekeBawah.setToY(notificationBox.getHeight());
        // Menambahkan aksi yang dijalankan setelah animasi selesai
        slidekeBawah.setOnFinished(e -> {
            // Mendapatkan root dari scene sebelumnya dan mengaktifkan kembali semua tombol di dalamnya
            StackPane rootSebelumnya = (StackPane) sceneSebelumnya.getRoot();
            for (Node tombol : rootSebelumnya.getChildren()) {
                tombol.setDisable(false);
            }
        });
        // Memulai animasi
        slidekeBawah.play();
    }

    // Metode untuk menampilkan pop-up error
    public static void showErrorPopup(Stage owner, String message) {
        // Membuat stage baru untuk pop-up error
        Stage popUpError = new Stage();
        // Mengatur owner dari pop-up error
        popUpError.initOwner(owner);
        // Mengatur modality agar pop-up error bersifat modal
        popUpError.initModality(Modality.APPLICATION_MODAL);
        // Mengatur style dari pop-up error
        popUpError.initStyle(StageStyle.UTILITY);
        // Mengatur judul dari pop-up error
        popUpError.setTitle("ERROR");

        // Membuat label untuk menampilkan pesan error
        Label errorLabel = new Label(message);
        // Mengatur ID untuk styling
        errorLabel.setId("error-label");
        // Mengatur teks label agar dapat melipat jika panjang
        errorLabel.setWrapText(true);

        // Membuat layout untuk pop-up
        VBox popupLayout = new VBox(errorLabel);
        // Mengatur alignment dari layout
        popupLayout.setAlignment(Pos.CENTER);
        // Mengatur margin dari label di dalam layout
        VBox.setMargin(errorLabel, new Insets(10));

        // Membuat scene untuk pop-up 
        Scene popupScene = new Scene(popupLayout, 300, 100);
        // Menambahkan stylesheet untuk styling
        popupScene.getStylesheets().add("/styles/stylesNotifInput.css");
        // Mengatur scene dari stage pop-up
        popUpError.setScene(popupScene);

        // Menampilkan pop-up
        popUpError.show();
    }
}

