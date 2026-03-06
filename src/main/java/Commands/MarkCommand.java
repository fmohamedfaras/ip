package Commands;

import Tasks.Task;
import Tasks.TaskList;
import core.Ui;
import core.Storage;
import core.OlafException;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OlafException {
        Task modifiedTask = tasks.markTask(index);
        ui.showMarked(modifiedTask);
        storage.save(tasks.getTasks());
    }
}
