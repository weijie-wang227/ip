package base;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("--------------------------------\n" +
                "Hello! I'm YapperBot\n" +
                "What can I do for you?\n" +
                "--------------------------------");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError (String message) {
        System.out.println(message);
    }

    public void display(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println("--------------------------------");
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
