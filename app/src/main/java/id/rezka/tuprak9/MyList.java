package id.rezka.tuprak9;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
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

        VBox layout = new VBox(20, myListLabel, scroll, backButton);
        layout.setAlignment(Pos.BASELINE_CENTER);
        layout.setStyle("-fx-background-color: #CAF4FF;");

        return new Scene(layout, 500, 600);
    }
}