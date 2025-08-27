package base;
import commands.*;
import tasks.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static Command parse(String input) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");

        if (input.matches("^mark(?:\\s+(\\d+))?$")){
            //mark
            Matcher matcher = Pattern.compile("^mark(?:\\s+(\\d+))?$").matcher(input);
            String digit = matcher.group(1);
            if (digit == null) {
                throw new InvalidInputException("OOPS!!! The index cannot be empty");
            }
            int index = Integer.parseInt(digit) - 1;
            return new MarkCommand(index);
        } else if (input.matches("^unmark\\s+(\\d+)$")) {
            Matcher matcher = Pattern.compile("^unmark(?:\\s+(\\d+))?$").matcher(input);
            String digit = matcher.group(1);
            if (digit == null) {
                throw new InvalidInputException("OOPS!!! The index cannot be empty");
            }
            int index = Integer.parseInt(digit) - 1;
            return new UnmarkCommand(index);
        } else if (input.matches("^delete\\s+(\\d+)$")) {
            Matcher matcher = Pattern.compile("^delete\\s+(\\d+)$").matcher(input);
            String digit = matcher.group(1);
            if (digit == null) {
                throw new InvalidInputException("OOPS!!! The index cannot be empty");
            }
            int index = Integer.parseInt(digit) - 1;
            return new DeleteCommand(index);
        } else if (input.matches("^todo(?:\\s+(.*))?$")) {
            Matcher matcher = Pattern.compile("^todo(?:\\s+(.*))?$").matcher(input);
            String desc = matcher.group(1);
            if (desc == null) {
                throw new InvalidInputException("OOPS!!! The index cannot be empty");
            }
            return new TodoCommand(desc);
        } else if (input.matches("^deadline(?:\\s+(.+?))?(?:\\s*/by\\s+(.+))?$")) {
            Matcher matcher = Pattern.compile("^deadline(?:\\s+(.+?))?(?:\\s*/by\\s+(.+))?$").matcher(input);
            String desc = matcher.group(1);
            String end = matcher.group(2);
            if (desc == null|| end == null) {
                throw new InvalidInputException("OOPS!!! A deadline task has to have both a description and end date");
            }
            try {
                LocalDateTime dateTime = LocalDateTime.parse(end, formatter);
                return new DeadlineCommand(desc, dateTime);
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("Invalid time format");
            }
        } else if (input.matches("^event(?:\\s+(.+?))?(?:\\s*/from\\s+(.+?))?(?:\\s*/to\\s+(.+))?$")) {
            Matcher matcher = Pattern.compile("^event(?:\\s+(.+?))?(?:\\s*/from\\s+(.+?))?(?:\\s*/to\\s+(.+))?$").matcher(input);
            String desc = matcher.group(1);
            String start = matcher.group(2);
            String end = matcher.group(3);
            if (desc == null || start == null || end == null) {
                throw new InvalidInputException("OOPS!!! A event has to have a description, a start date and an end date");
            }
            try {
                LocalDateTime startTime = LocalDateTime.parse(start, formatter);
                LocalDateTime endTime = LocalDateTime.parse(end, formatter);
                return new EventCommand(desc, startTime, endTime);
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("Invalid time format");
            }
        } else if (input.matches("time\\s+(.+)")) {
            Matcher matcher = Pattern.compile("time\\s+(.+)").matcher(input);
            String text = matcher.group(1);
            try {
                LocalDateTime time = LocalDateTime.parse(text, formatter);
                return new TimeCommand(time);
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("Invalid time format");
            }
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("bye")) {
            return new ByeCommand();
        }

        throw new InvalidInputException();
    }
}
