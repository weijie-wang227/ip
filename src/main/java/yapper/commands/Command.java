package yapper.commands;

import yapper.Storage;
import yapper.Ui;
import yapper.tasks.TaskList;

/**
 * Base Command Interface for all Commands to implement.
 */
public interface Command {
    /**
     * Executes the command
     * @param tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns if the command is the exit command
     * @return
     */
    public boolean isExit();
}
