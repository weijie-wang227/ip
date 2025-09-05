package yapper.commands;

import yapper.Storage;
import yapper.tasks.Deadline;
import yapper.tasks.Task;
import yapper.tasks.TaskList;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Represent the command used to create the Deadline task
 */
public class DeadlineCommand implements Command{
    private String desc;
    private LocalDateTime time;

    /**
     * Initialises the Deadline Command
     * @param desc
     * @param time deadline of task
     */
    public DeadlineCommand(String desc, LocalDateTime time) {
        this.desc = desc;
        this.time = time;
    }

    /**
     * Creates Deadline and add to tasklist
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task deadline = new Deadline(desc, time);
        tasks.add(deadline);
        String response = deadline.forDisplay(tasks.size());
        try {
            storage.save(tasks);
        } catch (IOException e) {
            return e.getMessage();
        }
        return response;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj2) {
        if (obj2 instanceof DeadlineCommand) {
            DeadlineCommand command = (DeadlineCommand) obj2;
            return desc.equals(command.desc) && time.equals(command.time);
        } else {
            return false;
        }
    }
}
