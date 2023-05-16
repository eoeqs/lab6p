package me.lab6.server.commands;


import me.lab6.common.Response;
import me.lab6.common.workerRelated.Worker;
import me.lab6.server.managers.CollectionManager;

/**
 * Command to remove an element with the given key from the collection.
 * Implements the {@link Command} interface.
 */
public class RemoveKey implements Command {
    private final CollectionManager collectionManager;

    /**
     * Constructs a RemoveKey command object with the given ColMan object.
     *
     * @param collectionManager the ColMan object to operate on
     */
    public RemoveKey(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the RemoveKey command by removing the element with the given key from the collection.
     *
     * @param arg the key of the element to remove from the collection
     */
    @Override
    public Response execute(String arg) {
        long key = Long.parseLong(arg);
        for (Worker worker : collectionManager.getWorkerMap().values()) {
            if (worker.getId() == key) {
                collectionManager.getWorkerMap().values().remove(worker);
                return new Response("Collection element with key " + key + " has been successfully deleted.\n");
            }
        }
        return new Response("The collection doesn't contain an element with key = " + key + ".\n");
    }

    /**
     * Returns the name of the RemoveKey command.
     *
     * @return the name of the command
     */
    @Override
    public String name() {
        return "remove_key";
    }

    /**
     * Returns the argument syntax of the RemoveKey command.
     *
     * @return the argument syntax of the command
     */
    @Override
    public String argDesc() {
        return "{key(long value)}";
    }

    /**
     * Returns the description of the RemoveKey command.
     *
     * @return the description of the command
     */
    @Override
    public String desc() {
        return "delete an element with the given key from the collection";
    }

}
