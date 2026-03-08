package Commands;

import Tasks.Task;
import Tasks.TaskList;
import core.Ui;
import core.Storage;
import core.OlafException;

/**
 * Represents a command to delete a task from the task list based on its index.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the specified index.
     *
     * @param index The zero-based index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the deletion of the task, updates the UI, and saves the modified list to storage.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage component.
     * @throws OlafException If the index is out of bounds or saving fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OlafException {
        Task removedTask = tasks.deleteTask(index);
        ui.showDeleted(removedTask, tasks.getSize());
        storage.save(tasks.getTasks());
    }
}
