package id.rezka.tuprak9;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CompletedScene {
    public static Scene createScene(Stage primaryStage, App app) {
        Label completedLabel = new Label("Completed List");
        completedLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        backButton.setOnAction(e -> primaryStage.setScene(app.createMainScene(primaryStage)));

        VBox centerLayout = new VBox(30, completedLabel);
        centerLayout.setAlignment(Pos.TOP_CENTER);

        BorderPane layout = new BorderPane();
        layout.setCenter(centerLayout);
        layout.setBottom(backButton);
        BorderPane.setAlignment(backButton, Pos.BOTTOM_LEFT);
        BorderPane.setMargin(backButton, new Insets(0, 0, 20, 20));
        layout.setStyle("-fx-background-color: black;");

        return new Scene(layout, 500, 600);
    }
}
