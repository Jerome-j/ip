import commands.ByeCommand;
import commands.Command;
import commands.CommandResult;
import common.DataStorage;
import exception.MalformedUserInputException;
import parser.EventParser;
import parser.Parser;
import tasklist.Task;

public class Duke {

    private Ui ui;
    private DataStorage dataStorage;

    public Duke() {

    }

    public void start() {
        // TODO: There could be a try catch here
        this.ui = new Ui();
        this.dataStorage = new DataStorage(Integer.MAX_VALUE, "database.txt");
        ui.showWelcome();
    }

    /**
     * Prints the Goodbye message and exits.
     */
    private void exit() {
        ui.showGoodbye();
        System.exit(0);
    }


    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    public static void main(String[] args) {
        new Duke().run();
    }


    public CommandResult executeCommand(Command command) {
        command.setData(dataStorage);
        CommandResult commandResult = command.execute();
        return commandResult;
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command.
     */
    private void runCommandLoopUntilExitCommand() {

        Command command;

        do {
            // Keep reading user input until they type "bye"
            String userInput = ui.readCommand();
            command = new Parser().parseCommand(userInput);

            CommandResult commandResult = executeCommand(command);

            ui.showLine();
            System.out.println(commandResult.feedbackToUser);
            ui.showLine();

        } while (!ByeCommand.isExit(command));
    }


}

