package yapper.commands;

import yapper.Storage;
import yapper.Ui;
import yapper.tasks.Task;
import yapper.tasks.TaskList;
import yapper.tasks.Todo;

import java.io.IOException;

public class TodoCommand implements Command{
    private String desc;
    public TodoCommand(String desc) {
        this.desc = desc;
    }

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
}
