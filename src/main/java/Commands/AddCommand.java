package Commands;

import Tasks.Task;
import Tasks.TaskList;
import core.Ui;
import core.Storage;
import core.OlafException;

/**
 * Represents a command to add a new task (Todo, Deadline, or Event) to the task list.
 */
public class AddCommand extends Command {
    private Task taskToAdd;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param taskToAdd The Task object to be added to the list.
     */
    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    /**
     * Executes the addition of the task, updates the UI, and saves the new list to storage.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage component.
     * @throws OlafException If adding task or saving fails
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OlafException {
        tasks.addTask(taskToAdd);
        ui.showAdded(taskToAdd, tasks.getSize());
        storage.save(tasks.getTasks());
    }
}