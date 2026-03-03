import java.util.ArrayList;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;


public class TaskList {
    private final ArrayList<Task> tasks;

    public static final String BY = " /by ";
    public static final String FROM = " /from ";
    public static final String TO = " /to ";

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

    public Task addTodo(String input) throws OlafException {
        if (input.isEmpty()) {
            throw new OlafException(Ui.ERROR_EMPTY_TASK);
        }
        Todo newTodo = new Todo(input.trim());
        tasks.add(newTodo);
        return newTodo;
    }

    public Task addDeadline(String input) throws OlafException {
        if (input.isEmpty()) {
            throw new OlafException(Ui.ERROR_EMPTY_TASK);
        }
        if (!input.contains(BY)) {
            throw new OlafException(Ui.ERROR_MISSING_BY);
        }
        String[] parts = input.split(BY);
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new OlafException(Ui.ERROR_MISSING_BY);
        }

        Deadline newDeadline = new Deadline(parts[0].trim(), parts[1].trim());
        tasks.add(newDeadline);
        return newDeadline;
    }

    public Task addEvent(String input) throws OlafException {
        if (input.isEmpty()) {
            throw new OlafException(Ui.ERROR_EMPTY_TASK);
        }
        if (!input.contains(FROM)) {
            throw new OlafException(Ui.ERROR_MISSING_FROM_TO);
        }

        if (!input.contains(TO)) {
            throw new OlafException(Ui.ERROR_MISSING_TO);
        }
        // split by " /from " first
        String[] parts = input.split(FROM);
        String description = parts[0].trim();

        // split the second part by " /to "
        String[] timeParts = parts[1].split(TO);

        if (timeParts.length < 2 || timeParts[1].isBlank()) {
            throw new OlafException(Ui.ERROR_MISSING_TO);
        }

        Event newEvent = new Event(description, timeParts[1].trim(), timeParts[1].trim());
        tasks.add(newEvent);
        return newEvent;
    }

    public Task markTask(String input) throws OlafException {
        if (input.isEmpty()) {
            throw new OlafException(Ui.ERROR_NO_INDEX);
        }
        try {
            int index = Integer.parseInt(input) - 1;

            if (index < 0 || index >= tasks.size()) {
                throw new OlafException(Ui.ERROR_INVALID_INDEX);
            }
            Task task = tasks.get(index);
            task.markAsDone();
            return task;
        } catch (NumberFormatException e) {
            throw new OlafException(Ui.ERROR_NOT_A_NUMBER);
        }
    }

    public Task unmarkTask(String input) throws OlafException {
        if (input.isEmpty()) {
            throw new OlafException(Ui.ERROR_NO_INDEX);
        }
        try {
            int index = Integer.parseInt(input) - 1;

            if (index < 0 || index >= tasks.size()) {
                throw new OlafException(Ui.ERROR_INVALID_INDEX);
            }
            Task task = tasks.get(index);
            task.unmarkAsDone();
            return task;
        } catch (NumberFormatException e) {
            throw new OlafException(Ui.ERROR_NOT_A_NUMBER);
        }
    }

    public Task deleteTask(String input) throws OlafException {
        if (input.isEmpty()) {
            throw new OlafException(Ui.ERROR_NO_DELETE_INDEX);
        }
        try {
            int index = Integer.parseInt(input) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new OlafException(Ui.ERROR_INVALID_INDEX);
            }
            return tasks.remove(index);
        } catch (NumberFormatException e) {
            throw new OlafException(Ui.ERROR_NOT_A_NUMBER);
        }
    }
}
