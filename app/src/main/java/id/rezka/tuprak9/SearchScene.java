package id.rezka.tuprak9;

import id.rezka.tuprak9.controller.DbManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class SearchScene {

    public static Scene createScene(Stage primaryStage, App appInstance) {

        // Membuat tombol untuk kembali ke scene utama
        Button backButton = new Button("");
        backButton.setMaxWidth(40);
        backButton.setMaxHeight(40);
        backButton.setId("bck-bttn");
        backButton.setOnAction(e -> primaryStage.setScene(appInstance.createMainScene(primaryStage)));
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

        // Membuat kolom pencarian
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.setPrefWidth(400);
        searchField.setId("search-field");

        // Membuat ScrollPane
        ScrollPane scroll = new ScrollPane();
        scroll.setFitToWidth(true);
        scroll.setId("scroll-pane");

        // Membuat kotak vertikal untuk menampung hasil pencarian
        VBox searchBox = new VBox(10);
        searchBox.setPadding(new javafx.geometry.Insets(10));
        searchBox.setId("main-layout");
        scroll.setContent(searchBox);

        // Mengatur tata letak utama scene
        VBox layout2 = new VBox(10, searchField, scroll, backButton);
        layout2.setPadding(new javafx.geometry.Insets(20));
        layout2.setAlignment(Pos.TOP_CENTER);
        layout2.setId("search-box");

        // Memuat data awal
        updateSearchResults(searchBox, "", primaryStage);

        // Penanganan acara untuk perubahan teks dalam kolom pencarian
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateSearchResults(searchBox, newValue, primaryStage);
        });

        Scene scene = new Scene(layout2, 500, 600);
        scene.getStylesheets().add("/styles/stylesSearch.css");
        scene.getStylesheets().add("/styles/stylesDetail.css");
        return scene;
    }

    public static void updateSearchResults(VBox searchBox, String keyword, Stage primaryStage) {
        List<String[]> searchResults = DbManager.cariData(keyword);
        searchBox.getChildren().clear();

        for (String[] result : searchResults) {
            Label resultLabel = new Label(result[3] + "\t" + result[1]);
            resultLabel.setPrefWidth(400);
            resultLabel.setId("result-label");
            
            resultLabel.setOnMouseClicked(ev -> {
                Scene detailScene = DaftarPengingatHarian.detailScene(primaryStage, result, primaryStage.getScene());
                primaryStage.setScene(detailScene);
            });
            searchBox.getChildren().add(resultLabel);
        }
    }
}
