package sample;

import javafx.scene.control.TextField;

public class ArticleTextField extends TextField {
    private static int count = 0;


    public ArticleTextField() {
        count++;
    }


    public ArticleTextField(String text) {
        super(text);
        count++;
    }


    public static int getCount() {
        return count;
    }
}
