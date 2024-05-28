package id.rezka.tuprak9.utils;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class NotifInputJadwal {

    public static final double NOTIFICATION_HEIGHT = 500;

    public static void slideAtas(VBox notificationBox, Scene sceneSebelumnya) {
        TranslateTransition slideAtas = new TranslateTransition(Duration.seconds(0.5), notificationBox);
        slideAtas.setToY(NOTIFICATION_HEIGHT / 2);
        slideAtas.setOnFinished(e -> {
            StackPane rootSebelumnya = (StackPane) sceneSebelumnya.getRoot();
            for (Node tombol : rootSebelumnya.getChildren()) {
                tombol.setDisable(true);
            }
        });
        slideAtas.play();
    }

    public static void slidekeBawah(VBox notificationBox, Scene sceneSebelumnya) {
        TranslateTransition slidekeBawah = new TranslateTransition(Duration.seconds(0.5), notificationBox);
        slidekeBawah.setToY(notificationBox.getHeight());
        slidekeBawah.setOnFinished(e -> {
            StackPane rootSebelumnya = (StackPane) sceneSebelumnya.getRoot();
            for (Node tombol : rootSebelumnya.getChildren()) {
                tombol.setDisable(false);
            }
        });
        slidekeBawah.play();
    }

    public static void showErrorPopup(Stage owner, String message) {
        Stage popUpError = new Stage();
        popUpError.initOwner(owner);
        popUpError.initModality(Modality.APPLICATION_MODAL);
        popUpError.initStyle(StageStyle.UTILITY);
        popUpError.setTitle("ERROR");

        Label errorLabel = new Label(message);
        errorLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold; -fx-font-size: 10px");
        errorLabel.setWrapText(true);

        VBox popupLayout = new VBox(errorLabel);
        popupLayout.setAlignment(Pos.CENTER);
        VBox.setMargin(errorLabel, new Insets(10));

        Scene popupScene = new Scene(popupLayout, 300, 100);
        popUpError.setScene(popupScene);
        
        popUpError.show();
    }
}
