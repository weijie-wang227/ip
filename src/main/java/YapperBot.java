import java.util.Objects;
import java.util.Scanner;
public class YapperBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String greeting = """
                --------------------------------
                Hello! I'm YapperBot
                What can I do for you?
                --------------------------------
                """;
        String exit = """
                --------------------------------
                Bye. Hope to see you again soon!
                --------------------------------
                """;
        System.out.println(greeting);
        while (true) {
            String userInput = sc.nextLine();
            if (Objects.equals(userInput, "bye")) {
                System.out.println(exit);
                break;
            } else {
                System.out.println(userInput);
            }
        }
    }
}
