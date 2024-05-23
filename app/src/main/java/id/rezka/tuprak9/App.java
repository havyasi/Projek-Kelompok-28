package id.rezka.tuprak9;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
// import java.util.ArrayList;

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

        Button button2 = new Button("");
        button2.setMaxWidth(130);
        button2.setMinHeight(80);
        button2.setStyle("-fx-font-size: 25px;"+
        "-fx-font-weight: bold;" + 
        "-fx-background-color: rgba(128, 128, 128, 255);" +
        "-fx-background-radius: 20");

        StackPane stackPane2 = new StackPane(button2);
        stackPane2.setAlignment(Pos.CENTER_LEFT);
        stackPane2.setPadding(new javafx.geometry.Insets(0, 10, 10, 20));

        try {
            FileInputStream iconStream = new FileInputStream("src/main/resources/image/calendar.png");
            Image icon = new Image(iconStream);

            ImageView imageView = new ImageView(icon);
            imageView.setFitHeight(70);
            imageView.setFitWidth(70);
            button2.setGraphic(imageView);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
            
        jadwalButton.setOnAction(e -> {
            try {
                primaryStage.setScene(InputJadwal.createScene(primaryStage, appInstance));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        StackPane stackPane3 = new StackPane(jadwalButton);
        stackPane3.setAlignment(Pos.BOTTOM_LEFT);
        stackPane3.setPadding(new javafx.geometry.Insets(350, 0, 0, 360));
        
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

