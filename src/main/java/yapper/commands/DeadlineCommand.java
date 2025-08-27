package yapper.commands;

import yapper.Storage;
import yapper.Ui;
import yapper.tasks.Deadline;
import yapper.tasks.Task;
import yapper.tasks.TaskList;

import java.io.IOException;
import java.time.LocalDateTime;

public class DeadlineCommand implements Command{
    private String desc;
    private LocalDateTime time;
    public DeadlineCommand(String desc, LocalDateTime time) {
        this.desc = desc;
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deadline = new Deadline(desc, time);
        tasks.add(deadline);
        ui.display(deadline.forDisplay(tasks.size()));
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
