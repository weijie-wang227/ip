package commands;

import Yapper.Storage;
import Yapper.Ui;
import tasks.TaskList;
import tasks.Task;

import java.io.IOException;

public class MarkCommand implements Command{
    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index);
        task.mark();
        ui.display("Ok, I've marked this task as done");
        ui.display(task.toString());
        try {
            storage.save(tasks);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
