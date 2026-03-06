import Tasks.Task;

public class AddCommand extends Command {
    private Task taskToAdd;

    // Constructor accepts ANY type of Task
    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OlafException {
        // Add the task to the list
        tasks.addTask(taskToAdd);

        // Show the UI message
        ui.showAdded(taskToAdd, tasks.getSize());

        // Save to the hard drive
        storage.save(tasks.getTasks());
    }
}