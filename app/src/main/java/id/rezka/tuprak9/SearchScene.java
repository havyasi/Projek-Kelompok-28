package id.rezka.tuprak9;

import id.rezka.tuprak9.controller.DbManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class SearchScene {

    public static Scene createScene(Stage primaryStage, App appInstance) {

        // Membuat tombol untuk kembali ke scene utama
        Button backButton = new Button("Back");
        backButton.setId("back-button");
        backButton.setOnAction(e -> primaryStage.setScene(appInstance.createMainScene(primaryStage)));

        // Membuat kolom pencarian
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
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
        VBox layout2 = new VBox(10, backButton, searchField, scroll);
        layout2.setPadding(new javafx.geometry.Insets(20));
        layout2.setAlignment(Pos.TOP_LEFT);
        layout2.setId("search-box");

        // Memuat data awal
        updateSearchResults(searchBox, "", primaryStage);

        // Penanganan acara untuk perubahan teks dalam kolom pencarian
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateSearchResults(searchBox, newValue, primaryStage);
        });

        Scene scene = new Scene(layout2, 500, 600);
        scene.getStylesheets().add("/styles/stylesSearch.css");
        return scene;
    }

    public static void updateSearchResults(VBox searchBox, String keyword, Stage primaryStage) {
        List<String[]> searchResults = DbManager.cariData(keyword);
        searchBox.getChildren().clear();

        for (String[] result : searchResults) {
            Label resultLabel = new Label(result[3] + " - " + result[1]);
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
