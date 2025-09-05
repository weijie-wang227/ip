package yapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    private Label text;

    public DialogBox(String s) {
        text = new Label(s);
        text.setWrapText(true);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String s) {
        return new DialogBox(s);
    }

    public static DialogBox getDukeDialog(String s) {
        var db = new DialogBox(s);
        db.flip();
        return db;
    }
}
