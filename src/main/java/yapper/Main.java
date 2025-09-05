package yapper;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import yapper.ui.MainWindow;


/**
 * Represent Ui for the user to interact with
 */


public class Main extends Application {
    private final String defaultFilePath = "data/YapperBot.txt";

    private final YapperBot yapperBot = new YapperBot(DEFAULTFILEPATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(yapperBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
