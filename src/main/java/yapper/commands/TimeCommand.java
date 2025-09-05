package yapper.commands;

import yapper.Storage;
import yapper.tasks.TaskList;

import java.time.LocalDateTime;

/**
 * Represent command used to list all the task that are still available for a specified time
 */
public class TimeCommand implements Command{
    private LocalDateTime time;

    /**
     * Initialises the Time command
     * @param time
     */
    public TimeCommand(LocalDateTime time) {
        this.time = time;
    }

    /**
     * Displays the tasks that are still active during the time
     * @param tasks
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "These tasks are still current:\n" + tasks.filterToString(task -> task.isCurrent(time));
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
