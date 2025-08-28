package yapper;
import java.util.Scanner;

/**
 * Represent Ui for the user to interact with
 */
import yapper.tasks.TaskList;

public class Ui {
    private final Scanner sc;

    /**
     * Initialises a new Ui with a Scanner
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints welcome message
     */
    public void showWelcome() {
        System.out.println("--------------------------------\n" +
                "Hello! I'm YapperBot\n" +
                "What can I do for you?\n" +
                "--------------------------------");
    }

    /**
     * Prints exit message
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the Error message for user
     * @param message
     */
    public void showError (String message) {
        System.out.println(message);
    }

    /**
     * Prints message
     * @param message
     */
    public void display(String message) {
        System.out.println(message);
    }

    /**
     * Prints line to separate each message
     */
    public void showLine() {
        System.out.println("--------------------------------");
    }

    /**
     * Reads user input
     * @return
     */
    public String readCommand() {
        return sc.nextLine();
    }

    public void displayList(TaskList tasks) {
        tasks.foreachI((task, i) -> {display((i + 1) + ", " + task);});
    }
}
