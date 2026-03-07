package Commands;

import Tasks.Task;
import Tasks.TaskList;
import core.Ui;
import core.Storage;
import core.OlafException;

public class AddCommand extends Command {
    private Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OlafException {

        tasks.addTask(taskToAdd);
        ui.showAdded(taskToAdd, tasks.getSize());
        storage.save(tasks.getTasks());
    }
}