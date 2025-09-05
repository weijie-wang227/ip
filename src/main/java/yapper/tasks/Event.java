package yapper.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represent a task in the format: event desc/from time/to start
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private String description;

    /**
     * Create an event task with a start and end time
     * @param description
     * @param start
     * @param end
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
        this.description = description;
    }

    /**
     * Create an event task after loading from file with option to set it to
     * done
     * @param description
     * @param start
     * @param end
     * @param isDone
     */
    public Event(String description, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
        this.description = description;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    @Override
    public String saveState() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");

        return "E | " + (isDone() ? 1 : 0) + " | " + description + " | " + start.format(formatter) + "-"
                + end.format(formatter) + "\n";
    }

    @Override
    public boolean isCurrent(LocalDateTime time) {
        return time.isAfter(start) && time.isBefore(end);
    }
}
