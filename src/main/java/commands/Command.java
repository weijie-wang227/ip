package commands;

import base.Storage;
import base.Ui;
import tasks.TaskList;

public interface Command {
    public void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit();
}
