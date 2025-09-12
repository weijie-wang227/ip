package yapper.commands;

import java.io.IOException;

import yapper.Storage;
import yapper.tasks.TaskList;

/**
 * Represents the command to save the current tasklist into archive
 */
public class ArchiveCommand implements Command {


    @Override
    public String execute(TaskList tasks, Storage storage) {
        try {
            storage.saveArchive(tasks);
        } catch (IOException e) {
            return "Failed to archive tasklist";
        }
        tasks.empty();
        try {
            storage.save(tasks);
        } catch (IOException e) {
            return "Failed to delete current memory";
        }
        return "TaskList Archived";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
