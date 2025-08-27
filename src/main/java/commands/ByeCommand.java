package commands;

import base.Storage;
import base.Ui;
import tasks.TaskList;

public class ByeCommand implements Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
