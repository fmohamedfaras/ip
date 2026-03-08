package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that needs to be completed by a specific date.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The text detailing what the task is.
     * @param by          The LocalDate object representing the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Translates the Deadline into a formatted string for terminal display, including a human-readable date.
     *
     * @return The display string prefixed with "[D]" and appended with the formatted date.
     */
    @Override
    public String toString() {
        String formattedDate = by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "[D]" + super.toString() + " (by: " + formattedDate + ")";
    }

    /**
     * Formats the Deadline data into a standardized string for hard drive storage.
     *
     * @return The save format string prefixed with "D | date".
     */
    @Override
    public String toSaveFormat() {
        return "D | " + super.toSaveFormat() + " | " + this.by;
    }
}