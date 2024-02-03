package duke.commands;

import duke.exception.MalformedUserInputException;

/**
 * Represents the command to add an event to the data storage.
 * @@author se-edu
 * Reuse from https://github.com/se-edu/addressbook-level2
 * with minor modifications to cater for differences in
 * error handling and output.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = "\t " + COMMAND_WORD + ": Marks an event as completed.\n"
            + "\t Example: " + COMMAND_WORD
            + " 1 ";

    public static final String MESSAGE_SUCCESS = "\t Nice! I've marked this task as done:\n"
            + "\t %s";
    public static final String MESSAGE_INVALID_ID = "\t Please enter a integer that is 1 or larger.";
    public static final String MESSAGE_TASK_NOT_CREATED_YET = " \t This is an invalid index\n"
            + "\t There are %d tasks available.";

    private int targetIndex;

    public MarkCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute() {
        try {
            dataStorage.setTaskStatus(targetIndex, true);
            return new CommandResult(String.format(MESSAGE_SUCCESS, dataStorage.getTask(targetIndex)));
        } catch (MalformedUserInputException e) {
            return new CommandResult(e.getMessage());
        } catch (IndexOutOfBoundsException iobe) {
            return new CommandResult(String.format(MESSAGE_TASK_NOT_CREATED_YET, dataStorage.getTaskCount()));
        }
    }
}
