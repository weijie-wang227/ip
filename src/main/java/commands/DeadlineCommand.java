package commands;

import base.Storage;
import base.Ui;
import tasks.TaskList;

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

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
