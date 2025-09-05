package yapper.commands;

import yapper.Storage;
import yapper.ui.Ui;
import yapper.tasks.TaskList;

/**
 * Represents the exit command by using bye
 */
public class ByeCommand implements Command{
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object obj2) {
        return obj2 instanceof ByeCommand;
    }
}
