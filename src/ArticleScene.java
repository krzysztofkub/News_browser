import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class ArticleScene {
    private Stage mainStage;
    @FXML
    private Button button;
    @FXML
    private TextField title;
    @FXML
    private WebView webView;


    void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }


    TextField getTitle() {
        return title;
    }


    WebView getWebView() {
        return webView;
    }

    @FXML
    void buttonOnClick() {
        button.getScene().getWindow().hide();
        this.mainStage.show();
    }
}


