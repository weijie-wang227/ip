package yapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import yapper.commands.ArchiveCommand;
import yapper.commands.ByeCommand;
import yapper.commands.Command;
import yapper.commands.DeadlineCommand;
import yapper.commands.DeleteCommand;
import yapper.commands.EventCommand;
import yapper.commands.FindCommand;
import yapper.commands.ListCommand;
import yapper.commands.LoadCommand;
import yapper.commands.MarkCommand;
import yapper.commands.TimeCommand;
import yapper.commands.TodoCommand;
import yapper.commands.UnmarkCommand;

/**
 * Represent Parser used to parse user input to create commands
 */
public class Parser {

    private static final DateTimeFormatter[] FORMATTERS = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("M/d/yyyy HHmm"),
            DateTimeFormatter.ofPattern("M/d/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
    };

    /**
     * Parses the input from the input and returns the corresponding command
     * @param input //user input as String
     */
    public static Command parse(String input) {
        input = input.trim();

        if (matches("^mark(?:\\s+(\\d+))?$", input)) {
            return parseMark(input);
        } else if (matches("^unmark(?:\\s+(\\d+))?$", input)) {
            return parseUnmark(input);
        } else if (matches("^delete\\s+(\\d+)$", input)) {
            return parseDelete(input);
        } else if (matches("^todo(?:\\s+(.*))?$", input)) {
            return parseTodo(input);
        } else if (matches("^deadline(?:\\s+(.+?))?(?:\\s*/by\\s+(.+))?$", input)) {
            return parseDeadline(input);
        } else if (matches("^event(?:\\s+(.+?))?(?:\\s*/from\\s+(.+?))?(?:\\s*/to\\s+(.+))?$", input)) {
            return parseEvent(input);
        } else if (matches("^time\\s+(.+)$", input)) {
            return parseTime(input);
        } else if (matches("^find(?:\\s+(.*))?$", input)) {
            return parseFind(input);
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("archive")) {
            return new ArchiveCommand();
        } else if (input.equals("load")) {
            return new LoadCommand();
        } else {
            throw new InvalidInputException();
        }
    }

    // -----------------------
    // Helper methods
    // -----------------------

    private static boolean matches(String regex, String input) {
        return Pattern.compile(regex).matcher(input).matches();
    }

    private static Command parseMark(String input) {
        Matcher matcher = Pattern.compile("^mark(?:\\s+(\\d+))?$").matcher(input);
        matcher.matches();
        String digit = matcher.group(1);
        if (digit == null) {
            throw new InvalidInputException("OOPS!!! The index cannot be empty");
        }
        return new MarkCommand(Integer.parseInt(digit) - 1);
    }

    private static Command parseUnmark(String input) {
        Matcher matcher = Pattern.compile("^unmark(?:\\s+(\\d+))?$").matcher(input);
        matcher.matches();
        String digit = matcher.group(1);
        if (digit == null) {
            throw new InvalidInputException("OOPS!!! The index cannot be empty");
        }
        return new UnmarkCommand(Integer.parseInt(digit) - 1);
    }

    private static Command parseDelete(String input) {
        Matcher matcher = Pattern.compile("^delete\\s+(\\d+)$").matcher(input);
        matcher.matches();
        String digit = matcher.group(1);
        if (digit == null) {
            throw new InvalidInputException("OOPS!!! The index cannot be empty");
        }
        return new DeleteCommand(Integer.parseInt(digit) - 1);
    }

    private static Command parseTodo(String input) {
        Matcher matcher = Pattern.compile("^todo(?:\\s+(.*))?$").matcher(input);
        matcher.matches();
        String desc = matcher.group(1);
        if (desc == null) {
            throw new InvalidInputException("OOPS!!! The description cannot be empty");
        }
        return new TodoCommand(desc);
    }

    private static Command parseDeadline(String input) {
        Matcher matcher = Pattern.compile("^deadline(?:\\s+(.+?))?(?:\\s*/by\\s+(.+))?$").matcher(input);
        matcher.matches();
        String desc = matcher.group(1);
        String end = matcher.group(2);
        if (desc == null || end == null) {
            throw new InvalidInputException("OOPS!!! A deadline task has to have both a description and end date");
        }
        return new DeadlineCommand(desc, parseDate(end));
    }

    private static Command parseEvent(String input) {
        Matcher matcher = Pattern.compile("^event(?:\\s+(.+?))?(?:\\s*/from\\s+(.+?))?(?:\\s*/to\\s+(.+))?$")
                .matcher(input);
        matcher.matches();
        String desc = matcher.group(1);
        String start = matcher.group(2);
        String end = matcher.group(3);
        if (desc == null || start == null || end == null) {
            throw new InvalidInputException("OOPS!!! An event has to have a description, start date and end date");
        }
        return new EventCommand(desc, parseDate(start), parseDate(end));
    }

    private static Command parseTime(String input) {
        Matcher matcher = Pattern.compile("^time\\s+(.+)$").matcher(input);
        matcher.matches();
        String text = matcher.group(1);
        return new TimeCommand(parseDate(text));
    }

    private static Command parseFind(String input) {
        Matcher matcher = Pattern.compile("^find(?:\\s+(.*))?$").matcher(input);
        matcher.matches();
        String keyword = matcher.group(1);
        if (keyword == null) {
            throw new InvalidInputException("OOPS!!! The description cannot be empty");
        }
        return new FindCommand(keyword);
    }

    private static LocalDateTime parseDate(String text) {
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDateTime.parse(text, formatter);
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new InvalidInputException("Time has to be formatted as one of: "
                + Arrays.stream(FORMATTERS)
                        .map(DateTimeFormatter::toString)
                        .collect(Collectors.joining(", ")));
    }
}
