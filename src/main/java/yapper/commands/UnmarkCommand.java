package yapper.commands;

import yapper.Storage;
import yapper.Ui;
import yapper.tasks.Task;
import yapper.tasks.TaskList;

import java.io.IOException;

/**
 * Represent Command used to unmark a task as done
 */
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

    @Override
    public boolean equals(Object obj2) {
        if (obj2 instanceof UnmarkCommand) {
            UnmarkCommand command = (UnmarkCommand) obj2;
            return index == command.index;
        } else {
            return false;
        }
    }
}
