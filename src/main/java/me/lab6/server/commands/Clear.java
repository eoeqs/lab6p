package me.lab6.server.commands;


import me.lab6.common.Response;
import me.lab6.common.utility.Limitations;
import me.lab6.server.managers.CollectionManager;

/**
 * The Clear class represents a command that clears the WorkerMap collection.
 * Implements the {@link Command} interface.
 */
public class Clear implements Command {
    CollectionManager collectionManager;

    /**
     * Constructs a Clear object with a specified ColMan object.
     *
     * @param collectionManager the ColMan object used to manage the collection
     */
    public Clear(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the clear command by clearing the WorkerMap collection.
     * If the collection is already empty, a message is printed to indicate so.
     *
     * @return
     */
    @Override
    public Response execute(Object arg) {
        if (collectionManager.getWorkerMap().isEmpty()) {
            System.out.println("This collection is already empty.\n");
            return;
        }
        collectionManager.getWorkerMap().clear();
        System.out.println("Collection has been cleared.\n");
    }

    /**
     * @return the name of this command
     */
    @Override
    public String name() {
        return "clear";
    }

    /**
     * @return the argument string for the command
     */
    @Override
    public String argDesc() {
        return "";
    }

    /**
     * @return a description of this command
     */
    @Override
    public String desc() {
        return "clear the collection";
    }

    /**
     * @return an array of DataLimitations objects
     */
    @Override
    public Limitations argLimitations() {
        return new Limitations();
    }
}