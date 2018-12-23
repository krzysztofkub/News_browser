package sample;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.List;

public class Controller {
    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    private MoreButton moreButton;



    public void initialize() {
        tab1.setStyle("-fx-font-size: 16pt;");
        tab2.setStyle("-fx-font-size: 16pt;");

        VBox vBox1 = new VBox();
        vBox1.setAlignment(Pos.CENTER);
        //First view
        List<List<String>> page = Article.getAllArticles();
        for (List<String> article : page) {
            ArticleTextField textField = new ArticleTextField(ArticleTextField.getCount() + " : " + article.get(0));
            vBox1.getChildren().add(textField);
        }

        //MoreButton
        moreButton = new MoreButton("Więcej");
        moreButton.setOnMouseClicked(s -> {
            moreButton.setCount(moreButton.getCount() + 1);
            int count = page.size();
            page.addAll(Article.getNextArticles(moreButton.getCount()));
            for (int i = count; i < page.size(); i++) {
                ArticleTextField textField = new ArticleTextField(ArticleTextField.getCount() + " : " + page.get(i).get(0));

                vBox1.getChildren().add(vBox1.getChildren().size() - 1, textField);
            }
        });
        vBox1.getChildren().add(moreButton);

        ScrollPane scrollPane = new ScrollPane(vBox1);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        tab1.setContent(scrollPane);
    }
}
