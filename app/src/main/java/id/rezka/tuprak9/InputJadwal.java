package id.rezka.tuprak9;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InputJadwal {
    public static Scene createScene(Stage primaryStage, App app) {
        VBox layout = new VBox(15);
        layout.setPadding(new javafx.geometry.Insets(20));
        layout.setAlignment(Pos.BASELINE_RIGHT);
        layout.setStyle("-fx-background-color: black; -fx-font-weight: bold; -fx-font-size: 25px");

        Label catatanLabel = new Label("Catatan");
        catatanLabel.setStyle("-fx-text-fill: white;");

        TextField jadwalField = new TextField();
        jadwalField.setPromptText("Masukkan Catatan");
        jadwalField.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 17px;");

        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-font-size: 17px;");
        saveButton.setOnAction(e -> {
            App.setJadwal(jadwalField.getText());
            primaryStage.setScene(app.createMainScene(primaryStage)); 
        });

        layout.getChildren().addAll(catatanLabel, jadwalField, saveButton);

        return new Scene(layout, 640, 480);
    }
}
