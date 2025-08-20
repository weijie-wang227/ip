import java.util.Objects;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        List<Task> previous = new ArrayList<>();
        Pattern markPattern = Pattern.compile("^mark\\s+(\\d+)$");
        Pattern unmarkPattern = Pattern.compile("^unmark\\s+(\\d+)$");

        System.out.println(greeting);
        while (true) {
            String userInput = sc.nextLine();
            Matcher markMatcher = markPattern.matcher(userInput);
            Matcher unmarkMatcher = unmarkPattern.matcher(userInput);

            if (Objects.equals(userInput, "bye")) {
                System.out.println(exit);
                break;
            } else if (Objects.equals(userInput, "list")) {
                for (int i = 0; i < previous.size(); i++) {
                    System.out.println((i + 1) + ", " + previous.get(i));
                }
            } else if (markMatcher.matches()) {
                String numberStr = markMatcher.group(1);
                int index = Integer.parseInt(numberStr) - 1;
                if (index >= previous.size() || index < 0) {
                    System.out.println("Index out of range");
                } else {
                    Task task = previous.get(index);
                    if (task.isDone()) {
                        System.out.println("This task is already marked as done:");
                        System.out.println(task);
                    } else {
                        task.mark();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(task);
                    }
                }
            } else if (unmarkMatcher.matches()) {
                String numberStr = unmarkMatcher.group(1);
                int index = Integer.parseInt(numberStr) - 1;
                if (index >= previous.size() || index < 0) {
                    System.out.println("Index out of range");
                } else {
                    Task task = previous.get(index);
                    if (!task.isDone()) {
                        System.out.println("This task is already marked as not done yet:");
                        System.out.println(task);
                    } else {
                        task.unmark();
                        System.out.println("Ok, I've marked this task as not done yet:");
                        System.out.println(task);
                    }
                }
            } else {
                previous.add(new Task(userInput));
                System.out.println("added: " + userInput);
            }
        }
    }
}
