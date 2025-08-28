package yapper.commands;

import yapper.Storage;
import yapper.Ui;
import yapper.tasks.TaskList;

/**
 * Represent the command used to list all the tasks in Task List
 */
public class ListCommand implements Command{

    /**
     * List out all the tasks in tasklist
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.foreachI((task, i) -> {ui.display((i + 1) + ", " + task);});
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
