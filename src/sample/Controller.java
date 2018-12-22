package sample;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.EventListener;
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

        List<List<String>> page = Articles.getAllArticles();
        for (List<String> article : page) {
            ArticleTextField textField = new ArticleTextField("1   :  " + article.get(0));
            textField.setFont(new Font(20));
            textField.setStyle("-fx-highlight-fill: null;\n" + "-fx-highlight-text-fill: null;");
            textField.setEditable(false);
            vBox1.getChildren().add(textField);
        }

        //MoreButton
        moreButton = new MoreButton("Więcej");
        moreButton.setOnMouseClicked(s -> {
            moreButton.setCount(moreButton.getCount() + 1);
            List<List<String>> nextPage = Articles.getNextArticles(moreButton.getCount());
            for (List<String> article : nextPage) {
                ArticleTextField textField = new ArticleTextField(article.get(0));
                textField.setFont(new Font(20));
                textField.setStyle("-fx-highlight-fill: null;\n" + "-fx-highlight-text-fill: null;");
                textField.setEditable(false);
                vBox1.getChildren().add(textField);
            }
        });

        vBox1.getChildren().add(moreButton);

        ScrollPane scrollPane = new ScrollPane(vBox1);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        tab1.setContent(scrollPane);

    }


}
