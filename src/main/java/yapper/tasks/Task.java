package yapper.tasks;

import java.time.LocalDateTime;

/**
 * Parent class for all tasks
 */
public abstract class Task {
    private boolean isDone;
    private final String description;

    /**
     * Create Task with description
     * 
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Create task after loading from file
     * 
     * @param description
     * @param isDone      //specify whether task is done
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Mark task as done
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Mark task as not done
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Check if task is done
     * 
     * @return
     */
    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

    /**
     * Return message for adding task
     * 
     * @param size
     * @return
     */
    public String forDisplay(int size) {
        return "Got it. I've added this task:\n  " +
                toString() +
                "\nNow you have " + size + " in the list";
    }

    /**
     * Return message for deleting task
     * 
     * @param size
     * @return
     */
    public String deleteMessage(int size) {
        return "Noted. I've removed this task:\n  " +
                toString() +
                "\nNow you have " + size + " in the list";
    }

    /**
     * Return a string used to save task
     * 
     * @return
     */
    public abstract String saveState();

    /**
     * Check if task is still open at a given time
     * 
     * @param time
     * @return
     */
    public abstract boolean isCurrent(LocalDateTime time);

    /**
     * Check if task description contains a keyword
     * 
     * @param keyword
     * @return
     */
    public boolean hasKeyword(String keyword) {
        return description.contains(keyword);
    }
}
