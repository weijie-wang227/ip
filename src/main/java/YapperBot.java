import java.util.Objects;
import java.util.Scanner;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.*;
public class YapperBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String greeting = """
                --------------------------------
                Hello! I'm YapperBot
                What can I do for you?
                --------------------------------
                """;
        String exitMessage = """
                --------------------------------
                Bye. Hope to see you again soon!
                --------------------------------
                """;
        List<Task> previous = new ArrayList<>();
        Map<Pattern, Consumer<Matcher>> commands = new LinkedHashMap<>();

        // mark command
        commands.put(Pattern.compile("^mark\\s+(\\d+)$"), matcher -> {
            int index = Integer.parseInt(matcher.group(1)) - 1;
            if (index >= previous.size() || index < 0) {
                System.out.println("Index out of range");
            } else {
                Task task = previous.get(index);
                task.mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task);
            }
        });

        // unmark command
        commands.put(Pattern.compile("^unmark\\s+(\\d+)$"), matcher -> {
            int index = Integer.parseInt(matcher.group(1)) - 1;
            if (index >= previous.size() || index < 0) {
                System.out.println("Index out of range");
            } else {
                Task task = previous.get(index);
                task.unmark();
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println(task);
            }
        });


        // main loop
        System.out.println(greeting);
        while (true) {
            String userInput = sc.nextLine();

            if (Objects.equals(userInput, "bye")) {
                System.out.println(exitMessage);
                break;
            } else if (Objects.equals(userInput, "list")) {
                for (int i = 0; i < previous.size(); i++) {
                    System.out.println((i + 1) + ", " + previous.get(i));
                }
            } else {
                boolean matched = false;
                for (Map.Entry<Pattern, Consumer<Matcher>> entry : commands.entrySet()) {
                    Matcher matcher = entry.getKey().matcher(userInput);
                    if (matcher.matches()) {
                        entry.getValue().accept(matcher);
                        matched = true;
                        break;
                    }
                }
                if (!matched) {
                    //Error handling
                    sc.close();
                }
            }
        }
        sc.close();
    }
}
