package yapper.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import yapper.InvalidInputException;

/**
 * Represent a list used to store all the tasks
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Create task list with already specified tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Create new empty task list
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Add task to task list
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Return number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Fetch task from tasklist
     */
    public Task get(int index) throws InvalidInputException {
        if (index >= size() || index < 0) {
            throw new InvalidInputException("Index out of bounds");
        } else {
            return tasks.get(index);
        }
    }

    /**
     * Remove task of specified index
     */
    public void remove(int index) {
        tasks.remove(index);
    }


    @SuppressWarnings("checkstyle:WhitespaceAfter")
    @Override
    public boolean equals(Object obj2) {
        if (obj2 instanceof TaskList) {
            return tasks.equals(((TaskList) obj2).tasks);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return IntStream.range(1, tasks.size() + 1)
                .mapToObj(i -> i + ". " + tasks.get(i - 1).toString()) // use i-1 since list is 0-based
                .collect(Collectors.joining("\n"));
    }

    /**
     * Return String representation of list after filtering using predicate
     */
    public String filterToString(Predicate<Task> predicate) {
        return tasks.stream()
                    .filter(predicate)
                    .map(Task::toString)
                    .collect(Collectors.joining("\n"));
    }

    public void foreach(Consumer<Task> consumer) {
        tasks.forEach(consumer);
    }

    /**
     * Search the task list for task containing keyword
     */
    public TaskList search(String keyword) {
        return new TaskList(tasks.stream()
                .filter(task -> task.hasKeyword(keyword))
                .collect(Collectors.toList()));
    }

    /**
     * Empty current tasklist
     */
    public void empty() {
        tasks.clear();
        assert tasks.isEmpty() : "Tasks not emptied";
    }

    /**
     * Adds new tasks into tasklist
     * @param newTasks
     */
    public void append(List<Task> newTasks) {
        tasks.addAll(newTasks);
    }
}
