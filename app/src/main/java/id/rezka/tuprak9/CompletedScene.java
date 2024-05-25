package id.rezka.tuprak9;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CompletedScene {
    public static Scene createScene(Stage primaryStage, App app) {
        Label completedLabel = new Label("Completed List");
        completedLabel.setStyle("-fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");

        // menampilakan bnyk tugas yg sdh diselesaikan scroll panenya nanti berguna klau bnyk daftar
        // tugas yg diselesaikan (sql)

        ScrollPane scroll= new ScrollPane();
        scroll.setContent(completedLabel);
        scroll.setFitToWidth(false);
        scroll.setStyle("-fx-background: #7296a4;");

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        backButton.setOnAction(e -> primaryStage.setScene(app.createMainScene(primaryStage)));

        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        // Tambahkan action untuk saveButton disini, e.g. saveButton.setOnAction(e -> saveAction());

        // Layout untuk tombol dibawah
        HBox bottomLayout = new HBox(300);
        bottomLayout.setPadding(new Insets(20));
        bottomLayout.setAlignment(Pos.BOTTOM_LEFT);
        bottomLayout.getChildren().addAll(backButton, saveButton);

        BorderPane layout = new BorderPane();
        layout.setTop(completedLabel);
        layout.setBottom(bottomLayout);
        BorderPane.setAlignment(completedLabel, Pos.TOP_CENTER);
        BorderPane.setMargin(completedLabel, new Insets(20, 0, 20, 0));
        BorderPane.setMargin(bottomLayout, new Insets(0, 20, 20, 20));
        layout.setStyle("-fx-background-color: #7296a4;");

        return new Scene(layout, 500, 600);
    }
}
