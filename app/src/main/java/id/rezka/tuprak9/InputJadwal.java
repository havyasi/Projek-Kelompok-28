package id.rezka.tuprak9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InputJadwal {
    public static Scene createScene(Stage primaryStage, App app) {
        
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
        
        Button tmbhtugas = new Button("");
        tmbhtugas.setMaxHeight(90);
        tmbhtugas.setMaxWidth(10);
        tmbhtugas.setAlignment(Pos.BASELINE_RIGHT);
        tmbhtugas.setStyle( "-fx-background-color: rgba(255, 255, 255, 255);" + 
        "-fx-background-radius : 50%");
        
        StackPane tmbktgspane = new StackPane(tmbhtugas);
        tmbktgspane.setAlignment(Pos.BOTTOM_CENTER);
        tmbktgspane.setPadding(new javafx.geometry.Insets(300, 0, 0, 0));

        
            try {
                FileInputStream iconStream = new FileInputStream("src/main/resources/image/plus2.png");
                Image icon = new Image(iconStream);
    
                ImageView imageView = new ImageView(icon);
                imageView.setFitHeight(20);
                imageView.setFitWidth(20);
                tmbhtugas.setGraphic(imageView);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
       
        
        VBox layout = new VBox(15);
        layout.setPadding(new javafx.geometry.Insets(20));
        layout.setAlignment(Pos.BASELINE_RIGHT);
        layout.setStyle("-fx-background-color: black; -fx-font-weight: bold; -fx-font-size: 25px");
        layout.getChildren().addAll(catatanLabel, jadwalField, saveButton, tmbktgspane);
        
        return new Scene(layout, 500, 600);
    }
}
