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
    private static String jadwal;
    private App appInstance;

    @Override
    public void start(Stage primaryStage) {
        appInstance = this;
        primaryStage.setTitle("Reminder's");
        primaryStage.setResizable(false);

        primaryStage.setScene(createMainScene(primaryStage));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public String getJadwal() {
        return jadwal;
    }

    public static void setJadwal(String jadwal) {
        App.jadwal = jadwal;
    }

    public Scene createMainScene(Stage primaryStage) {
        Button button1 = new Button("Search");
        button1.setStyle("-fx-font-weight: bold;");
        button1.setMaxWidth(470);
        button1.setMinHeight(20);

        StackPane stackPane1 = new StackPane(button1);
        stackPane1.setAlignment(Pos.TOP_CENTER);
        stackPane1.setPadding(new javafx.geometry.Insets(10));

        Button button2 = new Button("To Do");
        button2.setMaxWidth(130);
        button2.setMinHeight(80);
        button2.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");

        StackPane stackPane2 = new StackPane(button2);
        stackPane2.setAlignment(Pos.CENTER_LEFT);
        stackPane2.setPadding(new javafx.geometry.Insets(15));

        Button jadwalButton = new Button("+ Add Schedule");
        jadwalButton.setMaxHeight(150);
        jadwalButton.setMinHeight(80);
        jadwalButton.setAlignment(Pos.BASELINE_RIGHT);
        jadwalButton.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

       
        jadwalButton.setOnAction(e -> {
            try {
                primaryStage.setScene(InputJadwal.createScene(primaryStage, appInstance));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        StackPane stackPane3 = new StackPane(jadwalButton);
        stackPane3.setAlignment(Pos.BOTTOM_LEFT);
        stackPane3.setPadding(new javafx.geometry.Insets(40));

        VBox root = new VBox();
        root.setStyle("-fx-background-color: #222831;");
        root.getChildren().addAll(stackPane1, stackPane2, stackPane3); 

        Scene scene = new Scene(root, 500, 600);

        try {
            FileInputStream iconStream = new FileInputStream("src/main/resources/image/iconApp.png");
            Image icon = new Image(iconStream);
            primaryStage.getIcons().add(icon);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return scene;
    }
}

