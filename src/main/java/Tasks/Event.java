package Tasks;

/**
 * Represents a task that spans a specific duration with a start and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The text detailing what the event is.
     * @param from        The starting time or date of the event.
     * @param to          The ending time or date of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Translates the Event into a formatted string for terminal display.
     *
     * @return The display string prefixed with "[E]" and appended with the timeframe.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Formats the Event data into a standardized string for hard drive storage.
     *
     * @return The save format string prefixed with "E | from | to.
     */
    @Override
    public String toSaveFormat() {
        return "E | " + super.toSaveFormat() + " | " + this.from + " | " + this.to;
    }
}
