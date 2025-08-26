public class Event extends Task {
    private String start;
    private String end;
    private String description;
    public Event (String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        this.description = description;
    }

    public Event (String description, String start, String end, boolean done) {
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
}
