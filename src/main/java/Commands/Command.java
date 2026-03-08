package Commands;

import Tasks.TaskList;
import core.Ui;
import core.Storage;
import core.OlafException;

/**
 * Represents an executable command from the user.
 * This is an abstract class that all specific commands must inherit from.
 */
public abstract class Command {
    /**
     * Executes the specific action of the command.
     *
     * @param tasks   The list of tasks currently active in the program.
     * @param ui      The user interface handling input and output.
     * @param storage The storage handling the saving and loading of tasks.
     * @throws OlafException If an error occurs during the execution of the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws OlafException;

    /**
     * Indicates whether this command should terminate the main program loop.
     *
     * @return true if the program should exit; false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
