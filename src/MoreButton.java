import javafx.scene.control.Button;

public class MoreButton extends Button {
    private int count;

    public MoreButton(String text) {
        super(text);
        count = 0;
        this.setPrefWidth(1000);
        this.setPrefHeight(40);
        this.setMinHeight(50);

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
