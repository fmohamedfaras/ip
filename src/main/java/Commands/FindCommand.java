package Commands;

import Tasks.TaskList;
import core.Ui;
import core.Storage;

/**
 * Represents a command to search for tasks that contain a specific keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified search keyword.
     *
     * @param keyword The word or phrase to search for within the task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the search operation by matching the keyword against tasks and displaying the results.
     *
     * @param tasks   The list of tasks to search through.
     * @param ui      The user interface to display the found tasks.
     * @param storage The storage component.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList foundTasks = tasks.findTasks(keyword);
        ui.showFoundTasks(foundTasks);
    }
}
