package yapper.tasks;

import yapper.InvalidInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Represent a list used to store all the tasks
 */
public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) throws InvalidInputException {
        if (index >= size() || index < 0) {
            throw new InvalidInputException("Index out of bounds");
        } else {
            return tasks.get(index);
        }
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public void foreach(Consumer<Task> consumer) {
        for (Task task: tasks) {
            consumer.accept(task);
        }
    }

    public void foreachI(BiConsumer<Task, Integer> biConsumer) {
        for (int i = 0; i < tasks.size(); i ++) {
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
        } else {return false;}
    }

    @Override
    public String toString() {
        return tasks.toString();
    }
}
