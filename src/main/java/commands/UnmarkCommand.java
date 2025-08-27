package commands;

import base.Storage;
import base.Ui;
import tasks.TaskList;

public class UnmarkCommand implements Command{
    private int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
