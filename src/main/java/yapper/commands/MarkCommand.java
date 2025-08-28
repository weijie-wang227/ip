package yapper.commands;

import yapper.Storage;
import yapper.Ui;
import yapper.tasks.TaskList;
import yapper.tasks.Task;

import java.io.IOException;

/**
 * Represent command used to mark the task as done
 */
public class MarkCommand implements Command{
    private int index;

    /**
     * Initialises Mark command
     * @param index of the task in tasklist
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task as done
     * @param tasks
     * @param ui
     * @param storage
     */
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

    @Override
    public boolean equals(Object obj2) {
        if (obj2 instanceof MarkCommand) {
            MarkCommand command = (MarkCommand) obj2;
            return index == command.index;
        } else {
            return false;
        }
    }
}
