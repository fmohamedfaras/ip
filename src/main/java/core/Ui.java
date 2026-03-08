package core;

import java.util.ArrayList;

import Tasks.Task;
import Tasks.TaskList;

/**
 * Handles all interactions with the user via the terminal.
 * Contains methods to print greetings, errors, and task lists in an Olaf-themed persona.
 */
public class Ui {
    private static final String DIVIDER = "     ____________________________________________________________";
    private static final String INDENT = "     ";

    public static final String ERROR_UNKNOWN_COMMAND = "I don't know what that means. Samantha? (laughs hysterically) I don't even know a Samantha!";
    public static final String ERROR_EMPTY_TASK = "Stop it, Sven. Try and focus here. \n     The description is empty. A task needs a name.";
    public static final String ERROR_MISSING_BY = "I need to know when! Please put the description and '/by' so I don't forget.";
    public static final String ERROR_MISSING_FROM_TO = "Events need a description, start and end! Use '/from' and '/to'. Time is tricky!";
    public static final String ERROR_INVALID_INDEX = "I can't find that index! Did it melt?";
    public static final String ERROR_NO_INDEX = "You didn't tell me the index silly!";
    public static final String ERROR_NOT_A_NUMBER = "I can’t read… or spell but that is definitely not a number!";
    public static final String ERROR_NO_KEYWORD = "Please provide a keyword to search for!";
    public static final String ART = "  ___  _        __ \n" +
            INDENT + " / _ \\| | __ _ / _|\n" +
            INDENT + "| | | | |/ _` | |_ \n" +
            INDENT + "| |_| | | (_| |  _|\n" +
            INDENT + " \\___/|_|\\__,_|_|  ";

    /**
     * Displays the welcome message when the application starts.
     */
    public void showWelcome() {
        showLine();
        System.out.println(INDENT + "Hello! I'm ");
        System.out.println(INDENT + ART);
        System.out.println(INDENT + "I like warm hugs!");
        System.out.println(INDENT + "What can I do for you on this fine summer day?");
        showLine();
    }

    /**
     * Displays the farewell message when the user exits the application.
     */
    public void showBye() {
        System.out.println(INDENT + "I wish this could last forever, and yet change mocks us with her beauty.");
        System.out.println(INDENT + "Hands down, this is the best day of my life. And quite possibly the last.");
        System.out.println(INDENT + "Bye! (starts melting) Some people are worth melting for!");
        showLine();
    }

    /**
     * Prints a visual divider line for terminal formatting.
     */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(INDENT + message);
        showLine();
    }

    /**
     * Displays the complete list of active tasks to the user.
     *
     * @param tasks The TaskList of tasks to be displayed.
     */
    public void showTaskList(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println(INDENT + "I don’t have a skull. Or bones.");
            System.out.println(INDENT + "Likewise, your list has nothing. Empty.");
        } else {
            System.out.println(INDENT + "Here are the things you need to do:");
            for (int i = 0; i < tasks.getSize(); i++) {
                System.out.println(INDENT + (i + 1) + "." + tasks.getTask(i));
            }
        }
        showLine();
    }

    /**
     * Displays a confirmation message when a task is marked as done.
     *
     * @param task The task that was successfully marked.
     */
    public void showMarked(Task task) {
        System.out.println(INDENT + "I mean I presume we’re done or is this “putting us in mortal danger” situation gonna be a regular thing");
        System.out.println(INDENT + task);
        showLine();
    }

    /**
     * Displays a confirmation message when a task is unmarked.
     *
     * @param task The task that was successfully unmarked.
     */
    public void showUnmarked(Task task) {
        System.out.println(INDENT + "Really? Wow, I can’t wait until I’ve aged just like you, so I don’t have to worry about important things.");
        System.out.println(INDENT + "We can do it later then.");
        System.out.println(INDENT + task);
        showLine();
    }

    /**
     * Displays a confirmation message when a new task is added.
     *
     * @param task  The newly added task.
     * @param count The new total number of tasks in the list.
     */
    public void showAdded(Task task, int count) {
        String taskPlural = (count == 1) ? " task " : " tasks ";
        System.out.println(INDENT + "This just got a lot more complicated. I've added this to your list:");
        System.out.println(INDENT + task);
        System.out.println(INDENT + "Now you have " + count + taskPlural + "in the list.");
        showLine();
    }

    /**
     * Displays a confirmation message when a task is deleted.
     *
     * @param task  The task that was removed.
     * @param count The remaining total number of tasks in the list.
     */
    public void showDeleted(Task task, int count) {
        String taskPlural = (count == 1) ? " task " : " tasks ";
        System.out.println(INDENT + "This just got a lot less complicated. I've deleted this task:");
        System.out.println(INDENT + task);
        System.out.println(INDENT + "Now you have " + count + taskPlural + "in the list.");
        showLine();
    }

    /**
     * Displays the tasks that match the user's search keyword.
     *
     * @param foundTasks The TaskList object containing all matching tasks.
     */
    public void showFoundTasks(TaskList foundTasks) {
        if (foundTasks.getSize() == 0) {
            System.out.println(INDENT + "No matching tasks found in your list.");
        }
        System.out.println(INDENT + "Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.getSize(); i++) {
            System.out.println(INDENT + (i + 1) + "." + foundTasks.getTask(i));
        }
        showLine();
    }
}
