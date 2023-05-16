package me.lab6.server.commands;


import me.lab6.common.Response;
import me.lab6.common.exceptions.ArgMustBeEmptyException;
import me.lab6.common.utility.Limitations;
import me.lab6.server.managers.CollectionManager;


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
     */
    @Override
    public Response execute(String arg) {
        try {
            if (!arg.isEmpty()) throw new ArgMustBeEmptyException();
            if (collectionManager.getWorkerMap().isEmpty()) {
                return new Response("This collection is already empty.\n");
            }
            collectionManager.getWorkerMap().clear();
            return new Response("Collection has been cleared.\n");
        } catch (ArgMustBeEmptyException e) {
            return new Response("Command argument must be empty.");
        }
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


}