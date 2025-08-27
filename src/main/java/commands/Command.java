package commands;

import Yapper.Storage;
import Yapper.Ui;
import tasks.TaskList;

public interface Command {
    public void execute(TaskList tasks, Ui ui, Storage storage);
    public boolean isExit();
}
