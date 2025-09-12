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
    private static final String DEFAULTFILEPATH = "data/YapperBot.txt";
    private static final String DEFAULTARCHIVEPATH = "data/Archive.txt";

    private final YapperBot yapperBot = new YapperBot(DEFAULTFILEPATH, DEFAULTARCHIVEPATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            assert Main.class.getResource("/view/MainWindow.fxml") != null : "MainWindow.fxml not found in /view/";
            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "FXML loading failed: AnchorPane is null";
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setYapper(yapperBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
