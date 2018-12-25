import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.List;

public class FirstPageController {
    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    private MoreButton newsMoreButton;
    private MoreButton sportMoreButton;

    public void initialize() {
        tab1.setStyle("-fx-font-size: 14pt;");
        tab2.setStyle("-fx-font-size: 14pt;");
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();
        vBox1.setAlignment(Pos.CENTER);
        vBox2.setAlignment(Pos.CENTER);
        /*
        First view
         */
        //News
        List<List<String>> newsArticles = Article.newsTitles();
        for (List<String> newsArticle : newsArticles) {
            ArticleTextField textField = new ArticleTextField(ArticleTextField.getCount() + " : " + newsArticle.get(1),newsArticle.get(0));
            vBox1.getChildren().add(textField);
        }
        //Sport
        List<List<String>> sportArticles = Article.sportTitles();
        for (List<String> sportArticle : sportArticles) {
            SportArticleTextField textField = new SportArticleTextField(SportArticleTextField.getCount() + " : " + sportArticle.get(1),sportArticle.get(0));
            vBox2.getChildren().add(textField);
        }

        //NewsMoreButton
        newsMoreButton = new MoreButton("Załaduj więcej artykułów");
        newsMoreButton.setOnMouseClicked(s -> {
            newsMoreButton.setCount(newsMoreButton.getCount() + 1);
            int count = newsArticles.size();
            newsArticles.addAll(Article.getNextNewsArticles(newsMoreButton.getCount()));
            for (int i = count; i < newsArticles.size(); i++) {
                ArticleTextField textField = new ArticleTextField(ArticleTextField.getCount() + " : " + newsArticles.get(i).get(1), newsArticles.get(i).get(0) );
                vBox1.getChildren().add(vBox1.getChildren().size() - 1, textField);
            }
        });
        vBox1.getChildren().add(newsMoreButton);

        //SportMoreButton
        sportMoreButton = new MoreButton("Załaduj więcej artykułów");
        sportMoreButton.setOnMouseClicked(s -> {
            sportMoreButton.setCount(sportMoreButton.getCount() + 1);
            int count = sportArticles.size();
            sportArticles.addAll(Article.getNextSportArticles(sportMoreButton.getCount()));
            for (int i = count; i < sportArticles.size(); i++) {
                SportArticleTextField textField = new SportArticleTextField(SportArticleTextField.getCount() + " : " + sportArticles.get(i).get(1), sportArticles.get(i).get(0) );
                vBox2.getChildren().add(vBox2.getChildren().size() - 1, textField);
            }
        });
        vBox2.getChildren().add(sportMoreButton);

        ScrollPane scrollPane1 = new ScrollPane(vBox1);
        ScrollPane scrollPane2 = new ScrollPane(vBox2);
        scrollPane1.setFitToHeight(true);
        scrollPane1.setFitToWidth(true);
        scrollPane2.setFitToHeight(true);
        scrollPane2.setFitToWidth(true);
        tab1.setContent(scrollPane1);
        tab2.setContent(scrollPane2);
    }
}
