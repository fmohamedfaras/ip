import java.util.Scanner;
import java.util.ArrayList;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;


public class Olaf {

    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";

    private static ArrayList<Task> tasks = new ArrayList<>();

    private static Ui ui = new Ui();

    private static Storage storage = new Storage("./data/olaf.txt");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        tasks = storage.load();

        ui.showWelcome();

        boolean isActive = true;

        while (isActive) {
           String input = scanner.nextLine();
            ui.showLine();

            String[] parts = input.split(" ", 2); // Split into command + rest of string
            String command = parts[0];
            String taskName = (parts.length > 1) ? parts[1] : "";

            try {
                switch (command) {
                case COMMAND_BYE:
                    ui.showBye();
                    isActive = false;
                    break;

                case COMMAND_LIST:
                    ui.showTaskList(tasks);
                    break;

                case COMMAND_MARK:
                    markTask(taskName);
                    break;

                case COMMAND_UNMARK:
                    unmarkTask(taskName);
                    break;

                case COMMAND_TODO:
                    addTodo(taskName);
                    break;

                case COMMAND_DEADLINE:
                    addDeadline(taskName);
                    break;

                case COMMAND_EVENT:
                    addEvent(taskName);
                    break;

                case COMMAND_DELETE:
                    deleteTask(taskName);
                    break;

                default:
                    throw new OlafException(Ui.ERROR_UNKNOWN_COMMAND);
                }
            } catch (OlafException e) {
                ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }

    public static void addTodo(String input) throws OlafException {
        if (input.isEmpty()) {
            throw new OlafException(Ui.ERROR_EMPTY_TASK);
        }
        Todo newTodo = new Todo(input);
        tasks.add(newTodo);
        ui.showAdded(newTodo, tasks.size());

        storage.save(tasks);
    }

    private static void addDeadline(String input) throws OlafException {
        if (input.isEmpty()) {
            throw new OlafException(Ui.ERROR_EMPTY_TASK);
        }
        if (!input.contains(" /by ")) {
            throw new OlafException(Ui.ERROR_MISSING_BY);
        }
        String[] parts = input.split(" /by ");
        Deadline newDeadline = new Deadline(parts[0], parts[1]);
        tasks.add(newDeadline);
        ui.showAdded(newDeadline, tasks.size());

        storage.save(tasks);

    }

    private static void addEvent(String input) throws OlafException {

        if (input.isEmpty()) {
            throw new OlafException(Ui.ERROR_EMPTY_TASK);
        }
        if (!input.contains(" /from ")) {
            throw new OlafException(Ui.ERROR_MISSING_FROM_TO);
        }

        if (!input.contains(" /to ")) {
            throw new OlafException(Ui.ERROR_MISSING_TO);
        }
        // split by " /from " first
        String[] parts = input.split(" /from ");
        String description = parts[0];

        // split the second part by " /to "
        String[] timeParts = parts[1].split(" /to ");
        String from = timeParts[0];
        String to = timeParts[1];

        if (to.isBlank()) {
            throw new OlafException(Ui.ERROR_MISSING_TO);
        }

        Event newEvent = new Event(description, timeParts[0], timeParts[1]);
        tasks.add(newEvent);
        ui.showAdded(newEvent, tasks.size());

        storage.save(tasks);

    }

    private static void markTask(String input) throws OlafException {
        if (input.isEmpty()) {
            throw new OlafException(Ui.ERROR_NO_INDEX);
        }
        try {

            int index = Integer.parseInt(input) - 1;

            if (index < 0 || index >= tasks.size()) {
                throw new OlafException(Ui.ERROR_INVALID_INDEX);
            }
            tasks.get(index).markAsDone();
            ui.showMarked(tasks.get(index));

            storage.save(tasks);

        } catch (NumberFormatException e) {
            throw new OlafException(Ui.ERROR_NOT_A_NUMBER);
        }
    }

    private static void unmarkTask(String input) throws OlafException {
        if (input.isEmpty()) {
            throw new OlafException(Ui.ERROR_NO_INDEX);
        }
        try {

            int index = Integer.parseInt(input) - 1;

            if (index < 0 || index >= tasks.size()) {
                throw new OlafException(Ui.ERROR_INVALID_INDEX);
            }
            tasks.get(index).unmarkAsDone();
            ui.showUnmarked(tasks.get(index));

            storage.save(tasks);
        } catch (NumberFormatException e) {
            throw new OlafException(Ui.ERROR_NOT_A_NUMBER);
        }
    }

    private static void deleteTask(String input) throws OlafException {
        if (input.isEmpty()) {
            throw new OlafException(Ui.ERROR_NO_DELETE_INDEX);
        }
        try {
            int index = Integer.parseInt(input) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new OlafException(Ui.ERROR_INVALID_INDEX);
            }
            Task removedTask = tasks.remove(index);
            ui.showDeleted(removedTask, tasks.size());

            storage.save(tasks);
        } catch (NumberFormatException e) {
            throw new OlafException(Ui.ERROR_NOT_A_NUMBER);
        }
    }

}


