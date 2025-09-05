package yapper.commands;

import yapper.Storage;
import yapper.tasks.Task;
import yapper.tasks.TaskList;

import java.io.IOException;

/**
 * Represent Command used to unmark a task as done
 */
public class UnmarkCommand implements Command{
    private int index;

    /**
     * Initialise Unmark command
     * @param index
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Unmark the task specified by index in tasklist
     * @param tasks
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task task = tasks.get(index);
        task.unmark();
        String response = "Ok, I've marked this task as not done yet";
        try {
            storage.save(tasks);
        } catch (IOException e) {
            return e.getMessage();
        }
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj2) {
        if (obj2 instanceof UnmarkCommand) {
            UnmarkCommand command = (UnmarkCommand) obj2;
            return index == command.index;
        } else {
            return false;
        }
    }
}
