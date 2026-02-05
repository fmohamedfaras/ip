import java.util.Scanner;


public class Olaf {

    private static final int CAPACITY = 100;
    private static final String DIVIDER = "     ____________________________________________________________";
    private static final String WELCOME_MESSAGE = "     Hello! I'm Olaf and I like warm hugs!";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    private static Task[] tasks = new Task[CAPACITY];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(DIVIDER);
        System.out.println(WELCOME_MESSAGE);

        boolean isActive = true;

        while (isActive) {
           String input = scanner.nextLine();

            System.out.println(DIVIDER);

            String[] parts = input.split(" ", 2); // Split into command + rest of string
            String command = parts[0];
            String taskName = (parts.length > 1) ? parts[1] : "";

            switch (command) {
            case COMMAND_BYE:
                printBye();
                isActive = false;
                break;

            case COMMAND_LIST:
                listTasks();
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
                System.out.println("    Unknown command. Please try again.");
                System.out.println(DIVIDER);
            }
        }
        scanner.close();
    }

    public static void addTodo(String input) {
        tasks[taskCount] = new Todo(input);
        taskCount++;

        String taskPlural = (taskCount == 1) ? " task " : " tasks ";

        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + tasks[taskCount - 1]);
        System.out.println("    Now you have " + taskCount + taskPlural + "in the list.");
        System.out.println(DIVIDER);
    }

    private static void addDeadline(String input) {
        String[] parts = input.split(" /by ");
        String description = parts[0];
        String by = parts[1];

        tasks[taskCount] = new Deadline(description, by);
        taskCount++;

        String taskPlural = (taskCount == 1) ? " task " : " tasks ";

        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + tasks[taskCount - 1]);
        System.out.println("    Now you have " + taskCount + taskPlural + "in the list.");
        System.out.println(DIVIDER);
    }

    private static void addEvent(String input) {
        // split by " /from " first
        String[] parts = input.split(" /from ");
        String description = parts[0];

        // split the second part by " /to "
        String[] timeParts = parts[1].split(" /to ");
        String from = timeParts[0];
        String to = timeParts[1];

        tasks[taskCount] = new Event(description, from, to);
        taskCount++;

        String taskPlural = (taskCount == 1) ? " task " : " tasks ";

        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + tasks[taskCount - 1]);
        System.out.println("    Now you have " + taskCount + taskPlural + "in the list.");
        System.out.println(DIVIDER);
    }

    public static void listTasks() {
        if (taskCount == 0) {
            System.out.println("    There are no pending tasks.");
        } else {
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++){
                System.out.println("    " + (i + 1) + "." + tasks[i]);
            }
            System.out.println(DIVIDER);
        }
    }

    public static void printBye() {
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
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


