package yapper.tasks;

import java.time.LocalDateTime;

/**
 * Represent a task in the format: todo desc
 */
public class Todo extends Task {
    private String description;

    /**
     * Create a todo task with a description
     * @param description
     */
    public Todo(String description) {
        super(description);
        this.description = description;
    }

    /**
     * Create a todo task after loading from save file
     * @param description
     * @param isDone      //Specify if task is done
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
        this.description = description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveState() {
        return "T | " + (isDone() ? 1 : 0) + " | " + description + "\n";
    }

    @Override
    public boolean isCurrent(LocalDateTime time) {
        return true;
    }
}
