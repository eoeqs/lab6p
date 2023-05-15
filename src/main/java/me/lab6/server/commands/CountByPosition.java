package me.lab6.server.commands;

import managers.CollectionManager;
import managers.UserInteractionManager;
import utilities.DataLimitations;
import utilities.DataType;
import workerRelated.Position;
import workerRelated.Worker;

/**
 * CountByPosition class represents a command that counts the number of
 * elements in the collection with a given Position value.
 Implements the {@link Command} interface.
 */
public class CountByPosition implements Command {
    CollectionManager collectionManager;

    /**
     * Constructs a new CountByPosition command with the given ColMan object.
     *
     * @param collectionManager the ColMan object to use for the command
     */
    public CountByPosition(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command with the given argument.
     *
     * @param arg the Position value to count
     */
    @Override
    public void execute(String arg) {
        Position position = Position.valueOf(arg.toUpperCase());
        int count = 0;
        for (Worker w : collectionManager.getWorkerMap().values()) {
            if (w.getPosition() == position) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println("The collection doesn't contain elements with such position value.\n");
        } else {
            System.out.println("The collection contains " + count + " element(s) with position = " + position + ".\n");
        }
    }

    /**
     * Returns the name of the command.
     *
     * @return the name of the command
     */
    @Override
    public String name() {
        return "count_by_position";
    }

    /**
     * Returns the argument string for use in a help message.
     *
     * @return the argument string for the command
     */
    @Override
    public String argDesc() {
        return "{position(head_of_department, developer, manager_of_cleaning)}";
    }

    /**
     * Returns the description of the command for use in a help message.
     *
     * @return the description of the command
     */
    @Override
    public String desc() {
        return "print out the number of elements with Position field value equal to given";
    }

    /**
     * Returns an array of DataLimitations objects that represent the limitations
     * on the arguments that this command can take.
     *
     * @return an array of DataLimitations objects
     */
    @Override
    public Object[] argLimitations() {
        return new DataLimitations(DataType.POSITION,
                UserInteractionManager.wrongArgMessage(this) +
                        "Please, use a proper Position value (" + Position.allPositions() + ").",
                UserInteractionManager.noArgMessage(this)).limitations();
    }
}
