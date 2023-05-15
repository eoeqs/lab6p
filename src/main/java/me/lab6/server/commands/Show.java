package me.lab6.server.commands;


import me.lab6.common.utility.Limitations;
import me.lab6.common.workerRelated.Worker;
import me.lab6.server.managers.CollectionManager;

/**
 * Command to print out all elements of the collection.
 * Implements the {@link Command} interface.
 */

public class Show implements Command {
    CollectionManager collectionManager;
    /**
     * Print out all elements of the collection.
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
    public void execute(String arg) {
        if (collectionManager.getWorkerMap().isEmpty()) {
            System.out.println("There is nothing to show.\n");
        } else {
            for (Worker worker : collectionManager.getWorkerMap().values()) {
                System.out.println(worker + "\n");
            }
        }
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
        return "";
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
    /**
     * Get the limitations for the argument of the command.
     *
     * @return the limitations of the argument as an Object array.
     */
    @Override
    public Limitations argLimitations() {
        return new Limitations();
    }
}
