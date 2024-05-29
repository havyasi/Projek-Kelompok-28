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
        // Tombol back
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 13px; -fx-font-weight: bold;");
        backButton.setOnAction(e -> primaryStage.setScene(appInstance.createMainScene(primaryStage)));

        // Kolom Search
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 13px;");
        searchField.setPrefWidth(400);

        // ScrollPanel
        ScrollPane scroll = new ScrollPane();
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background: #DAF2FE;");
        VBox searchBox = new VBox(10);
        searchBox.setPadding(new javafx.geometry.Insets(10));
        searchBox.setStyle("-fx-background-color: #DAF2FE;");
        scroll.setContent(searchBox);

        // Layout setup
        VBox layout2 = new VBox(10, backButton, searchField, scroll);
        layout2.setPadding(new javafx.geometry.Insets(20));
        layout2.setAlignment(Pos.TOP_LEFT);
        layout2.setStyle("-fx-background-color: #DAF2FE; -fx-font-weight: bold; -fx-font-size: 25px");

        // Initial load of all data
        updateSearchResults(searchBox, "", primaryStage);

        // Event handler for text change in search field
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateSearchResults(searchBox, newValue, primaryStage);
        });

        return new Scene(layout2, 500, 600);
    }

    private static void updateSearchResults(VBox searchBox, String keyword, Stage primaryStage) {
        List<String[]> searchResults = DbManager.cariData(keyword);

        // Clear previous search results
        searchBox.getChildren().clear();

        // Display search results in the VBox within the ScrollPane
        for (String[] result : searchResults) {
            Label resultLabel = new Label(result[3] + " - " + result[1]);
            resultLabel.setPrefWidth(400);
            resultLabel.setStyle("-fx-font-size: 15px; -fx-text-fill: black; -fx-background-color: #90caf9; -fx-background-radius: 5;");
            resultLabel.setOnMouseClicked(ev -> {
                Scene detailScene = DaftarPengingatHarian.detailScene(primaryStage, result, primaryStage.getScene());
                primaryStage.setScene(detailScene);
            });
            searchBox.getChildren().add(resultLabel);
        }
    }
}
