import java.util.Scanner;

import Tasks.TaskList;
import core.OlafException;
import core.Storage;
import core.Ui;
import core.Parser;
import Commands.Command;

/**
 * The main class for the Olaf chatbot application.
 * It initializes the core components and manages the main execution loop.
 */
public class Olaf {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs an Olaf instance and initializes the user interface, storage, and task list.
     * Attempts to load existing tasks from the specified file path. If loading fails,
     * it starts with an empty task list.
     *
     * @param filePath The relative path to the text file where tasks are saved and loaded.
     */
    public Olaf(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (OlafException e) {
            ui.showError("Unable to load file. Starting with an empty list!");
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the chatbot.
     * Continuously reads user input, parses it into commands, and executes them
     * until an exit command is issued.
     */
    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = scanner.nextLine();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (OlafException e) {
                ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * The main entry point for the application.
     * Creates a new instance of Olaf with the default save file path and starts the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Olaf("./data/olaf.txt").run();
    }
}