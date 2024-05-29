package id.rezka.tuprak9;

import java.util.List;

import id.rezka.tuprak9.controller.DbManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyList {
    public static Scene createScene(Stage primaryStage, App app) {
        Label myListLabel = new Label("My List");
        myListLabel.setId("search-label");
    

        // nampilkan semua list schedule scroll panenya nanti tmpil kalau dtanya lewat dri ukuran stageny kita
        // pke sql

        ScrollPane scroll= new ScrollPane();
        scroll.setId("scroll-pane");

        VBox list = new VBox(10);
        list.setPadding(new Insets(10));
        list.setId("list-box");

        //ArrayList
        List<String[]> allSchedule = DbManager.loadData();

        for (String[] schedule : allSchedule){
            Label scheduLabel = new Label("\t" + schedule[3] + "\t\t" + schedule[1]);
            scheduLabel.setPrefWidth(460);
            scheduLabel.setPrefHeight(40);
            scheduLabel.setId("schedule-label");
            scheduLabel.setOnMouseClicked(e -> {
                Scene detailScene = DaftarPengingatHarian.detailScene(primaryStage, schedule, primaryStage.getScene());
                primaryStage.setScene(detailScene);
            });
            list.getChildren().add(scheduLabel);
        }

        scroll.setContent(list);
        scroll.setFitToWidth(true);

        Button backButton = new Button("Back");
        backButton.setId("back-btn");
        backButton.setOnAction(e -> primaryStage.setScene(app.createMainScene(primaryStage)));

        BorderPane layout = new BorderPane();
        layout.setTop(myListLabel);
        layout.setCenter(scroll);
        layout.setBottom(backButton);
        BorderPane.setAlignment(myListLabel, Pos.TOP_CENTER);
        BorderPane.setAlignment(scroll, Pos.TOP_CENTER);
        BorderPane.setMargin(myListLabel, new Insets(20, 0, 20, 0));
        BorderPane.setAlignment(backButton, Pos.CENTER);
        BorderPane.setMargin(backButton, new Insets(0, 20, 20, 20));
        layout.setId("lyt-list");

        Scene scene = new Scene(layout, 500, 600);
        scene.getStylesheets().add("/styles/stylesMyList.css");

        return scene;
        
    }
}