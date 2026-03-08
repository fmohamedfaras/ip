package Commands;

import Tasks.Task;
import Tasks.TaskList;
import core.Ui;
import core.Storage;
import core.OlafException;

/**
 * Represents a command to mark a specific task as completed.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs a MarkCommand with the specified target index.
     *
     * @param index The zero-based index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the marking of the task, updates the UI, and saves the modified list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage component.
     * @throws OlafException If the index is out of bounds or saving fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OlafException {
        Task modifiedTask = tasks.markTask(index);
        ui.showMarked(modifiedTask);
        storage.save(tasks.getTasks());
    }
}
