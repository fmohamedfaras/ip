import java.util.Scanner;


public class Olaf {

    private static final int CAPACITY = 100;
    private static final String DIVIDER = "____________________________________________________________";
    private static final String WELCOME_MESSAGE = "Hello! I'm Olaf and I like warm hugs!";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";

    private static String[] list = new String[CAPACITY];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(DIVIDER);
        System.out.println(WELCOME_MESSAGE);
        boolean isActive = true;
        int count = 0;
        while (isActive) {
           String input = scanner.nextLine();
            System.out.println(DIVIDER);
            switch (input){
            case COMMAND_BYE:
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(DIVIDER);
                    isActive = false;
                    scanner.close();
                    break;
            case COMMAND_LIST:
                for (int i = 0; i < count; i++){
                    System.out.print(i+1 + ". ");
                    System.out.println(list[i]);
                }
                System.out.println(DIVIDER);
                break;

            default:
                System.out.println("added: " + input);
                list[count] = input;
                count++;
                System.out.println(DIVIDER);

            }

        }

    }
}


