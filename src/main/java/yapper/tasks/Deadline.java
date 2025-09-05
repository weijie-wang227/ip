package yapper.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represent a task in the format: deadline desc/by time
 */
public class Deadline extends Task {
    private LocalDateTime deadline;
    private String description;

    /**
     * Create a deadline task with description and deadline
     * @param description
     * @param deadline
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
        this.description = description;
    }

    /**
     * Create deadline task after loading from save file
     * @param description
     * @param deadline
     * @param isDone      //Specify whether task is done
     */
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
