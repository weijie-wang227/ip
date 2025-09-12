package yapper.commands;

import java.io.IOException;

import yapper.Storage;
import yapper.tasks.TaskList;

/**
 * Represents command to load from the archive
 */
public class LoadCommand implements Command {

    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.append(storage.loadArchive());
        try {
            storage.save(tasks);
        } catch (IOException e) {
            return "Failed to load from Archive";
        }
        return "Tasks loaded successfully";
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
