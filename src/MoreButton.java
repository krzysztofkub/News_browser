import javafx.scene.control.Button;

class MoreButton extends Button {
    private int count;

    MoreButton(String text) {
        super(text);
        count = 0;
        this.setPrefWidth(1000);
        this.setPrefHeight(40);
        this.setMinHeight(50);

    }

    int getCount() {
        return count;
    }

    void setCount(int count) {
        this.count = count;
    }
}
