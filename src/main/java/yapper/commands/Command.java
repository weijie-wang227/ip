package yapper.commands;

import yapper.Storage;
import yapper.Ui;
import yapper.tasks.TaskList;

public interface Command {
    public void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit();
}
