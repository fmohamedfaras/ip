import java.util.ArrayList;

import Tasks.Task;

public class Ui {
    private static final String DIVIDER = "     ____________________________________________________________";
    private static final String INDENT = "     ";

    public static final String ERROR_UNKNOWN_COMMAND = "I don't know what that means. Samantha? (laughs hysterically) I don't even know a Samantha!";
    public static final String ERROR_EMPTY_TASK = "Stop it, Sven. Try and focus here. \n     The description is empty. A task needs a name.";
    public static final String ERROR_MISSING_BY = "I need to know when! Please use '/by' so I don't forget.";
    public static final String ERROR_MISSING_FROM_TO = "Events need a start and end! Use '/from' and '/to'. Time is tricky!";
    public static final String ERROR_MISSING_TO = "You forgot the ending time! Use '/to'.";
    public static final String ERROR_INVALID_INDEX = "I can't find that number! Did it melt?";
    public static final String ERROR_NO_INDEX = "Who’s the funny-looking donkey… And who’s the reindeer? You didn't tell me the number!";
    public static final String ERROR_NOT_A_NUMBER = "I can’t read… or spell but that is definitely not a number!";
    public static final String ERROR_NO_DELETE_INDEX = "You didn't tell me the number to delete silly!";

    public void showWelcome() {
        showLine();
        System.out.println(INDENT + "Hello! I'm Olaf!");
        System.out.println(INDENT + "I like warm hugs!");
        System.out.println(INDENT + "What can I do for you on this fine summer day?");
        showLine();
    }

    public void showBye() {
        System.out.println(INDENT + "I wish this could last forever, and yet change mocks us with her beauty.");
        System.out.println(INDENT + "Hands down, this is the best day of my life. And quite possibly the last.");
        System.out.println(INDENT + "Bye! (starts melting) Some people are worth melting for!");
        showLine();
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showError(String message) {
        System.out.println(INDENT + message);
        showLine();
    }

    public void showTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(INDENT + "I don’t have a skull. Or bones.");
            System.out.println(INDENT + "Likewise, your list has nothing. Empty.");
        } else {
            System.out.println(INDENT + "Here are the things you need to do:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(INDENT + (i + 1) + "." + tasks.get(i));
            }
        }
        showLine();
    }

    public void showMarked(Task task) {
        System.out.println(INDENT + "I mean I presume we’re done or is this “putting us in mortal danger” situation gonna be a regular thing");
        System.out.println(INDENT + task);
        showLine();
    }

    public void showUnmarked(Task task) {
        System.out.println(INDENT + "Really? Wow, I can’t wait until I’ve aged just like you, so I don’t have to worry about important things.");
        System.out.println(INDENT + "We can do it later then.");
        System.out.println(INDENT + task);
        showLine();
    }

    public void showAdded(Task task, int count) {
        String taskPlural = (count == 1) ? " task " : " tasks ";
        System.out.println(INDENT + "This just got a lot more complicated. I've added this to your list:");
        System.out.println(INDENT + task);
        System.out.println(INDENT + "Now you have " + count + taskPlural + "in the list.");
        showLine();
    }

    public void showDeleted(Task task, int count) {
        String taskPlural = (count == 1) ? " task " : " tasks ";
        System.out.println(INDENT + "This just got a lot less complicated. I've deleted this task:");
        System.out.println(INDENT + task);
        System.out.println(INDENT + "Now you have " + count + taskPlural + "in the list.");
        showLine();
    }
}
