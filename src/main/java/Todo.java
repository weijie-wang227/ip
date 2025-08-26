public class Todo extends Task {
    private String description;

    public Todo(String description) {
        super(description);
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

}
