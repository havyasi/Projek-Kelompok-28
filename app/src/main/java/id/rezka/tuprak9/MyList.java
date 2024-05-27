package id.rezka.tuprak9;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MyList {
    public static Scene createScene(Stage primaryStage, App app) {
        Label myListLabel = new Label("My List");
        myListLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");

        // nampilkan semua list schedule scroll panenya nanti tmpil kalau dtanya lewat dri ukuran stageny kita
        // pke sql

        ScrollPane scroll= new ScrollPane();
        // scroll.setContent();
        scroll.setFitToWidth(false);
        scroll.setStyle("-fx-background: #CAF4FF;");

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        backButton.setOnAction(e -> primaryStage.setScene(app.createMainScene(primaryStage)));

        BorderPane layout = new BorderPane();
        layout.setTop(myListLabel);
        layout.setBottom(backButton);
        BorderPane.setAlignment(myListLabel, Pos.TOP_CENTER);
        BorderPane.setMargin(myListLabel, new Insets(20, 0, 20, 0));
        BorderPane.setAlignment(backButton, Pos.CENTER);
        BorderPane.setMargin(backButton, new Insets(0, 20, 20, 20));
        layout.setStyle("-fx-background-color: #CAF4FF;");

        return new Scene(layout, 500, 600);
    }
}