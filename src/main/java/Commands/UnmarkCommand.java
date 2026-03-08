package Commands;

import Tasks.Task;
import Tasks.TaskList;
import core.Ui;
import core.Storage;
import core.OlafException;

/**
 * Represents a command to unmark a specific task, setting it as not completed.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand with the specified target index.
     *
     * @param index The zero-based index of the task to be unmarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmarking of the task, updates the UI, and saves the modified list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage component.
     * @throws OlafException If the index is out of bounds or saving fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OlafException {
        Task modifiedTask = tasks.unmarkTask(index);
        ui.showUnmarked(modifiedTask);
        storage.save(tasks.getTasks());
    }
}
