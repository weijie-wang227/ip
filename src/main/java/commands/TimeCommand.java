package commands;

import base.Storage;
import base.Ui;
import tasks.TaskList;

import java.time.LocalDateTime;

public class TimeCommand implements Command{
    private LocalDateTime time;
    public TimeCommand(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
