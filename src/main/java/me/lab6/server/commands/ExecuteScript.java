package commands;

import exceptions.ExitException;
import exceptions.InputEndException;
import exceptions.ScriptEndException;
import managers.CollectionManager;
import managers.CommandManager;
import managers.FileManager;
import managers.UserInteractionManager;
import utilities.DataLimitations;
import utilities.DataType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Executes commands in the given script.
 */
public class ExecuteScript implements Command {

    private final FileManager fileManager;
    private final CollectionManager collectionManager;
    private static final Deque<File> scriptDeque = new ArrayDeque<>();
    private static final Deque<Scanner> scannerDeque = new ArrayDeque<>();

    /**
     * Constructs an ExecuteScript object with the specified column manager and file manager.
     *
     * @param collectionManager   collection manager to use for executing the script
     * @param fileManager file manager to use for accessing the script file
     */
    public ExecuteScript(CollectionManager collectionManager, FileManager fileManager) {
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command with the specified file path argument.
     * If the specified file has already been executed, interrupts the execution to avoid infinite recursion.
     * Otherwise, executes the script by setting the input source to the script file and creating a new UIMan object.
     *
     * @param arg the file path of the script to execute
     * @throws ExitException if the user chooses to exit the program
     */
    @Override
    public void execute(String arg) throws ExitException {
        File script = new File(arg).getAbsoluteFile();
        if (scriptDeque.contains(script)) {
            scriptDeque.clear();
            scannerDeque.clear();
            UserInteractionManager.setSource(new Scanner(System.in), true);
            System.out.println("Recursion detected during the script execution. Script execution interrupted.\n");
        } else {
            try {
                Scanner scanner = new Scanner(script);
                scriptDeque.addLast(script);
                scannerDeque.addLast(scanner);
                UserInteractionManager.setSource(scanner, false);
                CollectionManager collectionManager1 = collectionManager;
                CommandManager commandManager = new CommandManager(collectionManager1, fileManager, false);
                UserInteractionManager userInteractionManager = new UserInteractionManager(commandManager);
                System.out.println("Executing " + script.getPath() + "...\n");
                try {
                    userInteractionManager.interact();
                } catch (ScriptEndException e) {
                    collectionManager.setWorkerMap(collectionManager1.getWorkerMap());
                    scriptDeque.removeLast();
                    scannerDeque.removeLast();
                    System.out.println("Script " + script.getPath() + " finished execution.\n");
                    if (!scriptDeque.isEmpty()) {
                        UserInteractionManager.setSource(scannerDeque.getLast(), false);
                    } else {
                        UserInteractionManager.setSource(new Scanner(System.in), true);
                    }
                } catch (InputEndException | ExitException e) {
                    throw new ExitException();
                }
            } catch (FileNotFoundException e) {
                System.out.println("File " + arg + " not found.\n");
            }
        }
    }

    /**
     * Returns the name of the command.
     *
     * @return the name of the command ("execute_script")
     */
    @Override
    public String name() {
        return "execute_script";
    }

    /**
     * Returns the expected argument format for the command.
     *
     * @return the expected argument format for the command ("{file_path}")
     */
    @Override
    public String argDesc() {
        return "{file_path}";
    }

    /**
     * Returns the description of the command for the help command.
     *
     * @return the description of the command ("execute the sequence of commands from a file")
     */
    @Override
    public String desc() {
        return "execute the sequence of commands from a file";
    }

    /**
     * Returns the data limitations for the command argument.
     *
     * @return the data limitations for the command argument
     */
    @Override
    public Object[] argLimitations() {
        return new DataLimitations(DataType.FILEPATH,
                UserInteractionManager.wrongArgMessage(this) + "Please, use a proper file path.",
                UserInteractionManager.noArgMessage(this)).limitations();
    }
}
