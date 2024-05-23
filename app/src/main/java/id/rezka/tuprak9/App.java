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
        button1.setMinHeight(20);

        StackPane stackPane1 = new StackPane(button1);
        stackPane1.setAlignment(Pos.TOP_CENTER);
        stackPane1.setPadding(new javafx.geometry.Insets(10));

        Button button2 = new Button("To Do");
        button2.setMaxWidth(150);
        button2.setMinHeight(80);
        button2.setStyle("-fx-font-size: 25px;");

        StackPane stackPane2 = new StackPane(button2);
        stackPane2.setAlignment(Pos.CENTER_LEFT);
        stackPane2.setPadding(new javafx.geometry.Insets(15));

        VBox root = new VBox();
        root.setStyle("-fx-background-color: #222831;");
        root.getChildren().addAll(stackPane1, stackPane2);

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
