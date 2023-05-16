package me.lab6.server.commands;

import me.lab6.common.Response;
import me.lab6.common.exceptions.ArgMustBeEmptyException;
import me.lab6.server.managers.CollectionManager;
import me.lab6.server.managers.FileManager;

import java.io.IOException;

/**
 * A command that stops the application without saving.
 * Implements the {@link Command} interface.
 */
public class Exit implements Command {

    private final FileManager fileManager;
    private final CollectionManager collectionManager;

    public Exit(CollectionManager collectionManager, FileManager fileManager) {
        this.fileManager = fileManager;
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new ArgMustBeEmptyException();
            if (fileManager.writeWorkersToFile(collectionManager)) {
                System.exit(0);
            }
        } catch (ArgMustBeEmptyException e) {
            System.out.println("Command arguments must be empty.");
        } catch (IOException e) {
            System.out.println("There was an error saving collection to file.");
            ;
        }
        return null;
    }

    /**
     * Returns the name of this command.
     *
     * @return a string representing the name of the command ("exit")
     */
    @Override
    public String name() {
        return "exit";
    }

    /**
     * Returns a string representing the argument syntax of this command.
     *
     * @return an empty string (as this command does not require any arguments)
     */
    @Override
    public String argDesc() {
        return "";
    }

    /**
     * Returns a string representing the description of this command.
     *
     * @return a string describing the functionality of the command ("stop the application without saving")
     */
    @Override
    public String desc() {
        return "stop the application without saving";
    }

}
