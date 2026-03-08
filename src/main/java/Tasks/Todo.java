package Tasks;

/**
 * Represents a basic task without any specific date or time attached to it.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the provided description.
     *
     * @param description The text detailing what the task is.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Translates the Todo into a formatted string for terminal display.
     *
     * @return The display string prefixed with "[T]".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats the Todo data into a standardized string for hard drive storage.
     *
     * @return The save format string prefixed with "T | ".
     */
    @Override
    public String toSaveFormat() {
        return "T | " + super.toSaveFormat();
    }
}
