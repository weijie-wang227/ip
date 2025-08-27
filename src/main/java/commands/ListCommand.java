package commands;

import Yapper.Storage;
import Yapper.Ui;
import tasks.TaskList;

public class ListCommand implements Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.foreachI((task, i) -> {ui.display((i + 1) + ", " + task);});
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
