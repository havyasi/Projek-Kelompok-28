// package scene;

// import javafx.application.Application;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.layout.StackPane;
// import javafx.scene.layout.VBox;
// import javafx.stage.Stage;
// public class home  extends Application{
//     @Override
//     public void start(Stage primaryStage) throws Exception {
//          primaryStage.setResizable(false);

//         Button button1 = new Button("..");
//         button1.setMaxWidth(470);
//         button1.setMaxHeight(40);
    

//         StackPane stackPane = new StackPane(button1);
//         stackPane.setAlignment(Pos.TOP_CENTER);
//         stackPane.setPadding(new javafx.geometry.Insets(10));

//         VBox root = new VBox(stackPane);
//         root.setStyle("-fx-background-color: #222831;");

//         Scene scene = new Scene(root, 500, 600);

//         primaryStage.setScene(scene);
//         primaryStage.show();
        
//     }
    

//     public static void main(String[] args) {
//         launch(args);
        
//     }
// }
