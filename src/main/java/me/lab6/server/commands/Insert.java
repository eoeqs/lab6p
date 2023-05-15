package commands;

import exceptions.ExitException;
import managers.CollectionManager;
import managers.UserInteractionManager;
import utilities.DataLimitations;
import utilities.DataType;

/**
 * A command to add a new element to the collection using the given key.
 * If the collection already contains an element with the given key, the operation is not performed.
 * The user is prompted to input the values for the new element.
 * Empty input results in assigning a field to null where it's possible.
 * If the user decides to cancel the inserting process, they can use the '!e' command.
 * The key argument should be a long value.
 */
public class Insert implements Command {
    CollectionManager collectionManager;

    /**
     * Constructs a new Insert command with the given Collection Manager.
     *
     * @param collectionManager the Collection Manager used to manipulate the collection
     */
    public Insert(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the Insert command, adding a new element to the collection using the given key.
     * If the collection already contains an element with the given key, the operation is not performed.
     * The user is prompted to input the values for the new element.
     * Empty input results in assigning a field to null where it's possible.
     * If the user decides to cancel the inserting process, they can use the '!e' command.
     *
     * @param arg the key argument for the new element to be added to the collection
     * @throws NumberFormatException if the key argument is not a proper long value
     */
    @Override
    public void execute(String arg) {
        long key = Long.parseLong(arg);
        if (collectionManager.getWorkerMap().containsKey(key)) {
            System.out.println("The collection already contains an element with key = " + key + ".\n");
        } else {
            if (UserInteractionManager.getMode()) {
                System.out.println("To cancel the inserting process, use '!e'.");
                System.out.println("Empty input will result in assigning a field to null where it's possible.");
            }
            try {
                collectionManager.getWorkerMap().put(key, UserInteractionManager.createNewWorker(key));
                System.out.println("\nThe following worker was inserted into the collection under the key = " + key + ":");
                System.out.println(collectionManager.getWorkerMap().get(key) + "\n");
            } catch (ExitException e) {
                System.out.println("The inserting process was cancelled.\n");
            }
        }
    }

    /**
     * Returns the name of the command.
     *
     * @return the name of the command
     */
    @Override
    public String name() {
        return "insert";
    }

    /**
     * Returns the argument syntax for the command to be used in the Help command.
     *
     * @return the argument syntax for the command to be used in the Help command
     */
    @Override
    public String argDesc() {
        return "{key(long value)}";
    }

    /**
     * Returns the argument syntax for the command to be used in the Help command.
     *
     * @return the argument syntax for the command to be used in the Help command
     */
    @Override
    public String desc() {
        return "add a new element to the collection using the given key";
    }

    /**
     * Returns the data limitations for the command argument to be used in the UI Manager.
     *
     * @return the data limitations for the command argument to be used in the UI Manager
     */
    @Override
    public Object[] argLimitations() {
        return new DataLimitations(DataType.LONG,
                UserInteractionManager.wrongArgMessage(this) + "Please, use a proper long value.",
                UserInteractionManager.noArgMessage(this)).limitations();
    }
}
