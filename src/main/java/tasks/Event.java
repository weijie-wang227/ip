package tasks;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private String description;
    public Event (String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
        this.description = description;
    }

    public Event (String description, LocalDateTime start, LocalDateTime end, boolean done) {
        super(description, done);
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
        return "E | " + (isDone() ? 1 : 0) + " | " + description + " | " + start + "-" + end + "\n";
    }

    @Override
    public boolean isCurrent(LocalDateTime time) {
        return time.isAfter(start) && time.isBefore(end);
    }
}
