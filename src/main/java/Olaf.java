import java.util.Scanner;


public class Olaf {

    private static final int CAPACITY = 100;
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    private static Task[] tasks = new Task[CAPACITY];
    private static int taskCount = 0;

    private static Ui ui = new Ui();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
                    ui.showTaskList(tasks, taskCount);
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
        tasks[taskCount] = new Todo(input);
        taskCount++;
        ui.showAdded(tasks[taskCount - 1], taskCount);
    }

    private static void addDeadline(String input) throws OlafException {
        if (input.isEmpty()) {
            throw new OlafException(Ui.ERROR_EMPTY_TASK);
        }
        if (!input.contains(" /by ")) {
            throw new OlafException(Ui.ERROR_MISSING_BY);
        }
        String[] parts = input.split(" /by ");
        tasks[taskCount] = new Deadline(parts[0], parts[1]);
        taskCount++;
        ui.showAdded(tasks[taskCount - 1], taskCount);
    }


    private static void addEvent(String input) throws OlafException {

        if (input.isEmpty()) {
            throw new OlafException(Ui.ERROR_EMPTY_TASK);
        }
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new OlafException(Ui.ERROR_MISSING_FROM_TO);
        }
        // split by " /from " first
        String[] parts = input.split(" /from ");
        String description = parts[0];

        // split the second part by " /to "
        String[] timeParts = parts[1].split(" /to ");
        String from = timeParts[0];
        String to = timeParts[1];

        if (timeParts.length < 2) {
            throw new OlafException(Ui.ERROR_MISSING_TO);
        }

        tasks[taskCount] = new Event(description, timeParts[0], timeParts[1]);
        taskCount++;
        ui.showAdded(tasks[taskCount - 1], taskCount);
    }

    public static void markTask(String parts) {
        int markIndex = Integer.parseInt(parts) - 1;
        tasks[markIndex].markAsDone();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("     " + tasks[markIndex]);
        System.out.println(DIVIDER);
    }

    public static void unmarkTask(String parts) {
        int markIndex = Integer.parseInt(parts) - 1;
        tasks[markIndex].unmarkAsDone();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("     " + tasks[markIndex]);
        System.out.println(DIVIDER);
    }

}


