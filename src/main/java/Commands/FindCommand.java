package Commands;

import Tasks.TaskList;
import core.Ui;
import core.Storage;
import Tasks.Task;
import Tasks.TaskList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList foundTasks = tasks.findTasks(keyword);
        ui.showFoundTasks(foundTasks);
    }
}
