import java.util.Scanner;

import Tasks.TaskList;
import core.OlafException;
import core.Storage;
import core.Ui;
import core.Parser;
import Commands.Command;

public class Olaf {

    // 1. Removed "static" - these now belong to the Olaf instance
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

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

    public static void main(String[] args) {
        new Olaf("./data/olaf.txt").run();
    }
}