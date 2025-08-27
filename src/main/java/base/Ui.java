package base;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("--------------------------------\n" +
                "Hello! I'm YapperBot\n" +
                "What can I do for you?\n");
    }

    public void showBye() {
        System.out.println(
                "Bye. Hope to see you again soon!\n" +
                "--------------------------------");
    }

    public void showError (String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println("--------------------------------\n");
    }

    public String readCommand() {
        sc.nextLine();
    }
}
