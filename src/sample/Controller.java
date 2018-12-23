package sample;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.List;

public class Controller {
    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    private MoreButton newsMoreButton;
    private MoreButton sportMoreButton;

    public void initialize() {
        tab1.setStyle("-fx-font-size: 16pt;");
        tab2.setStyle("-fx-font-size: 16pt;");
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();
        vBox1.setAlignment(Pos.CENTER);
        vBox2.setAlignment(Pos.CENTER);
        /*
        First view
         */
        //News
        List<List<String>> page = Article.getAllNewsArticles();
        for (List<String> article : page) {
            ArticleTextField textField = new ArticleTextField(ArticleTextField.getCount() + " : " + article.get(0), article.get(0),article.get(1), article.get(2),article.get(3));
            vBox1.getChildren().add(textField);
        }
        //Sport
        List<List<String>> sportArticles = Article.sportTitles();
        for (List<String> sportArticle : sportArticles) {
            SportArticleTextField textField = new SportArticleTextField(SportArticleTextField.getCount() + " : " + sportArticle.get(1),sportArticle.get(0));
            vBox2.getChildren().add(textField);
        }

        //NewsMoreButton
        newsMoreButton = new MoreButton("Więcej");
        newsMoreButton.setOnMouseClicked(s -> {
            newsMoreButton.arm();
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(e -> newsMoreButton.disarm());
            pause.play();
            newsMoreButton.setCount(newsMoreButton.getCount() + 1);
            int count = page.size();
            page.addAll(Article.getNextNewsArticles(newsMoreButton.getCount()));
            for (int i = count; i < page.size(); i++) {
                ArticleTextField textField = new ArticleTextField(ArticleTextField.getCount() + " : " + page.get(i).get(0), page.get(i).get(0),page.get(i).get(1), page.get(i).get(2),page.get(i).get(3));
                vBox1.getChildren().add(vBox1.getChildren().size() - 1, textField);
            }
        });
        vBox1.getChildren().add(newsMoreButton);

        //SportMoreButton
        sportMoreButton = new MoreButton("Więcej");
        sportMoreButton.setOnMouseClicked(s -> {
            sportMoreButton.arm();
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(e -> sportMoreButton.disarm());
            pause.play();
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
