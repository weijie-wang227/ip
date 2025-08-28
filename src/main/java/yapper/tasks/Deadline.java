package yapper.tasks;

import yapper.commands.DeadlineCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    private String description;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
        this.description = description;
    }

    public Deadline(String description, LocalDateTime deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
        this.description = description;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String saveState() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
        return "D | " + (isDone() ? 1 : 0) + " | " + description + " | " + deadline.format(formatter) + "\n";
    }

    @Override
    public boolean isCurrent(LocalDateTime time) {
        return false;
    }

    @Override
    public boolean equals(Object obj2) {
        if (obj2 instanceof Deadline) {
            Deadline command = (Deadline) obj2;
            return description.equals(command.description) && deadline.equals(command.deadline);
        } else {
            return false;
        }
    }
}
