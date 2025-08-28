package yapper.commands;

import yapper.Storage;
import yapper.Ui;
import yapper.tasks.TaskList;

public class FindCommand implements Command{
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.display("Here are the matching tasks in your list:");
        ui.displayList(tasks.search(keyword));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
