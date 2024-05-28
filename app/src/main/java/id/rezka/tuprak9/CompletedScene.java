package id.rezka.tuprak9;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CompletedScene {
    public static Scene createScene(Stage primaryStage, App app) {
        Label completedLabel = new Label("Completed List");
        completedLabel.setId("completed-Label");

        // menampilakan bnyk tugas yg sdh diselesaikan scroll panenya nanti berguna klau bnyk daftar
        // tugas yg diselesaikan (sql)

        ScrollPane scroll= new ScrollPane();
        scroll.setContent(completedLabel);
        scroll.setFitToWidth(false);
        scroll.setId("scrol-styl");

        Button backButton = new Button("Back");
        backButton.setId("back-btn");
        backButton.setOnAction(e -> primaryStage.setScene(app.createMainScene(primaryStage)));


        // Layout untuk tombol dibawah
        VBox bottomLayout = new VBox();
        bottomLayout.setPadding(new Insets(20));
        bottomLayout.setAlignment(Pos.CENTER);
        bottomLayout.getChildren().addAll(backButton);

        BorderPane layout = new BorderPane();
        layout.setTop(completedLabel);
        layout.setBottom(bottomLayout);
        BorderPane.setAlignment(completedLabel, Pos.TOP_CENTER);
        BorderPane.setMargin(completedLabel, new Insets(20, 0, 20, 0));
        BorderPane.setMargin(bottomLayout, new Insets(0, 20, 20, 20));
        layout.setId("completed-lyt");

        Scene scene = new Scene(layout, 500, 600);
        scene.getStylesheets().add("/styles/stylesCompleted.css");
        return scene;
    }
}
