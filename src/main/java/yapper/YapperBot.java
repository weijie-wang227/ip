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
    private YapperBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (RuntimeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs program loop
     */
    public void run() {
        /** ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (InvalidInputException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
         */
    }

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Ui.class, args);
        new YapperBot("data/YapperBot.txt").run();
    }
}
