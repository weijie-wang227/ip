package yapper.commands;

import yapper.Storage;
import yapper.ui.Ui;
import yapper.tasks.TaskList;

public class FindCommand implements Command{
    private String keyword;
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Here are the matching tasks in your list:\n" + tasks.search(keyword);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
