package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SportArticleTextField extends TextField {
    private static int count = 1;

    public SportArticleTextField() {
        count++;
    }

    public SportArticleTextField(String text, String href) {
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
            List<String> article = Article.getArticle(href);
            controller.getTitle().setText(article.get(0));
            controller.getWebView().getEngine().loadContent(article.get(3) + article.get(1) + article.get(2));

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
