package yapper;
import javafx.application.Application;
import yapper.commands.Command;
import yapper.tasks.TaskList;
import yapper.ui.Ui;

/**
 * Main bot
 */
public class YapperBot {


    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises YapperBot with a new UI and Storage
     * @param filePath
     */
    public YapperBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public String getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            String response = c.execute(tasks, ui, storage);
            return response;
        } catch (InvalidInputException e) {
            return e.getMessage();
        }
    }
}
