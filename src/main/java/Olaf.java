import java.util.Scanner;


public class Olaf {

    private static final int CAPACITY = 100;
    private static final String DIVIDER = "     ____________________________________________________________";
    private static final String WELCOME_MESSAGE = "     Hello! I'm Olaf and I like warm hugs!";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";

    private static String[] list = new String[CAPACITY];
    private static boolean[] isMarked = new boolean[CAPACITY];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(DIVIDER);
        System.out.println(WELCOME_MESSAGE);

        boolean isActive = true;
        int count = 0;

        while (isActive) {
           String input = scanner.nextLine();

            System.out.println(DIVIDER);

            String[] parts = input.split(" ");
            String command = parts[0];

            switch (command){
            case COMMAND_BYE:
                    System.out.println("    Bye. Hope to see you again soon!");
                    System.out.println(DIVIDER);
                    isActive = false;
                    scanner.close();
                    break;

            case COMMAND_LIST:
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < count; i++){
                    String status = isMarked[i] ? "[X] " : "[ ] ";
                    System.out.println("    " + (i + 1) + "." + status + list[i]);
                }
                System.out.println(DIVIDER);
                break;

            case COMMAND_MARK:
                int markIndex = Integer.parseInt(parts[1]) - 1;
                isMarked[markIndex] = true;

                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("    [X] " + list[markIndex]);
                System.out.println(DIVIDER);
                break;

            case COMMAND_UNMARK:
                int unmarkIndex = Integer.parseInt(parts[1]) - 1;
                isMarked[unmarkIndex] = false;

                System.out.println("    OK, I've marked this task as not done yet:");
                System.out.println("    [ ] " + list[unmarkIndex]);
                System.out.println(DIVIDER);
                break;

            default:
                System.out.println("    added: " + input);
                list[count] = input;
                isMarked[count] = false;
                count++;
                System.out.println(DIVIDER);

            }

        }

    }
}


