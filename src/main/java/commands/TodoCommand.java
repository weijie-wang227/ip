package commands;

import base.Storage;
import base.Ui;
import tasks.TaskList;

public class TodoCommand implements Command{
    private String desc;
    public TodoCommand(String desc) {
        this.desc = desc;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
