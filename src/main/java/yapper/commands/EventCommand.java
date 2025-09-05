package yapper.commands;

import yapper.Storage;
import yapper.tasks.Event;
import yapper.tasks.Task;
import yapper.tasks.TaskList;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Represent Command used to create Event Task
 */
public class EventCommand implements Command{
    private String desc;
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Initialises the command
     * @param desc
     * @param start
     * @param end
     */
    public EventCommand(String desc, LocalDateTime start, LocalDateTime end) {
        this.desc = desc;
        this.start = start;
        this.end = end;
    }

    /**
     * Adds a Event task to tasklist
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task event = new Event(desc, start, end);
        tasks.add(event);
        String message = event.forDisplay(tasks.size());
        try {
            storage.save(tasks);
        } catch (IOException e) {
            return e.getMessage();
        }
        return message;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj2) {
        if (obj2 instanceof EventCommand) {
            EventCommand command = (EventCommand) obj2;
            return desc.equals(command.desc) && start.equals(command.start) && end.equals(command.end);
        } else {
            return false;
        }
    }
}
