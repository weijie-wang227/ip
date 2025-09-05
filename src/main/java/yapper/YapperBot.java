package yapper;
import yapper.commands.Command;
import yapper.tasks.TaskList;

/**
 * Main bot
 */
public class YapperBot {


    private Storage storage;
    private TaskList tasks;

    /**
     * Initialises YapperBot with a new UI and Storage
     * @param filePath
     */
    public YapperBot(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Returns response of YapperBot in response to userInput
     * @param userInput
     * @return
     */
    public String getResponse(String userInput) {
        try {
            Command c = Parser.parse(userInput);
            return c.execute(tasks, storage);
        } catch (InvalidInputException e) {
            return e.getMessage();
        }
    }
}
