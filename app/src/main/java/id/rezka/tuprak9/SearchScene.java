package id.rezka.tuprak9;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchScene {

    public static Scene createScene(Stage primaryStage, App appInstance) {
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 13px; -fx-font-weight: bold;");
        backButton.setOnAction(e -> primaryStage.setScene(appInstance.createMainScene(primaryStage)));

        TextField searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 15px;");
        searchField.setPrefWidth(400);

        VBox layout2 = new VBox(10,backButton ,searchField);
        layout2.setPadding(new javafx.geometry.Insets(20));
        layout2.setAlignment(Pos.TOP_LEFT);
        layout2.setStyle("-fx-background-color: #7296a4; -fx-font-weight: bold; -fx-font-size: 25px");

        return new Scene(layout2, 500, 600);
    }
}
    

