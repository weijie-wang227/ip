package yapper.commands;

import yapper.Storage;
import yapper.ui.Ui;
import yapper.tasks.TaskList;

/**
 * Represents the exit command by using bye
 */
public class ByeCommand implements Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
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
