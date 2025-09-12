package yapper;

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

import yapper.tasks.Deadline;
import yapper.tasks.Event;
import yapper.tasks.Task;
import yapper.tasks.TaskList;
import yapper.tasks.Todo;


/**
 * Used to interact with the storage
 */
public class Storage {

    private final String fileName;
    private final String archiveName;

    /**
     * Adds a new file if it doesn't exist
     * @param saveFile path to file
     */
    public Storage(String saveFile, String archiveFile) {
        this.fileName = saveFile;
        this.archiveName = archiveFile;
        File file = new File(fileName);
        File dir = file.getParentFile();
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Directory created: " + dir);
            } else {
                throw new RuntimeException("Failed to create Directory");
            }
        }

        try {
            if (file.createNewFile()) {
                System.out.println("Save File created: " + fileName);
            } else {
                System.out.println("Save File already exits");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads from file into task list
     * @return //List of tasks to create a task list
     */
    public List<Task> load() {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            List<Task> taskList = new ArrayList<Task>();
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

    /**
     * Decode line from save file to recover tasks
     * @param line //String representation from save file
     * @return //The task corresponding to the save file representation
     */
    private Task decode(String line) {
        Pattern pattern = Pattern.compile("^T\\s*\\|\\s*(\\d+)\\s*\\|\\s*(.+)$");
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            assert matcher.groupCount() == 2 : "Todo regex should capture 2 groups";
            String number = matcher.group(1);
            String description = matcher.group(2);
            return new Todo(description, Objects.equals(number, "1"));
        }

        pattern = Pattern.compile("^D\\s*\\|\\s*(\\d+)\\s*\\|\\s*([^|]+)\\s*\\|\\s*(.+)$");
        matcher = pattern.matcher(line);
        if (matcher.matches()) {
            assert matcher.groupCount() == 3 : "Deadline regex should capture 3 groups";
            String number = matcher.group(1);
            String description = matcher.group(2);
            String deadline = matcher.group(3);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(deadline, formatter);
            return new Deadline(description, dateTime, Objects.equals(number, "1"));
        }

        pattern = Pattern.compile("^E\\s*\\|\\s*(\\d+)\\s*\\|\\s*([^|]+)\\s*\\|\\s*(.+?)\\s*-\\s*(.+)$");
        matcher = pattern.matcher(line);
        if (matcher.matches()) {
            assert matcher.groupCount() == 4 : "Event regex should capture 4 groups";
            String number = matcher.group(1);
            String description = matcher.group(2);
            String from = matcher.group(3);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
            LocalDateTime fromTime = LocalDateTime.parse(from, formatter);
            String to = matcher.group(4);
            LocalDateTime toTime = LocalDateTime.parse(to, formatter);
            return new Event(description, fromTime, toTime, Objects.equals(number, "1"));
        }

        throw new InvalidInputException("Save File corrupted");
    }

    /**
     * Writes into the file to update task ist
     */
    public void save(TaskList tasks) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            tasks.foreach(task -> {
                try {
                    System.out.println(task.toString());
                    writer.write(task.saveState());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    /**
     * Makes a new archive file if it does not exist and save the current list into the archive
     */
    public void saveArchive(TaskList tasks) throws IOException {
        File archiveFile = new File(archiveName);
        File archiveDir = archiveFile.getParentFile();
        if (!archiveDir.exists()) {
            if (archiveDir.mkdir()) {
                System.out.println("Archive created: " + archiveDir);
            } else {
                throw new RuntimeException("Failed to create Archive");
            }
        }
        assert archiveDir.exists() : "Archive not created";
        try {
            if (archiveFile.createNewFile()) {
                System.out.println("Archive File created: " + fileName);
            } else {
                System.out.println("Archive File already exits");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert archiveFile.exists();

        try (FileWriter writer = new FileWriter(archiveName)) {
            tasks.foreach(task -> {
                try {
                    writer.write(task.saveState());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    /**
     * Returns a taskList from the archive
     */
    public List<Task> loadArchive() {
        try (Scanner scanner = new Scanner(new File(archiveName))) {
            List<Task> taskList = new ArrayList<Task>();
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
}
