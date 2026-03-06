import Tasks.Task;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OlafException {
        Task removedTask = tasks.deleteTask(index);
        ui.showDeleted(removedTask, tasks.getSize());
        storage.save(tasks.getTasks());
    }
}
