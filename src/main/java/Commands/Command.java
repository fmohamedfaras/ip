package Commands;

import Tasks.TaskList;
import core.Ui;
import core.Storage;
import core.OlafException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws OlafException;

    public boolean isExit() {
        return false;
    }
}
