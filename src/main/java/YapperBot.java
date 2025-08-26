import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.*;
public class YapperBot {
    public static void main(String[] args) {
        String fileName = initData();

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
        List<Task> taskList = loadFile(fileName);
        Map<Pattern, Consumer<Matcher>> commands = new LinkedHashMap<>();

        // mark command
        commands.put(Pattern.compile("^mark(?:\\s+(\\d+))?$"
        ), matcher -> {
            String digit = matcher.group(1);
            if (digit == null) {
                throw new InvalidInputException("OOPS!!! The index cannot be empty");
            }
            int index = Integer.parseInt(digit) - 1;
            if (index >= taskList.size() || index < 0) {
                throw new InvalidInputException("OOPS!!! The index is out of range");
            } else {
                Task task = taskList.get(index);
                task.mark();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(task);
            }
        });

        // unmark command
        commands.put(Pattern.compile("^unmark\\s+(\\d+)$"), matcher -> {
            String digit = matcher.group(1);
            if (digit == null) {
                throw new InvalidInputException("OOPS!!! The index cannot be empty");
            }
            int index = Integer.parseInt(digit) - 1;
            if (index >= taskList.size() || index < 0) {
                throw new InvalidInputException("OOPS!!! The index is out of range");
            } else {
                Task task = taskList.get(index);
                task.unmark();
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println(task);
            }
        });

        // delete command
        commands.put(Pattern.compile("^delete\\s+(\\d+)$"), matcher -> {
            String digit = matcher.group(1);
            if (digit == null) {
                throw new InvalidInputException("OOPS!!! The index cannot be empty");
            }
            int index = Integer.parseInt(digit) - 1;
            if (index >= taskList.size() || index < 0) {
                throw new InvalidInputException("OOPS!!! The index is out of range");
            } else {
                Task task = taskList.get(index);
                taskList.remove(index);
                System.out.println("Ok, I've removed this task");
                System.out.println(task.deleteMessage(taskList.size()));
            }
        });

        //todo
        commands.put(Pattern.compile("^todo(?:\\s+(.*))?$"), matcher -> {
            String desc = matcher.group(1);
            Task task = new Todo(desc);
            if (desc == null) {
                throw new InvalidInputException("OOPS!!! The description of a todo cannot be empty");
            }
            taskList.add(task);
            System.out.println(task.forDisplay(taskList.size()));
        });

        //deadline
        commands.put(Pattern.compile("^deadline(?:\\s+(.+?))?(?:\\s*/by\\s+(.+))?$"
        ), matcher -> {
            String desc = matcher.group(1);
            String end = matcher.group(2);
            Task task;
            if (desc == null|| end == null) {
                throw new InvalidInputException("OOPS!!! A deadline task has to have both a description and end date");
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
                LocalDateTime dateTime = LocalDateTime.parse(end, formatter);
                task = new Deadline(desc, dateTime);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format: " + end);
                return;
            }
            taskList.add(task);
            System.out.println(task.forDisplay(taskList.size()));
        });

        //event
        commands.put(Pattern.compile("^event(?:\\s+(.+?))?(?:\\s*/from\\s+(.+?))?(?:\\s*/to\\s+(.+))?$"), matcher -> {
            String desc = matcher.group(1);
            String start = matcher.group(2);
            String end = matcher.group(3);
            Task task;
            if (desc == null || start == null || end == null) {
                throw new InvalidInputException("OOPS!!! A event has to have a description, a start date and an end date");
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
                LocalDateTime startTime = LocalDateTime.parse(start, formatter);
                LocalDateTime endTime = LocalDateTime.parse(end, formatter);
                task = new Event(desc, startTime, endTime);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format: " + end);
                return;
            }
            taskList.add(task);
            System.out.println(task.forDisplay(taskList.size()));

        });


        // main loop
        System.out.println(greeting);
        while (true) {
            String userInput = sc.nextLine();

            if (Objects.equals(userInput, "bye")) {
                System.out.println(exitMessage);
                break;
            } else if (Objects.equals(userInput, "list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ", " + taskList.get(i));
                }
            } else {
                try {
                    processInput(userInput, commands, fileName, taskList);
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        sc.close();
    }

    private static void processInput(String userInput, Map<Pattern, Consumer<Matcher>> commands,
                                    String fileName, List<Task> list) {
        boolean matched = false;
        for (Map.Entry<Pattern, Consumer<Matcher>> entry : commands.entrySet()) {
            Matcher matcher = entry.getKey().matcher(userInput);
            if (matcher.matches()) {
                entry.getValue().accept(matcher);
                saveData(fileName, list);
                matched = true;
                break;
            }
        }
        if (!matched) {
            throw new InvalidInputException();
        }
    }

    private static String initData() {
        String dirName = "data";
        String fileName = "data/YapperBot.txt";

        File dir = new File(dirName);
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Directory created: " + dirName);
            } else {
                throw new RuntimeException("Failed to create Directory");
            }
        }

        // Create file if it doesn't exist and write to it
        File file = new File(fileName);
        try
        {
            if (file.createNewFile()) {
                System.out.println("Save File created: " + dirName);
            } else {
                System.out.println("Save File already exits");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    private static void saveData(String fileName, List<Task> list) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (Task task : list) {
                writer.write(task.saveState());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Task> loadFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            List<Task> taskList= new ArrayList<Task>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = decode(line);
                taskList.add(task);
                System.out.println(task + " loaded");
            }
            return taskList;
        } catch (FileNotFoundException | InvalidInputException | DateTimeParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static Task decode(String line) throws InvalidInputException {
        Pattern pattern = Pattern.compile("^T\\s*\\|\\s*(\\d+)\\s*\\|\\s*(.+)$");
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            String number = matcher.group(1);       // "1"
            String description = matcher.group(2);  // "readbook"
            return new Todo(description, Objects.equals(number, "1"));
        }

        pattern = Pattern.compile("^D\\s*\\|\\s*(\\d+)\\s*\\|\\s*([^|]+)\\s*\\|\\s*(.+)$");
        matcher = pattern.matcher(line);
        if (matcher.matches()) {
            String number = matcher.group(1);       // "0"
            String description = matcher.group(2);  // "return book"
            String deadline = matcher.group(3);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(deadline, formatter);
            return new Deadline(description, dateTime, Objects.equals(number, "1"));
        }

        pattern = Pattern.compile("^E\\s*\\|\\s*(\\d+)\\s*\\|\\s*([^|]+)\\s*\\|\\s*(.+?)\\s*-\\s*(.+)$");
        matcher = pattern.matcher(line);
        if (matcher.matches()) {
            String number = matcher.group(1);        // "0"
            String description = matcher.group(2);   // "meeting"
            String from = matcher.group(3);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
            LocalDateTime fromTime = LocalDateTime.parse(from, formatter);
            String to = matcher.group(4);
            LocalDateTime toTime = LocalDateTime.parse(to, formatter);
            return new Event(description, fromTime, toTime, Objects.equals(number, "1"));
        }

        throw new InvalidInputException("Save File corrupted");
    }
}
