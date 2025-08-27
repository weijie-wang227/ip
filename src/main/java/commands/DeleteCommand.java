package commands;

import base.InvalidInputException;
import base.Storage;
import base.Ui;
import tasks.Task;
import tasks.TaskList;

import java.io.IOException;

public class DeleteCommand implements Command{
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index);
        tasks.remove(index);
        ui.display("Ok, I've removed this task");
        ui.display(task.deleteMessage(tasks.size()));
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
