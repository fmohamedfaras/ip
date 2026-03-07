package core;

import Commands.Command;
import Commands.AddCommand;
import Commands.DeleteCommand;
import Commands.ExitCommand;
import Commands.ListCommand;
import Commands.MarkCommand;
import Commands.UnmarkCommand;
import Tasks.Todo;
import Tasks.Event;
import Tasks.Deadline;



public class Parser {
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";

    public static final String BY = " /by ";
    public static final String FROM = " /from ";
    public static final String TO = " /to ";

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
            return new AddCommand(new Deadline(deadlineInputs[0], deadlineInputs[1]));

        case COMMAND_EVENT:
            String[] eventInputs = parseEvent(taskDesc);
            return new AddCommand(new Event(eventInputs[0], eventInputs[1], eventInputs[2] ));

        case COMMAND_DELETE:
            return new DeleteCommand(parseIndex(taskDesc));

        default:
            throw new OlafException(Ui.ERROR_UNKNOWN_COMMAND);
        }
    }

    private static String parseTodo(String input) throws OlafException {
        String description = input.trim();
        if (description.isBlank()) {
            throw new OlafException(Ui.ERROR_EMPTY_TASK);
        }
        return description;
    }

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
}
