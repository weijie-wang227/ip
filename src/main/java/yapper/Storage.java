package yapper;
import yapper.tasks.Task;
import yapper.tasks.TaskList;
import yapper.tasks.Todo;
import yapper.tasks.Deadline;
import yapper.tasks.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage {

    private final String fileName;
    public Storage(String fileName) {
        this.fileName = fileName;
        File file = new File(fileName);
        File dir = file.getParentFile();
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Directory created: " + dir);
            } else {
                throw new RuntimeException("Failed to create Directory");
            }
        }

        try
        {
            if (file.createNewFile()) {
                System.out.println("Save File created: " + fileName);
            } else {
                System.out.println("Save File already exits");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> load() {
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

    private Task decode(String line) {
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

    public void save(TaskList tasks) throws IOException{
        try (FileWriter writer = new FileWriter(fileName)) {
            tasks.foreach(task -> {
                try {
                    writer.write(task.saveState());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

}
