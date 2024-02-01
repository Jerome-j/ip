package duke.commands;

import duke.tasklist.Task;

/**
 * Represents the command to add an event to the data storage.
 * @@author se-edu
 * Reuse from https://github.com/se-edu/addressbook-level2
 * with minor modifications to cater for differences in
 * error handling and output.
 */
public class FindCommand extends Command {

    /**
     * Represents the command word for Find command.
     */
    public static final String COMMAND_WORD = "find";

    /**
     * Represents how each individual event should be formatted.
     */
    private static final String MESSAGE_INDIVIDUAL_LISTING_FORMAT = "\t %d. %s";

    /**
     * Represents message for no items found.
     */
    public static final String MESSAGE_CANNOT_FIND =
            "\t Sorry, but we could not find anything that you requested.";

    public static final String MESSAGE_EMPTY_SEARCH_TERM =
            "\t If you would like to list everything, please use the list command.";

    private String targetIndex;

    public FindCommand(String searchTerm) {
        this.targetIndex = searchTerm;
    }

    /**
     * Executes the command to display a list of all entries in the database.
     *
     * @return a CommandResult containing the formatted list of entries that contains required keywords.
     */
    @Override
    public CommandResult execute() {

        StringBuilder listViewBuilder = new StringBuilder();

        for (int i = 0; i < dataStorage.getTaskCount(); i++) {
            Task currentTask = dataStorage.getTask(i);

            // If the taks contains the required string.
            if (currentTask.getDescription().contains(this.targetIndex)) {
                String entry = String.format(MESSAGE_INDIVIDUAL_LISTING_FORMAT, i + 1, currentTask.toString());
                listViewBuilder.append(entry);

                // Append blank line if it is not last line.
                if (i < dataStorage.getTaskCount() - 1) {
                    listViewBuilder.append(System.lineSeparator());
                }

            }


        }

        return new CommandResult(listViewBuilder.toString());
    }
}