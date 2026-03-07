package Tasks;

import java.util.ArrayList;
import core.OlafException;
import core.Ui;


public class TaskList {
    private final ArrayList<Task> tasks;

    // Constructor for when we load from the hard drive
    public TaskList(ArrayList<Task> loadedTasks) {
        this.tasks = loadedTasks;
    }

    // Constructor for a brand new, empty list
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task markTask(int input) throws OlafException {
            if (input < 0 || input >= tasks.size()) {
                throw new OlafException(Ui.ERROR_INVALID_INDEX);
            }
            Task task = tasks.get(input);
            task.markAsDone();
            return task;
        }

    public Task unmarkTask(int input) throws OlafException {
            if (input < 0 || input >= tasks.size()) {
                throw new OlafException(Ui.ERROR_INVALID_INDEX);
            }
            Task task = tasks.get(input);
            task.unmarkAsDone();
            return task;
        }

    public Task deleteTask(int index) throws OlafException {
            if (index < 0 || index >= tasks.size()) {
                throw new OlafException(Ui.ERROR_INVALID_INDEX);
            }
            return tasks.remove(index);
        }
}
