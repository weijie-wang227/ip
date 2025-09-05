package yapper.commands;

import yapper.Storage;
import yapper.tasks.TaskList;

/**
 * Represent the command used to list all the tasks in Task List
 */
public class ListCommand implements Command{

    /**
     * List out all the tasks in tasklist
     * @param tasks
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj2) {
        return obj2 instanceof ListCommand;
    }
}
