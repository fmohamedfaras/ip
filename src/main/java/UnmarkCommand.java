import Tasks.Task;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OlafException {
        Task modifiedTask = tasks.unmarkTask(index);
        ui.showUnmarked(modifiedTask);
        storage.save(tasks.getTasks());
    }
}
