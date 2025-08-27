package commands;

import Yapper.Storage;
import Yapper.Ui;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;

import java.io.IOException;
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
        Task event = new Event(desc, start, end);
        tasks.add(event);
        ui.display(event.forDisplay(tasks.size()));
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
