package yapper.commands;

import java.io.IOException;

import yapper.Storage;
import yapper.tasks.Task;
import yapper.tasks.TaskList;



/**
 * Represent Command used to delete a task from the Task List
 */
public class DeleteCommand implements Command {
    private int index;

    /**
     * Initialises Delete command
     * @param index of task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task from tasklist
     * @param tasks
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task task = tasks.get(index);
        tasks.remove(index);
        String message = "Ok, I've removed this task\n" + task.deleteMessage(tasks.size());
        try {
            storage.save(tasks);
        } catch (IOException e) {
            return e.getMessage();
        }
        return message;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj2) {
        if (obj2 instanceof DeleteCommand) {
            DeleteCommand command = (DeleteCommand) obj2;
            return index == command.index;
        } else {
            return false;
        }
    }
}
