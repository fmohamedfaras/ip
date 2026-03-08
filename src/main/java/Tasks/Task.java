package Tasks;

/**
 * Represents a generic task with a description and completion status.
 * This is the parent class for Todo, Deadline, and Event.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the provided description.
     *
     * @param description The text detailing what the task is.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the text description of the task.
     *
     * @return The description string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the visual status icon for the task.
     *
     * @return "X" if the task is completed, or a blank space if it is not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task, setting it to an incomplete state.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Translates the task into a formatted string for display in the terminal.
     *
     * @return The display string containing the status icon and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Formats the task data into a standardized string suitable for saving to the hard drive.
     *
     * @return The save format string (e.g., "1 | read book").
     */
    public String toSaveFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}


