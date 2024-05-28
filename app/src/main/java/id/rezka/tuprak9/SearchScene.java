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
        // Back button setup
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 13px; -fx-font-weight: bold;");
        backButton.setOnAction(e -> primaryStage.setScene(appInstance.createMainScene(primaryStage)));

        // Search field setup
        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 13px;");
        searchField.setPrefWidth(400);

        // ScrollPane setup
        ScrollPane scroll = new ScrollPane();
        scroll.setFitToWidth(false);
        scroll.setStyle("-fx-background: #CAF4FF;");

        // Layout setup
        VBox layout2 = new VBox(10, backButton, searchField, scroll);
        layout2.setPadding(new javafx.geometry.Insets(20));
        layout2.setAlignment(Pos.TOP_LEFT);
        layout2.setStyle("-fx-background-color: #CAF4FF; -fx-font-weight: bold; -fx-font-size: 25px");

        // Event handler for search action
        searchField.setOnAction(e -> {
            String keyword = searchField.getText();
            List <String[]> searchResults = DbManager.cariData(keyword);

            // Display search results in the ScrollPane
            VBox searchBox = new VBox(10);
            searchBox.setPadding(new javafx.geometry.Insets(10));
            searchBox.setStyle("-fx-background-color: #CAF4FF;");
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
            scroll.setContent(searchBox);
        });

        return new Scene(layout2, 500, 600);
    }
}