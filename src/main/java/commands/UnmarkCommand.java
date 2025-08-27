package commands;

import base.Storage;
import base.Ui;
import tasks.Task;
import tasks.TaskList;

import java.io.IOException;

public class UnmarkCommand implements Command{
    private int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index);
        task.unmark();
        ui.display("Ok, I've marked this task as not done yet");
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
