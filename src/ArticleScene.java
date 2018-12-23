import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class ArticleScene {
    private Stage mainStage;
    @FXML
    private AnchorPane article;
    @FXML
    private Button button;
    @FXML
    private TextField title;
    @FXML
    private WebView webView;


    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }


    public TextField getTitle() {
        return title;
    }


    public WebView getWebView() {
        return webView;
    }

    public void initialize() {
        button.setStyle("-fx-background-color: red");

    }

    @FXML
    void buttonOnClick() {
        button.getScene().getWindow().hide();
        this.mainStage.show();
    }
}


