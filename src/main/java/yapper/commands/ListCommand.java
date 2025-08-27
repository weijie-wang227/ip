package yapper.commands;

import yapper.Storage;
import yapper.Ui;
import yapper.tasks.TaskList;

public class ListCommand implements Command{

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.foreachI((task, i) -> {ui.display((i + 1) + ", " + task);});
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj2) {
        return obj2 instanceof ListCommand;
    }
}
