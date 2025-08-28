package yapper.commands;

import yapper.Storage;
import yapper.Ui;
import yapper.tasks.Task;
import yapper.tasks.TaskList;

import java.io.IOException;

/**
 * Represent Command used to delete a task from the Task List
 */
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

    @Override
    public boolean equals(Object obj2) {
        if (obj2 instanceof DeleteCommand) {
            DeleteCommand command = (DeleteCommand) obj2;
            return index == command.index;
        } else {
            return false;
        }
    }
}
