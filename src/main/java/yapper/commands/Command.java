package yapper.commands;

import yapper.Storage;
import yapper.Ui;
import yapper.tasks.TaskList;

/**
 * Base Command Interface for all Commands to implement.
 */
public interface Command {
    public void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit();
}
