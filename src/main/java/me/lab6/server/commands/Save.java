package me.lab6.server.commands;



import me.lab6.common.utility.Limitations;
import me.lab6.server.managers.CollectionManager;
import me.lab6.server.managers.FileManager;

import java.io.IOException;

/**
 * A command to save the current state of the collection to a file.
 * Implements the {@link Command} interface.
 */
public class Save implements Command {
    CollectionManager collectionManager;
    FileManager fileManager;
    /**
     * Constructor for the Save command.
     *
     * @param collectionManager the collection manager object
     * @param fileManager the file manager object
     */
    public Save(CollectionManager collectionManager, FileManager fileManager) {
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }
    /**
     * Executes the save command, saving the current state of the collection to a file.
     *
     * @param arg the command argument
     */
    @Override
    public void execute(String arg) {
        System.out.println("Saving...");
        try {
            fileManager.writeWorkersToFile(collectionManager);
            System.out.println("Successfully saved.\n");
        } catch (IOException e) {
            System.out.println("Failed to save to the source file. Make sure that the file is available.\n");
        }
    }
    /**
     * Returns the name of the command.
     *
     * @return the name of the command
     */
    @Override
    public String name() {
        return "save";
    }
    /**
     * Returns the argument format for the command.
     *
     * @return the argument format for the command
     */
    @Override
    public String argDesc() {
        return "";
    }
    /**
     * Returns a description of the command.
     *
     * @return a description of the command
     */
    @Override
    public String desc() {
        return "save the collection to file";
    }
    /**
     * Returns the argument limitations for the command.
     *
     * @return the argument limitations for the command
     */
    @Override
    public Limitations argLimitations() {
        return new Limitations();
    }
}
