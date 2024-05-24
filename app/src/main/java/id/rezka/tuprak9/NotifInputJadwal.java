package id.rezka.tuprak9;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class NotifInputJadwal {

    public static final double NOTIFICATION_HEIGHT = 500;

    public static void slideAtas(VBox notificationBox) {
        TranslateTransition slideAtas = new TranslateTransition(Duration.seconds(0.5), notificationBox);
        slideAtas.setToY(NOTIFICATION_HEIGHT / 2);
        slideAtas.play();
    }

    public static void slidekeBawah(VBox notificationBox) {
        TranslateTransition slidekeBawah = new TranslateTransition(Duration.seconds(0.5), notificationBox);
        slidekeBawah.setToY(notificationBox.getHeight());
        slidekeBawah.play();
    }
}
