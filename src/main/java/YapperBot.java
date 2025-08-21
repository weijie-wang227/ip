import java.util.Objects;
import java.util.Scanner;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.*;
public class YapperBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String greeting =
                "--------------------------------\n" +
                "Hello! I'm YapperBot\n" +
                "What can I do for you?\n" +
                "--------------------------------";
        String exitMessage =
                "--------------------------------\n" +
                "Bye. Hope to see you again soon!\n" +
                "--------------------------------";
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

        //todo
        commands.put(Pattern.compile("^todo(?:\\s+(.*))?$"), matcher -> {
            String desc = matcher.group(1);
            Task task = new Todo(desc);
            if (desc == null) {
                throw new InvalidInputException("OOPS!!! The description of a todo cannot be empty");
            }
            previous.add(task);
            System.out.println(task.forDisplay(previous.size()));
        });

        //deadline
        commands.put(Pattern.compile("^deadline(?:\\s+(.+?))?(?:\\s*/by\\s+(.+))?$"
        ), matcher -> {
            String desc = matcher.group(1);
            String end = matcher.group(2);
            if (desc == null|| end == null) {
                throw new InvalidInputException("OOPS!!! A deadline task has to have both a description and end date");
            }
            Task task = new Deadline(desc, end);
            previous.add(task);
            System.out.println(task.forDisplay(previous.size()));
        });

        //event
        commands.put(Pattern.compile("^event(?:\\s+(.+?))?(?:\\s*/from\\s+(.+?))?(?:\\s*/to\\s+(.+))?$"), matcher -> {
            String desc = matcher.group(1);
            String start = matcher.group(2);
            String end = matcher.group(3);
            if (desc == null || start == null || end == null) {
                throw new InvalidInputException("OOPS!!! A event has to have a description, a start date and an end date");
            }
            Task task = new Event(desc, start, end);
            previous.add(task);
            System.out.println(task.forDisplay(previous.size()));

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
                try {
                    processInput(userInput, commands);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        sc.close();
    }

    public static void processInput(String userInput, Map<Pattern, Consumer<Matcher>> commands) {
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
            throw new InvalidInputException();
        }
    }
}
