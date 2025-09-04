package yapper.commands;

import yapper.Storage;
import yapper.ui.Ui;
import yapper.tasks.Task;
import yapper.tasks.TaskList;
import yapper.tasks.Todo;

import java.io.IOException;

/**
 * Represent Command Used to create the Todo task
 */
public class TodoCommand implements Command{
    private String desc;

    /**
     * Initialises todo command
     * @param desc
     */
    public TodoCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Adds Todo task to task list
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task todo = new Todo(desc);
        tasks.add(todo);
        ui.display(todo.forDisplay(tasks.size()));
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
        if (obj2 instanceof TodoCommand) {
            TodoCommand command = (TodoCommand) obj2;
            return desc.equals(command.desc);
        } else {
            return false;
        }
    }
}
