package yapper.tasks;

import java.time.LocalDateTime;

public abstract class Task {
    private boolean isDone;
    private final String description;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

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

    public String forDisplay(int size) {
        return "Got it. I've added this task:\n  " +
                toString() +
                "\nNow you have " + size + " in the list";
    }

    public String deleteMessage(int size) {
        return "Noted. I've removed this task:\n  " +
                toString() +
                "\nNow you have " + size + " in the list";
    }

    public abstract String saveState();

    public abstract boolean isCurrent(LocalDateTime time);
}
