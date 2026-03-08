package Commands;

import Tasks.TaskList;
import core.Ui;
import core.Storage;

/**
 * Represents a command to safely exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit protocol by displaying a goodbye message to the user.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage component.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    /**
     * Signals the main loop in the application to terminate.
     *
     * @return true, as this command explicitly requests an exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}