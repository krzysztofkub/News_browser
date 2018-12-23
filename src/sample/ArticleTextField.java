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


    public ArticleTextField(String text, String title, String intro, String artcile, String mainPhoto) {
        super(text);
        count++;
        this.setFont(new Font(20));
        this.setStyle("-fx-highlight-fill: null;\n" + "-fx-highlight-text-fill: null;");
        this.setEditable(false);
        this.setOnMouseClicked(e -> {
            Stage stageTheTextFieldBelongs = (Stage) this.getScene().getWindow();
            Parent articleRoot = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ArticleScene.fxml"));
            try {
                articleRoot = loader.load();

            } catch (IOException e1) {
                e1.printStackTrace();
            }
            ArticleScene controller = loader.getController();
            controller.setMainStage(stageTheTextFieldBelongs);
            controller.getTitle().setText(title);
            controller.getIntro().setText(intro);

            controller.getWebView().getEngine().loadContent(Article.getImages(mainPhoto) + Article.getImages(artcile));

            Scene articleScene = new Scene(articleRoot);
            Stage articleStage = new Stage();

            articleStage.setScene(articleScene);
            articleStage.setMaximized(true);
            stageTheTextFieldBelongs.hide();
            articleStage.show();


        });
    }


    public static int getCount() {
        return count;
    }
}
