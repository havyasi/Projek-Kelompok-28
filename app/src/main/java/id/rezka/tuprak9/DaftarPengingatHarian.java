package id.rezka.tuprak9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

import id.rezka.tuprak9.controller.DbManager;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class DaftarPengingatHarian {

    private static boolean popupmuncul = false;
    private static Stage popup;

    // Metode untuk membuat scene detail untuk sebuah jadwal
    public static Scene detailScene(Stage primaryStage, String[] scheduleDetails, Scene previousScene) {
        
        // Membuat kontainer VBox untuk menempatkan komponen-komponen UI
        VBox detailJadwal = new VBox(10);
        detailJadwal.setAlignment(Pos.TOP_CENTER);
        detailJadwal.setId("box-detail"); // Setel ID untuk CSS styling

        // Label judul untuk menandakan bagian detail
        Label titleLabel = new Label("Schedule Details");
        titleLabel.setId("label-detail");

         // Informasi jadwal yang akan ditampilkan
        Label scheduleLabel = new Label(scheduleDetails[4] + "\tDate : " + scheduleDetails[3] + "\nTitle : " + scheduleDetails[1] + "\nType Of Priority: " + scheduleDetails[2] +
                "\nDeskripsi: " + scheduleDetails[5]);
        scheduleLabel.setId("label-schedule"); // Setel ID untuk CSS styling

        // Tombol kembali untuk kembali ke scene sebelumnya
        Button backButton = new Button();
        backButton.setMaxWidth(40);
        backButton.setMaxHeight(40);
        backButton.setId("bck-bttn");
        backButton.setOnAction(e -> {
            primaryStage.setScene(previousScene); ; // Kembali ke scene sebelumnya
            MyList.upadateList(primaryStage); // Perbarui daftar jadwal
        });
        try {
             // Setel ikon tombol kembali
            FileInputStream iconStream = new FileInputStream("src/main/resources/image/back-arrow.png");
            Image icon = new Image(iconStream);

            ImageView imageView = new ImageView(icon);
            imageView.setFitHeight(20);
            imageView.setFitWidth(20);
            backButton.setGraphic(imageView);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Perbarui daftar jadwal di scene sebelumnya
        Button deleteButton = new Button("");
        deleteButton.setMaxWidth(40);
        deleteButton.setMaxHeight(40);
        deleteButton.setId("delete-btn");
        deleteButton.setOnAction(e -> {
            int id = Integer.parseInt(scheduleDetails[0]); // Ambil ID jadwal dari array
            DbManager.removeData(id); // Hapus jadwal dari database
            primaryStage.setScene(previousScene); // Kembali ke scene sebelumnya

            // Perbarui daftar jadwal di scene sebelumnya
            MyList.upadateList(primaryStage);

            // Jika scene sebelumnya adalah scene pencarian, perbarui juga hasil pencarian
            String rootid = previousScene.getRoot().getId();
            if (rootid != null && rootid.equals("search-box")) {
                VBox searchBox = (VBox) ((ScrollPane) previousScene.lookup("#scroll-pane")).getContent();
                SearchScene.updateSearchResults(searchBox, "", primaryStage);
            }
        });

        try {
             // Setel ikon tombol hapus
            FileInputStream iconStream = new FileInputStream("src/main/resources/image/delete (2).png");
            Image icon = new Image(iconStream);

            ImageView imageView = new ImageView(icon);
            imageView.setFitHeight(20);
            imageView.setFitWidth(20);
            deleteButton.setGraphic(imageView);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

          // Kotak untuk menempatkan tombol-tombol
        HBox buttonBox = new HBox(400, backButton, deleteButton);

        // Kotak untuk menempatkan informasi jadwal
        HBox scheduleBox = new HBox(scheduleLabel);
        scheduleBox.setAlignment(Pos.BASELINE_LEFT);

        // Kotak tengah yang berisi judul dan informasi jadwal
        VBox centerBox = new VBox(10, titleLabel, scheduleBox);
        centerBox.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(titleLabel, new Insets(20, 20, 10, 20));
        VBox.setMargin(scheduleBox, new Insets(10, 20, 20, 20));

        // Layout BorderPane yang menempatkan semua komponen
        BorderPane layout = new BorderPane();
        layout.setTop(buttonBox);
        layout.setCenter(centerBox);
        BorderPane.setAlignment(buttonBox, Pos.TOP_CENTER);
        BorderPane.setMargin(buttonBox, new Insets(20, 20, 20, 20));
        detailJadwal.getChildren().add(layout); // Tambahkan layout ke VBox utama

        // Buat scene dengan VBox sebagai root
        Scene scene = new Scene(detailJadwal, 500, 600);
        scene.getStylesheets().add("styles/stylesDetail.css"); // Tambahkan CSS styling

        return scene;
    }

    // Metode untuk menampilkan daftar jadwal harian dalam jendela popup
    public static void tampilkanDaftarHarian(Stage primaryStage, Button triggerButton) {
        if (popupmuncul) {
            if (popup != null) {
                popup.hide();
                popupmuncul = false;
            }
            return;
        }

        popupmuncul = true;
        popup = new Stage();
        popup.initOwner(primaryStage);
        popup.initModality(Modality.NONE);
        popup.initStyle(StageStyle.TRANSPARENT);

        // Kotak untuk menempatkan daftar jadwal harian
        VBox daftar = new VBox(10);
        daftar.setPadding(new Insets(10));
        daftar.setAlignment(Pos.TOP_LEFT);
        daftar.setPrefSize(360, 300);
        daftar.setId("box-daftar");

        // Label judul untuk menandakan bagian daftar
        Label titleLabel = new Label("Today's Schedule");
        titleLabel.setId("label-title");
        daftar.getChildren().addAll(titleLabel);

        // Mendapatkan tanggal hari ini.
        LocalDate today = LocalDate.now();

        //Memuat data jadwal dari database untuk tanggal hari ini.
        List<String[]> todaySchedules = DbManager.loadDataForDate(today);

        //// Membuat sebuah scroll pane dan menambahkan daftar jadwal ke dalamnya
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(daftar);
        scrollPane.setPrefSize(380, 300);
        scrollPane.setFitToWidth(true);

        //Menambahkan style dan scene ke popup yang akan menampilkan daftar jadwal.
        Scene popupScene = new Scene(scrollPane);
        popupScene.setFill(null);
        popupScene.getStylesheets().add("/styles/stylesDaftarPengingat.css");
        popup.setScene(popupScene);

        //Perulangan untuk setiap jadwal pada tanggal hari ini.
        for (String[] jadwal : todaySchedules) {
            Label scheduleLabel = new Label(jadwal[4] + "\t" + jadwal[1]); // Membuat sebuah label dengan teks berisi waktu dan judul jadwal.
            scheduleLabel.setPrefWidth(340);
            //Menentukan warna label berdasarkan tingkat prioritas jadwal.
            if ("Low".equals(jadwal[2])) {
                scheduleLabel.setId("rendah-label");
            } else if ("Medium".equals(jadwal[2])) {
                scheduleLabel.setId("sedang-label");
            } else if ("High".equals(jadwal[2])) {
                scheduleLabel.setId("tinggi-label");
            }


            //Menambahkan event listener untuk menangani pergeseran ke scene detail jadwal ketika label diklik.
            scheduleLabel.setOnMouseClicked(e -> {
                Scene detailScene = detailScene(primaryStage, jadwal, primaryStage.getScene()); //Menambahkan label ke VBox yang berisi daftar jadwal.
                primaryStage.setScene(detailScene);
                popup.hide();
                popupmuncul = false;
            });

            daftar.getChildren().add(scheduleLabel);
        }

        //Mendapatkan posisi trigger button dalam koordinat layar.
        Bounds bounds = triggerButton.localToScreen(triggerButton.getBoundsInLocal());

        //Menetukan posisi popup yang berisi daftar jadwal.
        popup.setX(bounds.getMinX());
        popup.setY(bounds.getMaxY() + 5);
        popup.show();////Menampilkan popup.


        ////Membuat scroll pane bergeser ke atas sebelum ditampilkan.
        scrollPane.setTranslateY(-300);
        ////Membuat transisi bergeser untuk scroll pane.
        TranslateTransition slide = new TranslateTransition(Duration.millis(300), scrollPane);
        slide.setFromY(-300);
        slide.setToY(0);
        slide.play();

        // //Menangani pergeseran jendela aplikasi dan menggeser popup sesuai dengan posisi trigger button.
        primaryStage.xProperty().addListener((obs, oldVal, newVal) -> {
            Bounds newBounds = triggerButton.localToScreen(triggerButton.getBoundsInLocal());
            popup.setX(newBounds.getMinX());
        });

        primaryStage.yProperty().addListener((obs, oldVal, newVal) -> {
            Bounds newBounds = triggerButton.localToScreen(triggerButton.getBoundsInLocal());
            popup.setY(newBounds.getMaxY() + 5);
        });

        ////Menyembunyikan popup ketika mouse ditekan di luar area popup.
        primaryStage.getScene().addEventFilter(javafx.scene.input.MouseEvent.MOUSE_PRESSED, event -> {
            if (popup.isShowing() && !bounds.contains(event.getScreenX(), event.getScreenY())) {
                popup.hide();
                popupmuncul = false;
            }
        });
    }
}
