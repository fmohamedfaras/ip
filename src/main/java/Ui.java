public class Ui {
    private static final String DIVIDER = "     ____________________________________________________________";

    public void showWelcome() {
        showLine();
        System.out.println("     Hello! I'm Olaf!");
        System.out.println("     I like warm hugs!");
        System.out.println("     What can I do for you on this fine summer day?");
        showLine();
    }

    public void showBye() {
        System.out.println("    I wish this could last forever, and yet change mocks us with her beauty.");
        System.out.println("    Hands down, this is the best day of my life. And quite possibly the last.");
        System.out.println("    Bye! (starts melting) Some people are worth melting for!");
        showLine();
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showError(String message) {
        System.out.println("     OH NO! " + message);
        showLine();
    }

    public void showTaskList(Task[] tasks, int count) {
        if (count == 0) {
            System.out.println("    I don’t have a skull. Or bones.");
            System.out.println("    Likewise, your list has nothing. Empty.");
        } else {
            System.out.println("     Here are the things you need to do:");
            for (int i = 0; i < count; i++) {
                System.out.println("     " + (i + 1) + "." + tasks[i]);
            }
        }
        showLine();
    }

    public void showMarked(Task task) {
        System.out.println("    I mean I presume we’re done or is this “putting us in mortal danger” situation gonna be a regular thing");
        System.out.println("    " + task);
        showLine();
    }

    public void showUnmarked(Task task) {
        System.out.println("    Really? Wow, I can’t wait until I’ve aged just like you, so I don’t have to worry about important things.");
        System.out.println("    We can do it later then.");
        System.out.println("       " + task);
        showLine();
    }

    public void showAdded(Task task, int count) {
        System.out.println("     This just got a lot more complicated. I've added this to your list:");
        System.out.println("       " + task);
        System.out.println("     Now you have " + count + " tasks in the list.");
        showLine();
    }
}
