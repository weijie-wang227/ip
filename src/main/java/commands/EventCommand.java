package commands;

import base.Storage;
import base.Ui;
import tasks.TaskList;

import java.time.LocalDateTime;

public class EventCommand implements Command{
    private String desc;
    private LocalDateTime start;
    private LocalDateTime end;
    public EventCommand(String desc, LocalDateTime start, LocalDateTime end) {
        this.desc = desc;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
