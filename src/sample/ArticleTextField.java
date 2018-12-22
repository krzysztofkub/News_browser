package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class ArticleTextField extends TextField {
    private static int count = 1;


    public ArticleTextField() {
        count++;
    }


    public ArticleTextField(String text) {
        super(text);
        count++;
        this.setFont(new Font(20));
        this.setStyle("-fx-highlight-fill: null;\n" + "-fx-highlight-text-fill: null;");
        this.setEditable(false);
        this.setOnMouseClicked(e -> {
            Stage stageTheTextFieldBelongs = (Stage) this.getScene().getWindow();
            stageTheTextFieldBelongs.hide();
            Parent articleRoot = null;
            try {
                articleRoot = FXMLLoader.load(getClass().getResource("ArticleScene.fxml"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            Scene articleScene = new Scene(articleRoot);
            Stage articleStage = new Stage();
            articleStage.setScene(articleScene);
            articleStage.setMaximized(true);
            articleStage.show();
        });
    }


    public static int getCount() {
        return count;
    }
}
