import java.util.Scanner;

import Tasks.Task;
import Tasks.TaskList;


public class Olaf {

    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";

    private static TaskList taskList;
    private static Ui ui = new Ui();
    private static Storage storage = new Storage("./data/olaf.txt");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        taskList = new TaskList(storage.load());

        ui.showWelcome();
        boolean isActive = true;

        while (isActive) {
           String input = scanner.nextLine();
            ui.showLine();

            String[] parts = input.split(" ", 2); // Split into command + rest of string
            String command = parts[0];
            String taskName = (parts.length > 1) ? parts[1] : "";

            try {
                Task modifiedTask;

                switch (command) {
                case COMMAND_BYE:
                    ui.showBye();
                    isActive = false;
                    break;

                case COMMAND_LIST:
                    ui.showTaskList(taskList.getTasks());
                    break;

                case COMMAND_MARK:
                    modifiedTask = taskList.markTask(taskName);
                    ui.showMarked(modifiedTask);
                    storeTaskList();
                    break;

                case COMMAND_UNMARK:
                    modifiedTask = taskList.unmarkTask(taskName);
                    ui.showUnmarked(modifiedTask);
                    storeTaskList();
                    break;

                case COMMAND_TODO:
                    modifiedTask = taskList.addTodo(taskName);
                    ui.showAdded(modifiedTask, taskList.getSize());
                    storeTaskList();
                    break;

                case COMMAND_DEADLINE:
                    modifiedTask = taskList.addDeadline(taskName);
                    ui.showAdded(modifiedTask, taskList.getSize());
                    storeTaskList();
                    break;

                case COMMAND_EVENT:
                    modifiedTask = taskList.addEvent(taskName);
                    ui.showAdded(modifiedTask, taskList.getSize());
                    storeTaskList();
                    break;

                case COMMAND_DELETE:
                    modifiedTask = taskList.deleteTask(taskName);
                    ui.showDeleted(modifiedTask, taskList.getSize());
                    storeTaskList();
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

    private static void storeTaskList() throws OlafException {
        storage.save(taskList.getTasks());
    }
}


