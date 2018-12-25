import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

class SportArticleTextField extends TextField {
    private static int count = 1;


    SportArticleTextField(String text, String href) {
        super(text);
        count++;
        this.setFont(new Font(20));
        this.setStyle("-fx-highlight-fill: null;\n" + "-fx-highlight-text-fill: null;");
        this.setEditable(false);
        this.setOnMouseClicked(e -> {

            Stage stageTheTextFieldBelongs = (Stage) this.getScene().getWindow();
            Parent articleRoot = null;
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("ArticleScene.fxml"));
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

            Scene articleScene = null;
            if (articleRoot != null) {
                articleScene = new Scene(articleRoot);
            }
            if (articleScene != null) {
                articleScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
            }
            Stage articleStage = new Stage();

            articleStage.setScene(articleScene);
            articleStage.setMaximized(true);
            stageTheTextFieldBelongs.hide();
            articleStage.show();
        });
    }


    static int getCount() {
        return count;
    }
}
