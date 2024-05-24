package id.rezka.tuprak9;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalendarScene {
    public static Scene createScene(Stage primaryStage, App app) {
        Label calendarLabel = new Label("My List");
        calendarLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        backButton.setOnAction(e -> primaryStage.setScene(app.createMainScene(primaryStage)));

        VBox layout = new VBox(20, calendarLabel, backButton);
        layout.setAlignment(Pos.BASELINE_CENTER);
        layout.setStyle("-fx-background-color: black;");

        return new Scene(layout, 500, 600);
    }
}