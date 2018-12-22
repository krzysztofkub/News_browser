package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Hello World");

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        Controller controller = loader.getController();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
