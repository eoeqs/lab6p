package commands;

import managers.CollectionManager;
import utilities.DataLimitations;

/**
 * The {@code Info} class represents a command to print out information about the collection.
 * It implements the {@code Command} interface.
 */
public class Info implements Command {
    CollectionManager collectionManager;
    /**
     * Constructs an {@code Info} object with the given collection manager.
     * @param collectionManager the collection manager
     */
    public Info(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    /**
     * Prints out information about the collection.
     * Prints out the collection's initialization date, the number of elements and that the collection stores Workers.
     * The collection keys are the same as the Workers' id's.
     *
     * @param arg the command argument (not used)
     */
    @Override
    public void execute(String arg) {
        System.out.println("Collection stores Workers.");
        System.out.println("Collection's initialization date is " + collectionManager.getCreationDate() + ".");
        System.out.println("The number of elements is " + collectionManager.getWorkerMap().size() + ".");
        System.out.println("The collection keys are the same as the Workers' id's.\n");
    }
    /**
     * Returns the command name.
     * @return the command name
     */
    @Override
    public String name() {
        return "info";
    }
    /**
     * Returns the command argument format.
     * The {@code Info} command doesn't require an argument, so it returns an empty string.
     * @return an empty string
     */
    @Override
    public String argDesc() {
        return "";
    }
    /**
     * Returns the command description for the help command.
     * @return the command description
     */
    @Override
    public String desc() {
        return "print out the information about the collection";
    }
    /**
     * Returns an empty array of object limitations.
     * The {@code Info} command doesn't have any limitations, so it returns an empty array.
     * @return an empty array
     */
    @Override
    public Object[] argLimitations() {
        return new DataLimitations().limitations();
    }
}
