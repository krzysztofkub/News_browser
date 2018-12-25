import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loadingScreenController implements Initializable {
    @FXML
    public BorderPane pane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new LoadingScreen().start();
    }


    class LoadingScreen extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("firstPage.fxml"));
                        Parent root = null;
                        try {
                            root = loader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
                        Stage stage = new Stage();
                        stage.setTitle("Prezent dla Dziadka");
                        stage.setScene(scene);
                        stage.setMaximized(true);
                        stage.show();

                        pane.getScene().getWindow().hide();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
