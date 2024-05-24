package id.rezka.tuprak9;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// masih dlm proses blm bisa jln

public class TambahDeskripsi {
    // public static App app;
    // public static Scene scenesblmnya;
    
    public static Scene tambahDeskrip(Stage primaryStage, String describe){
        TextArea desArea = new TextArea(describe);
        desArea.setPromptText("Masukkan Deskripsi");
        desArea.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 15px;");
        desArea.setPrefWidth(400);
        desArea.setPrefHeight(200);

        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-font-size: 15px;");
        saveButton.setOnAction(e -> {
            // String deskripsiBaru = desArea.getText();
            // System.out.println("Deskripsi baru: " + deskripsiBaru);
            // primaryStage.setScene(InputJadwal.createScene(primaryStage, app, scenesblmnya)); 
        });

        VBox layout = new VBox(10, desArea, saveButton);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #888a8e; -fx-font-weight: bold; -fx-font-size: 25px");

        return new Scene(layout, 500, 600);
    }
}
