package me.lab6.server.commands;

import me.lab6.common.Response;
import me.lab6.common.exceptions.ArgMustBeEmptyException;
import me.lab6.server.managers.CollectionManager;
import me.lab6.server.managers.FileManager;

import java.io.File;
import java.io.IOException;


public class Exit extends AbstractCommand {
    private final FileManager fileManager;
    private final CollectionManager collectionManager;

    public Exit(CollectionManager collectionManager, FileManager fileManager) {
        super("exit", "exit executing program");
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
            System.out.println("There was an error saving collection to file.");;
        }
        return null;
    }
}
