package id.rezka.tuprak9;

// import java.io.FileInputStream;
// import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
// import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
// import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InputJadwal {
    public static Scene createScene(Stage primaryStage, App app) {
        
        Label catatanLabel = new Label("Tambahkan Jadwal Baru");
        catatanLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");
        
        Label juduLabel = new Label("Judul");
        juduLabel.setStyle("-fx-text-fill: white; -fx-font-size: 15px;");
        
        TextField jadwalField = new TextField();
        jadwalField.setPromptText("Masukkan judul");
        jadwalField.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 15px;");
        jadwalField.setPrefWidth(400);

        Label jPLabel = new Label("Jenis Prioritas");
        jPLabel.setStyle("-fx-text-fill: white; -fx-font-size: 15px;");

        Button rendahPrio = new Button("Rendah");
        rendahPrio.setPrefWidth(100);
        rendahPrio.setStyle("-fx-font-size: 10px;");

        Button sedangPrio = new Button("Sedang");
        sedangPrio.setPrefWidth(100);
        sedangPrio.setStyle("-fx-font-size: 10px;");

        Button tinggiPrio = new Button("Tinggi");
        tinggiPrio.setPrefWidth(100);
        tinggiPrio.setStyle("-fx-font-size: 10px;");

        HBox jPbutton = new HBox(10,rendahPrio, sedangPrio, tinggiPrio);
        jPbutton.setAlignment(Pos.CENTER);

        Label detailLabel = new Label("Detail");
        detailLabel.setStyle("-fx-text-fill: white; -fx-font-size: 15px;");

        Button tambahWaktu = new Button("Tambahkan waktu");
        tambahWaktu.setPrefWidth(200);
        tambahWaktu.setStyle("-fx-font-size: 10px;");
        tambahWaktu.setOnAction(e ->{

        });
        Button tambahDeskrip = new Button("Tambahkan Deskripsi");
        tambahDeskrip.setPrefWidth(200);
        tambahDeskrip.setStyle("-fx-font-size: 10px;");
        tambahWaktu.setOnAction(e ->{
            
        });

        VBox detailVBox = new VBox(10, detailLabel,tambahWaktu ,tambahDeskrip);

        Button saveButton = new Button("Save");
        saveButton.setStyle("-fx-font-size: 17px;");
        saveButton.setOnAction(e -> {
            App.setJadwal(jadwalField.getText());
            primaryStage.setScene(app.createMainScene(primaryStage)); 
        });
        
        // Button tmbhtugas = new Button("");
        // tmbhtugas.setMaxHeight(90);
        // tmbhtugas.setMaxWidth(10);
        // tmbhtugas.setAlignment(Pos.BASELINE_RIGHT);
        // tmbhtugas.setStyle( "-fx-background-color: rgba(255, 255, 255, 255);" + 
        // "-fx-background-radius : 50%");
        
        // StackPane tmbktgspane = new StackPane(tmbhtugas);
        // tmbktgspane.setAlignment(Pos.BOTTOM_CENTER);
        // tmbktgspane.setPadding(new javafx.geometry.Insets(300, 0, 0, 0));

        // try {
        //     FileInputStream iconStream = new FileInputStream("src/main/resources/image/plus2.png");
        //     Image icon = new Image(iconStream);
    
        //     ImageView imageView = new ImageView(icon);
        //     imageView.setFitHeight(20);
        //     imageView.setFitWidth(20);
        //     tmbhtugas.setGraphic(imageView);
        // } catch (FileNotFoundException e) {
        //     e.printStackTrace();
        // }
            
        VBox layout = new VBox(10, catatanLabel, juduLabel, jadwalField, jPLabel, jPbutton, detailVBox);
        layout.setPadding(new javafx.geometry.Insets(20));
        layout.setAlignment(Pos.BASELINE_LEFT);
        layout.setStyle("-fx-background-color: black; -fx-font-weight: bold; -fx-font-size: 25px");
        layout.getChildren().addAll( saveButton);
        
        return new Scene(layout, 500, 600);
    }
}
