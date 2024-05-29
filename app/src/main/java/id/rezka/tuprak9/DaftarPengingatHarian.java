package id.rezka.tuprak9;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;


import id.rezka.tuprak9.controller.DbManager;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class DaftarPengingatHarian {

    private static boolean popupmuncul = false;

    public static Scene detailScene(Stage primaryStage, String[] scheduleDetails, Scene previousScene) {
        VBox detailJadwal = new VBox(10);
        detailJadwal.setAlignment(Pos.TOP_CENTER);
        detailJadwal.setId("box-detail");
    
        Label titleLabel = new Label("Schedule Details");
        titleLabel.setId("label-detail");

        Label scheduleLabel = new Label(scheduleDetails[4] + "\tDate : " + scheduleDetails[3]+ "\nTitle : " + scheduleDetails[1] + "\nType Of Priority: " + scheduleDetails[2] +
                "\nDeskripsi: " + scheduleDetails[5]);
        scheduleLabel.setId("label-schedule");

        Button backButton = new Button();
        backButton.setMaxWidth(40);
        backButton.setMaxHeight(40);
        backButton.setId("bck-bttn");
        backButton.setOnAction(e -> {
            primaryStage.setScene(previousScene);
            MyList.upadateList(primaryStage);
        });
        try {
            FileInputStream iconStream = new FileInputStream("src/main/resources/image/back.png");
            Image icon = new Image(iconStream);

            ImageView imageView = new ImageView(icon);
            imageView.setFitHeight(20);
            imageView.setFitWidth(30);
            backButton.setGraphic(imageView);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Button deleteButton = new Button("");
        deleteButton.setMaxWidth(40);
        deleteButton.setMaxHeight(40);
        deleteButton.setId("delete-btn");
        deleteButton.setOnAction(e -> {
            int id = Integer.parseInt(scheduleDetails[0]);
            DbManager.removeData(id);
            primaryStage.setScene(previousScene);

            //refresh di scene my list
            MyList.upadateList(primaryStage);

            //refresh di scene search
            if (previousScene.getRoot().getId().equals("search-box")) {
                VBox searchBox = (VBox) ((ScrollPane) previousScene.lookup("#scroll-pane")).getContent();
                SearchScene.updateSearchResults(searchBox, "", primaryStage);
            }
        });

        try {
            FileInputStream iconStream = new FileInputStream("src/main/resources/image/delete.png");
            Image icon = new Image(iconStream);

            ImageView imageView = new ImageView(icon);
            imageView.setFitHeight(20);
            imageView.setFitWidth(20);
            deleteButton.setGraphic(imageView);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        HBox buttonBox = new HBox(400, backButton, deleteButton);

        HBox scheduleBox = new HBox(scheduleLabel);
        scheduleBox.setAlignment(Pos.BASELINE_LEFT);

        VBox centerBox = new VBox(10, titleLabel, scheduleBox);
        centerBox.setAlignment(Pos.TOP_CENTER);
        VBox.setMargin(titleLabel, new Insets(20, 20, 10, 20));
        VBox.setMargin(scheduleBox, new Insets(10, 20, 20, 20));

        BorderPane layout = new BorderPane();
        layout.setTop(buttonBox);
        layout.setCenter(centerBox);
        BorderPane.setAlignment(buttonBox, Pos.TOP_CENTER);
        BorderPane.setMargin(buttonBox, new Insets(20, 20, 20, 20));
        detailJadwal.getChildren().add(layout);
    
        Scene scene = new Scene(detailJadwal, 500, 600);
        scene.getStylesheets().add("styles/stylesDetail.css");
        return scene;
    }
    
    public static void tampilkanDaftarHarian(Stage primaryStage, Button triggerButton) {
        if (popupmuncul) {
            return;
        }
        popupmuncul = true;
        
        
        Stage popup = new Stage();
        popup.initOwner(primaryStage);
        popup.initModality(Modality.NONE);
        popup.initStyle(StageStyle.TRANSPARENT);
    
        VBox daftar = new VBox(10);
        daftar.setPadding(new Insets(10));
        daftar.setAlignment(Pos.TOP_LEFT);
        daftar.setPrefSize(360, 300);
        daftar.setId("box-daftar");
    
        Label titleLabel = new Label("Today's Schedule");
        titleLabel.setId("label-title");
        daftar.getChildren().addAll(titleLabel);
        
        LocalDate today = LocalDate.now();
        List<String[]> todaySchedules = DbManager.loadDataForDate(today);
    
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(daftar);
        scrollPane.setPrefSize(380, 300);
        scrollPane.setFitToWidth(true); 

        Scene popupScene = new Scene(scrollPane);
        popupScene.setFill(null);
        popupScene.getStylesheets().add("/styles/stylesDaftarPengingat.css");
        popup.setScene(popupScene);
        
        
    

        // // Scene primaryScene = primaryStage.getScene();
        // // primaryScene.getRoot().addEventFilter(javafx.scene.input.MouseEvent.MOUSE_PRESSED, event -> {
        // //     if (popup.isShowing() && !popup.getScene().getRoot().contains(event.getSceneX(), event.getSceneY())) {
        // //         popup.hide();
        // //     }
        // // });

        // popup.show();

        

        for (String[] jadwal : todaySchedules) {
            Label scheduleLabel = new Label(jadwal[4] + "\t" + jadwal[1]);
            scheduleLabel.setPrefWidth(340);
            if ("rendah".equals(jadwal[2])) {
                scheduleLabel.setId("rendah-label");
            } else if ("sedang".equals(jadwal[2])) {
                scheduleLabel.setId("sedang-label");
            } else if ("tinggi".equals(jadwal[2])) {
                scheduleLabel.setId("tinggi-label");
            }

            scheduleLabel.setOnMouseClicked(e -> {
                Scene detailScene = detailScene(primaryStage, jadwal, primaryStage.getScene());
                primaryStage.setScene(detailScene);
                popup.hide();
                popupmuncul = false;
            });
            
            daftar.getChildren().add(scheduleLabel);

            // Scene scene = new Scene(scheduleLabel);
            // scene.getStylesheets().add("/styles/stylesDaftarPengingat.css");
            // return;
        }
    
        Bounds bounds = triggerButton.localToScreen(triggerButton.getBoundsInLocal());
        popup.setX(bounds.getMinX());
        popup.setY(bounds.getMaxY() + 5);
        popup.show();

        scrollPane.setTranslateY(-300);
        TranslateTransition slide = new TranslateTransition(Duration.millis(300), scrollPane);
        slide.setFromY(-300);
        slide.setToY(0);
        slide.play();

        
        // popup.getContent().add(scrollPane);
        // Scene popupscene = primaryStage.getScene();
        primaryStage.getScene().addEventFilter(javafx.scene.input.MouseEvent.MOUSE_PRESSED, event -> {
            if (popup.isShowing() && !bounds.contains(event.getScreenX(), event.getScreenY())) {
                popup.hide();
                popupmuncul = false;
            }
        });
    }
    
}