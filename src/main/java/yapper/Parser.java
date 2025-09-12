package yapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * Returns the Command corresponding to the userInput
     * @return Command
     */
    public static Command parse(String input) {
        input = input.trim();
        Matcher markMatcher = Pattern.compile("^mark(?:\\s+(\\d+))?$").matcher(input);
        Matcher unmarkMatcher = Pattern.compile("^unmark(?:\\s+(\\d+))?$").matcher(input);
        Matcher deleteMatcher = Pattern.compile("^delete\\s+(\\d+)$").matcher(input);
        Matcher todoMatcher = Pattern.compile("^todo(?:\\s+(.*))?$").matcher(input);
        Matcher deadlineMatcher = Pattern.compile("^deadline(?:\\s+(.+?))?(?:\\s*/by\\s+(.+))?$").matcher(input);
        Matcher eventMatcher = Pattern.compile("^event(?:\\s+(.+?))?(?:\\s*/from\\s+(.+?))?(?:\\s*/to\\s+(.+))?$")
                .matcher(input);
        Matcher timeMatcher = Pattern.compile("time\\s+(.+)").matcher(input);
        Matcher findMatcher = Pattern.compile("^find(?:\\s+(.*))?$").matcher(input);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");

        if (markMatcher.matches()) {
            assert markMatcher.groupCount() == 1 : "Mark regex should only have 1 group";
            String digit = markMatcher.group(1);
            if (digit == null) {
                throw new InvalidInputException("OOPS!!! The index cannot be empty");
            }
            int index = Integer.parseInt(digit) - 1;
            return new MarkCommand(index);
        } else if (unmarkMatcher.matches()) {
            assert markMatcher.groupCount() == 1 : "Unmark regex should only have 1 group";
            String digit = unmarkMatcher.group(1);
            if (digit == null) {
                throw new InvalidInputException("OOPS!!! The index cannot be empty");
            }
            int index = Integer.parseInt(digit) - 1;
            return new UnmarkCommand(index);
        } else if (deleteMatcher.matches()) {
            String digit = deleteMatcher.group(1);
            if (digit == null) {
                throw new InvalidInputException("OOPS!!! The index cannot be empty");
            }
            int index = Integer.parseInt(digit) - 1;
            return new DeleteCommand(index);
        } else if (todoMatcher.matches()) {
            String desc = todoMatcher.group(1);
            if (desc == null) {
                throw new InvalidInputException("OOPS!!! The description cannot be empty");
            }
            return new TodoCommand(desc);
        } else if (deadlineMatcher.matches()) {
            String desc = deadlineMatcher.group(1);
            String end = deadlineMatcher.group(2);
            if (desc == null || end == null) {
                throw new InvalidInputException("OOPS!!! A deadline task has to have both a description and end date");
            }
            try {
                LocalDateTime dateTime = LocalDateTime.parse(end, formatter);
                return new DeadlineCommand(desc, dateTime);
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("Time has to be formatted as +: M/d/yyyy HHmm");
            }
        } else if (eventMatcher.matches()) {
            String desc = eventMatcher.group(1);
            String start = eventMatcher.group(2);
            String end = eventMatcher.group(3);
            if (desc == null || start == null || end == null) {
                throw new InvalidInputException(
                        "OOPS!!! A event has to have a description, a start date and an end date");
            }
            try {
                LocalDateTime startTime = LocalDateTime.parse(start, formatter);
                LocalDateTime endTime = LocalDateTime.parse(end, formatter);
                return new EventCommand(desc, startTime, endTime);
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("Time has to be formatted as +: M/d/yyyy HHmm");
            }
        } else if (timeMatcher.matches()) {
            String text = timeMatcher.group(1);
            try {
                LocalDateTime time = LocalDateTime.parse(text, formatter);
                return new TimeCommand(time);
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("Time has to be formatted as +: M/d/yyyy HHmm");
            }
        } else if (findMatcher.matches()) {
            String keyword = findMatcher.group(1);
            if (keyword == null) {
                throw new InvalidInputException("OOPS!!! The description cannot be empty");
            }
            return new FindCommand(keyword);
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("archive")) {
            return new ArchiveCommand();
        } else if (input.equals("load")) {
            return new LoadCommand();
        }

        throw new InvalidInputException();
    }
}
