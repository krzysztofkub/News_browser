package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ArticleScene {
    private Stage mainStage;
    @FXML
    private AnchorPane article;
    @FXML
    private Button button;
    @FXML
    private TextArea intro;
    @FXML
    private TextField title;


    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }


    @FXML
    void buttonOnClick() {
        button.getScene().getWindow().hide();
        this.mainStage.show();
    }


}


