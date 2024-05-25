package id.rezka.tuprak9;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
        Button searchbar = new Button("Search");
        searchbar.setStyle(
            "-fx-font-weight: bold;" +
            "-fx-font-size: 10px;" +
            "-fx-background-color: rgba(255, 255, 255, 255);");
        searchbar.setMaxWidth(470);
        searchbar.setMinHeight(30);


        //Action untuk Search
        searchbar.setOnAction(e -> {
            try {
                primaryStage.setScene(SearchScene.createScene(primaryStage, appInstance));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        StackPane stackPane1 = new StackPane(searchbar);
        stackPane1.setAlignment(Pos.TOP_CENTER);
        stackPane1.setPadding(new javafx.geometry.Insets(20));



        try {
            FileInputStream iconStream = new FileInputStream("src/main/resources/image/search.png");
            Image icon = new Image(iconStream);

            ImageView imageView = new ImageView(icon);
            imageView.setFitHeight(10);
            imageView.setFitWidth(10);
            searchbar.setGraphic(imageView);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Tombol Calendar
        Button calendarButton = new Button("");
        calendarButton.setMaxWidth(150);
        calendarButton.setMinHeight(100);
        calendarButton.setStyle("-fx-font-size: 25px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: rgba(128, 128, 128, 255);" +
                "-fx-background-radius: 20");

        // Action untuk Calendar Button
        calendarButton.setOnAction(e -> {
            try {
                primaryStage.setScene(CalendarScene.createScene(primaryStage, appInstance));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        try {
            FileInputStream iconStream = new FileInputStream("src/main/resources/image/calendar.png");
            Image icon = new Image(iconStream);

            ImageView imageView = new ImageView(icon);
            imageView.setFitHeight(90);
            imageView.setFitWidth(90);
            calendarButton.setGraphic(imageView);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Tombol Completed
        Button completedButton = new Button("");
        completedButton.setMaxWidth(150);
        completedButton.setMinHeight(100);
        completedButton.setStyle("-fx-font-size: 25px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: rgba(128, 128, 128, 255);" +
                "-fx-background-radius: 20");

        // Action Untuk Completed Button
        completedButton.setOnAction(e -> {
            try {
                primaryStage.setScene(CompletedScene.createScene(primaryStage, appInstance));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        try {
            FileInputStream iconStream = new FileInputStream("src/main/resources/image/completed.png");
            Image icon = new Image(iconStream);

            ImageView imageView = new ImageView(icon);
            imageView.setFitHeight(90);
            imageView.setFitWidth(90);
            completedButton.setGraphic(imageView);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HBox hBoxButtons = new HBox(130);
        hBoxButtons.setAlignment(Pos.CENTER);
        hBoxButtons.getChildren().addAll(calendarButton, completedButton);

        StackPane buttonPane = new StackPane(hBoxButtons);
        buttonPane.setAlignment(Pos.TOP_CENTER);
        buttonPane.setPadding(new javafx.geometry.Insets(30, 0, 0, 0));

        Button jadwalButton = new Button(" Add Schedule ");
        jadwalButton.setMaxHeight(100);
        jadwalButton.setMinHeight(10);
        jadwalButton.setAlignment(Pos.BASELINE_RIGHT);
        jadwalButton.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-color: rgba(128, 128, 128, 255);" +
                        "-fx-text-fill: black;" +
                        "-fx-font-family: 'Arial';");


        //Action untuk jadwal
        jadwalButton.setOnAction(e -> {
            try {
                primaryStage.setScene(InputJadwal.createScene(primaryStage, appInstance, createMainScene(primaryStage)));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        StackPane jadwalPane = new StackPane(jadwalButton);
        jadwalPane.setAlignment(Pos.BOTTOM_LEFT);
        jadwalPane.setPadding(new javafx.geometry.Insets(350, 0, 0, 360));

        try {
            FileInputStream iconStream = new FileInputStream("src/main/resources/image/plus.png");
            Image icon = new Image(iconStream);
            ImageView imageView = new ImageView(icon);
            imageView.setFitHeight(10);
            imageView.setFitWidth(10);
            jadwalButton.setGraphic(imageView);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        VBox root = new VBox();
        root.setStyle("-fx-background-color: #0C0C0C;");
        root.getChildren().addAll(stackPane1, buttonPane, jadwalPane);

        Scene scene = new Scene(root, 500, 600);

        try {
            FileInputStream iconStream = new FileInputStream("src/main/resources/image/IMG_1146.JPG");
            Image icon = new Image(iconStream);
            primaryStage.getIcons().add(icon);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return scene;
    }
}
