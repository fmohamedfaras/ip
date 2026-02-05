import java.util.Scanner;


public class Olaf {

    private static final int CAPACITY = 100;
    private static final String DIVIDER = "     ____________________________________________________________";
    private static final String WELCOME_MESSAGE = "     Hello! I'm Olaf and I like warm hugs!";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";

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

            String[] inputParts = input.split(" ");
            String command = inputParts[0];

            switch (command) {
            case COMMAND_BYE:
                printBye();
                isActive = false;
                break;

            case COMMAND_LIST:
                listTasks();
                break;

            case COMMAND_MARK:
                markTask(inputParts);
                break;

            case COMMAND_UNMARK:
                unmarkTask(inputParts);
                break;

            default:
                addTask(input);
            }
        }
        scanner.close();
    }

    public static void addTask(String input) {
        tasks[taskCount] = new Task(input);
        taskCount++;
        System.out.println("    added: " + input);
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

    public static void markTask(String[] parts) {
        int markIndex = Integer.parseInt(parts[1]) - 1;
        tasks[markIndex].markAsDone();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("     " + tasks[markIndex]);
        System.out.println(DIVIDER);
    }

    public static void unmarkTask(String[] parts) {
        int markIndex = Integer.parseInt(parts[1]) - 1;
        tasks[markIndex].unmarkAsDone();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("     " + tasks[markIndex]);
        System.out.println(DIVIDER);
    }

}


