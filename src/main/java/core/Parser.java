package core;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import Commands.Command;
import Commands.AddCommand;
import Commands.DeleteCommand;
import Commands.ExitCommand;
import Commands.FindCommand;
import Commands.ListCommand;
import Commands.MarkCommand;
import Commands.UnmarkCommand;
import Tasks.Todo;
import Tasks.Event;
import Tasks.Deadline;

/**
 * Handles the interpretation of user input.
 * Translates raw string commands into executable Command objects.
 */
public class Parser {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";

    public static final String BY = " /by ";
    public static final String FROM = " /from ";
    public static final String TO = " /to ";

    /**
     * Parses the raw user input string and translates it into an executable Command object.
     *
     * @param fullCommand The full text typed by the user in the terminal.
     * @return The specific Command object to be executed.
     * @throws OlafException If the command is unknown or improperly formatted.
     */
    public static Command parse(String fullCommand) throws OlafException{
        String[] parts = fullCommand.split(" ", 2); // Split into command + rest of string
        String command = parts[0].toLowerCase();
        String taskDesc = (parts.length > 1) ? parts[1] : "";

        switch (command) {
        case COMMAND_BYE:
            return new ExitCommand();

        case COMMAND_LIST:
            return new ListCommand();

        case COMMAND_MARK:
            return new MarkCommand(parseIndex(taskDesc));

        case COMMAND_UNMARK:
            return new UnmarkCommand(parseIndex(taskDesc));

        case COMMAND_TODO:
            return new AddCommand(new Todo(parseTodo(taskDesc)));

        case COMMAND_DEADLINE:
            String[] deadlineInputs = parseDeadline(taskDesc);
            try {
                LocalDate parsedDate = LocalDate.parse(deadlineInputs[1]);
                return new AddCommand(new Deadline(deadlineInputs[0], parsedDate));
            } catch (DateTimeParseException e) {
                throw new OlafException("Please enter the date in yyyy-MM-dd format (e.g., 2026-10-15).");
            }

        case COMMAND_EVENT:
            String[] eventInputs = parseEvent(taskDesc);
            return new AddCommand(new Event(eventInputs[0], eventInputs[1], eventInputs[2] ));

        case COMMAND_DELETE:
            return new DeleteCommand(parseIndex(taskDesc));

        case COMMAND_FIND:
            return new FindCommand(parseKeyword(taskDesc));

        default:
            throw new OlafException(Ui.ERROR_UNKNOWN_COMMAND);
        }
    }

    /**
     * Validates and extracts the description for a Todo task.
     *
     * @param input The user input following the "todo" command.
     * @return The trimmed description of the Todo task.
     * @throws OlafException If the description is empty.
     */
    private static String parseTodo(String input) throws OlafException {
        String description = input.trim();
        if (description.isBlank()) {
            throw new OlafException(Ui.ERROR_EMPTY_TASK);
        }
        return description;
    }

    /**
     * Validates and extracts the description and date for a Deadline task.
     *
     * @param input The user input following the "deadline" command.
     * @return A String array where index 0 is the description and index 1 is the deadline string.
     * @throws OlafException If the description is empty or the "/by" keyword is missing.
     */
    private static String[] parseDeadline(String input) throws OlafException {
        if (input.isBlank()) {
            throw new OlafException(Ui.ERROR_EMPTY_TASK);
        }
        if (!input.contains(BY)) {
            throw new OlafException(Ui.ERROR_MISSING_BY);
        }

        String[] parts = input.split(BY);

        if (parts.length < 2) {
            throw new OlafException(Ui.ERROR_MISSING_BY);
        }

        String description = parts[0].trim();
        String by = parts[1].trim();

        if (description.isBlank()) {
            throw new OlafException(Ui.ERROR_EMPTY_TASK);
        }

        if (by.isBlank()) {
            throw new OlafException(Ui.ERROR_MISSING_BY);
        }
        return new String[] {description, by};
    }

    /**
     * Validates and extracts the description, start time, and end time for an Event task.
     *
     * @param input The user input following the "event" command.
     * @return A String array where index 0 is the description, index 1 is the start time, and index 2 is the end time.
     * @throws OlafException If the description is empty or the "/from" or "/to" keywords are missing.
     */
    private static String[] parseEvent(String input) throws OlafException{
        if (input.isBlank()) {
            throw new OlafException(Ui.ERROR_EMPTY_TASK);
        }
        if (!input.contains(FROM) || !input.contains(TO)) {
            throw new OlafException(Ui.ERROR_MISSING_FROM_TO);
        }

        // split by " /from " first
        String[] parts = input.split(FROM);

        if (parts.length < 2) {
            throw new OlafException(Ui.ERROR_MISSING_FROM_TO);
        }

        String description = parts[0].trim();
        if (description.isBlank()){
            throw new OlafException(Ui.ERROR_EMPTY_TASK);
        }

        // split the second part by " /to "
        String[] timeParts = parts[1].split(TO);

        if (timeParts.length < 2) {
            throw new OlafException(Ui.ERROR_MISSING_FROM_TO);
        }

        String from = timeParts[0].trim();
        String to = timeParts[1].trim();

        if (from.isBlank() || to.isBlank()) {
            throw new OlafException(Ui.ERROR_MISSING_FROM_TO);
        }
        return new String[] {description, from, to};
    }

    /**
     * Converts a user-provided string into a zero-based integer index.
     * Used for commands that target specific tasks (e.g., mark, unmark, delete).
     *
     * @param input The string representing the list number.
     * @return The zero-based integer index.
     * @throws OlafException If the input is empty or cannot be parsed as a number.
     */
    private static int parseIndex(String input) throws OlafException {
        if (input.isBlank()) {
            throw new OlafException(Ui.ERROR_NO_INDEX);
        }
        try {
            return Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new OlafException(Ui.ERROR_NOT_A_NUMBER);
        }
    }

    /**
     * Validates and extracts the search keyword for a Find command.
     *
     * @param input The user input following the "find" command.
     * @return The trimmed search keyword.
     * @throws OlafException If the keyword is missing or only contains whitespace.
     */
    private static String parseKeyword(String input) throws OlafException {
        if(input.isBlank()) {
            throw new OlafException(Ui.ERROR_NO_KEYWORD);
        }
        return input.trim();
    }
}
