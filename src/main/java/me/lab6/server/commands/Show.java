package me.lab6.server.commands;


import me.lab6.common.Response;
import me.lab6.common.workerRelated.Worker;
import me.lab6.server.managers.CollectionManager;

import java.util.stream.Collectors;

/**
 * Command to print out all elements of the collection.
 * Implements the {@link Command} interface.
 */

public class Show implements Command {
    CollectionManager collectionManager;

    /**
     * Print out all elements of the collection.
     *
     * @param collectionManager the collection manager that contains the collection to be searched
     */
    public Show(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Get the name of the command.
     *
     * @param arg the argument of the command
     */
    @Override
    public Response execute(Object arg) {
        if (collectionManager.getWorkerMap().size() == 0) {
            return new Response("This collection is empty.");
        }
        StringBuilder sb = new StringBuilder();
        for (Worker w : collectionManager.getWorkerMap().values()) {
            sb.append(w).append("\n");
        }
        return new Response(sb.toString());
//        return new Response(collectionManager.getWorkerMap().entrySet()
//                .stream()
//                .map(entry -> entry.getKey() + " -> " + entry.getValue())
//                .collect(Collectors.joining("\n")));
    }

    /**
     * Get the name of the command.
     *
     * @return the name of the command as a string.
     */
    @Override
    public String name() {
        return "show";
    }

    /**
     * Get the argument string for the command to display in the help command.
     *
     * @return the argument string for the command.
     */
    @Override
    public String argDesc() {
        return null;
    }

    /**
     * Get the description of the command to display in the help command.
     *
     * @return the description of the command as a string.
     */
    @Override
    public String desc() {
        return "print out all elements of the collection";
    }


}
