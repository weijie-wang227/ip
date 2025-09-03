package yapper.tasks;

import yapper.InvalidInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Represent a list used to store all the tasks
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Create tasklist with already specified tasks
     * 
     * @param tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Create new empty tasklist
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Add task to tasklist
     * 
     * @param task
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Return number of tasks
     * 
     * @return
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Fetch task from tasklist
     * 
     * @param index
     * @return
     * @throws InvalidInputException
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
     * 
     * @param index
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Execute a function on all tasks
     * 
     * @param consumer
     */
    public void foreach(Consumer<Task> consumer) {
        for (Task task : tasks) {
            consumer.accept(task);
        }
    }

    /**
     * Execute a function on all tasks with its index
     * 
     * @param biConsumer
     */
    public void foreachI(BiConsumer<Task, Integer> biConsumer) {
        for (int i = 0; i < tasks.size(); i++) {
            biConsumer.accept(tasks.get(i), i);
        }
    }

    @Override
    public boolean equals(Object obj2) {
        if (obj2 instanceof TaskList) {
            TaskList tasklist2 = (TaskList) obj2;
            System.out.println(get(0).equals(tasklist2.get(0)));
            System.out.println(get(0));
            System.out.println(tasklist2.get(0));
            return tasks.equals(tasklist2.tasks);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return tasks.toString();
    }

    /**
     * Search the tasklist for task containing keyword
     * 
     * @param keyword
     * @return
     */
    public TaskList search(String keyword) {
        return new TaskList(tasks.stream()
                .filter(task -> task.hasKeyword(keyword))
                .collect(Collectors.toList()));
    }
}
