package Commands;

import Tasks.Task;
import Tasks.TaskList;
import core.Ui;
import core.Storage;
import core.OlafException;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OlafException {
        Task removedTask = tasks.deleteTask(index);
        ui.showDeleted(removedTask, tasks.getSize());
        storage.save(tasks.getTasks());
    }
}
