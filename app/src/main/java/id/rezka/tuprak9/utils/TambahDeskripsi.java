package id.rezka.tuprak9.utils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TambahDeskripsi {
    private static String deskripsi;
    
    
    public static Stage tambahDeskrip(Stage primaryStage){
        Stage popUpDescribe = new Stage();
        popUpDescribe.initOwner(primaryStage);
        popUpDescribe.initModality(Modality.APPLICATION_MODAL);
        popUpDescribe.initStyle(StageStyle.UTILITY);
        popUpDescribe.setTitle("Tambahkan Deskripsi");

        TextArea desArea = new TextArea();
        desArea.setPromptText("Masukkan Deskripsi");
        desArea.setStyle("-fx-font-family: 'Cascadia Code'; -fx-background-color: white; -fx-text-fill: black; -fx-font-size: 15px;");
        desArea.setPrefWidth(300);
        desArea.setPrefHeight(100);
        desArea.setWrapText(true);

        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-font-size: 15px;");
        saveButton.setPrefWidth(100);
        saveButton.setOnAction(e -> {
            deskripsi = desArea.getText();

            popUpDescribe.close();
        });

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 15px;");
        backButton.setPrefWidth(100);
        backButton.setOnAction(e -> {
            popUpDescribe.close();
        });

        HBox tombolDesc = new HBox(190, backButton, saveButton );
        tombolDesc.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10, desArea, tombolDesc);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #e3f2fd ; -fx-font-size: 25px");

        Scene popupScene = new Scene(layout, 400, 200);
        popUpDescribe.setScene(popupScene);

        return popUpDescribe;
    }

    public static String getDeskripsi() {
        return deskripsi;
    }
}
