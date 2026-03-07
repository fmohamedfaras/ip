package Commands;

import Tasks.TaskList;
import core.Ui;
import core.Storage;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getTasks());
    }
}
