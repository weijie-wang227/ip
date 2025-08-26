public class Deadline extends Task {
    private String deadline;
    private String description;
    public Deadline (String description, String deadline) {
        super(description);
        this.deadline = deadline;
        this.description = description;
    }

    public Deadline (String description, String deadline, boolean done) {
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
}
