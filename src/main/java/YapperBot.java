import java.util.Objects;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
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
        List<String> previous = new ArrayList<>();
        System.out.println(greeting);
        while (true) {
            String userInput = sc.nextLine();
            if (Objects.equals(userInput, "bye")) {
                System.out.println(exit);
                break;
            } else if (Objects.equals(userInput, "list")) {
                for (int i = 0; i < previous.size(); i++) {
                    System.out.println((i + 1) + ", " + previous.get(i));
                }
            } else {
                previous.add(userInput);
                System.out.println("added: " + userInput);
            }
        }
    }
}
