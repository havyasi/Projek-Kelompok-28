package id.rezka.tuprak9;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Reminder's");
        primaryStage.setResizable(false);

        Button button1 = new Button("Search");
        button1.setMaxWidth(470);
        button1.setMaxHeight(45);
    

        StackPane stackPane = new StackPane(button1);
        stackPane.setAlignment(Pos.TOP_CENTER);
        stackPane.setPadding(new javafx.geometry.Insets(10));

        VBox root = new VBox(stackPane);
        root.setStyle("-fx-background-color: #222831;");

        Scene scene = new Scene(root, 500, 600);

        try {
            FileInputStream iconStream = new FileInputStream("src/main/resources/image/iconApp.png");
            Image icon = new Image(iconStream);
            primaryStage.getIcons().add(icon);
            } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
