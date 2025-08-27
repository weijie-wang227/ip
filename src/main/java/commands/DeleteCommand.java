package commands;

import base.Storage;
import base.Ui;
import tasks.TaskList;

public class DeleteCommand implements Command{
    private int index;
    public DeleteCommand(int index) {
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
