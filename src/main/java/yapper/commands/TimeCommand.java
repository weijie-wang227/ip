package yapper.commands;

import yapper.Storage;
import yapper.Ui;
import yapper.tasks.TaskList;

import java.time.LocalDateTime;

/**
 * Represent command used to list all the task that are still available for a specified time
 */
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

    @Override
    public boolean equals(Object obj2) {
        if (obj2 instanceof TimeCommand) {
            TimeCommand command = (TimeCommand) obj2;
            return time.equals(command.time);
        } else {
            return false;
        }
    }
}
