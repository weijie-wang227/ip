package tasks;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime deadline;
    private String description;
    public Deadline (String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
        this.description = description;
    }

    public Deadline (String description, LocalDateTime deadline, boolean done) {
        super(description, done);
        this.deadline = deadline;
        this.description = description;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String saveState() {
        return "D | " + (isDone() ? 1 : 0) + " | " + description + " | " + deadline + "\n";
    }

    @Override
    public boolean isCurrent(LocalDateTime time) {
        return time.isBefore(deadline);
    }
}
