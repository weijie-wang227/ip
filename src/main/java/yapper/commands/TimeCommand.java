package yapper.commands;

import yapper.Storage;
import yapper.Ui;
import yapper.tasks.TaskList;

import java.time.LocalDateTime;

public class TimeCommand implements Command{
    private LocalDateTime time;
    public TimeCommand(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.display("These tasks are still current:");
        tasks.foreach(task -> {if(task.isCurrent(time)) {ui.display(task.toString());}});
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
