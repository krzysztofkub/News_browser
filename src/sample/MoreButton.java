package sample;

import javafx.scene.control.Button;

public class MoreButton extends Button {
    private int count;
    public MoreButton(String text) {
        super(text);
        this.setPrefWidth(600);
        count = 0;
    }


    public int getCount() {
        return count;
    }


    public void setCount(int count) {
        this.count = count;
    }
}
