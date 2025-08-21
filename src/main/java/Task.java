public abstract class Task
{
    private boolean done;
    private final String description;
    public Task(String description) {
        this.description = description;
        this.done = false;
    }
    public void mark() {
        done = true;
    }

    public void unmark() {
        done = false;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public String toString() {
        if (done) {
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
}
