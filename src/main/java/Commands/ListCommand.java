package Commands;

import Tasks.TaskList;
import core.Ui;
import core.Storage;

/**
 * Represents a command to display all tasks currently in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by requesting the UI to print all active tasks.
     *
     * @param tasks   The list of tasks to be displayed.
     * @param ui      The user interface.
     * @param storage The storage component.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}
