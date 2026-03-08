package Tasks;

import java.util.ArrayList;
import core.OlafException;
import core.Ui;

/**
 * Represents a list of tasks and handles all operations related to it.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList loaded with an existing list of tasks.
     *
     * @param loadedTasks An ArrayList of tasks retrieved from storage.
     */
    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }

    /**
     * Constructs a brand new, empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Retrieves the current number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Retrieves a specific task based on its index.
     *
     * @param index The zero-based index of the target task.
     * @return The Task object at the specified index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Retrieves the entire raw ArrayList of tasks.
     *
     * @return The ArrayList containing all active tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a new task to the end of the list.
     *
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a specific task as completed.
     *
     * @param input The zero-based index of the task to mark.
     * @return The Task object that was successfully marked.
     * @throws OlafException If the provided index is out of bounds.
     */
    public Task markTask(int input) throws OlafException {
            if (input < 0 || input >= tasks.size()) {
                throw new OlafException(Ui.ERROR_INVALID_INDEX);
            }
            Task task = tasks.get(input);
            task.markAsDone();
            return task;
        }

    /**
     * Unmarks a specific task, setting its status back to incomplete.
     *
     * @param input The zero-based index of the task to unmark.
     * @return The Task object that was successfully unmarked.
     * @throws OlafException If the provided index is out of bounds.
     */
    public Task unmarkTask(int input) throws OlafException {
            if (input < 0 || input >= tasks.size()) {
                throw new OlafException(Ui.ERROR_INVALID_INDEX);
            }
            Task task = tasks.get(input);
            task.unmarkAsDone();
            return task;
        }

    /**
     * Deletes a task from the list.
     *
     * @param index The zero-based index of the task to delete.
     * @return The Task object that was successfully removed.
     * @throws OlafException If the provided index is out of bounds.
     */
    public Task deleteTask(int index) throws OlafException {
            if (index < 0 || index >= tasks.size()) {
                throw new OlafException(Ui.ERROR_INVALID_INDEX);
            }
            return tasks.remove(index);
        }

    /**
     * Searches for tasks that contain a specific keyword in their description.
     * The search is case-insensitive.
     *
     * @param keyword The term to search for.
     * @return A new TaskList containing only the tasks that match the keyword.
     */
    public TaskList findTasks(String keyword) {
        TaskList foundTasks = new TaskList();
        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }
}

