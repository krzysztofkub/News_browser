package sample;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;

public class ArticleScene {
    private Scene previousScene;
    @FXML
    private TextArea intro;

    public void initialize() {
        intro.setText("test");
    }
}
