package me.lab6.server.commands;

import me.lab6.common.Response;
import me.lab6.common.utility.DataType;
import me.lab6.common.workerRelated.Position;
import me.lab6.common.workerRelated.Worker;
import me.lab6.server.commands.Command;
import me.lab6.server.managers.CollectionManager;

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
    public Response execute(String arg) {
        Long key = Long.parseLong(arg);
        Position position = Position.valueOf(String.valueOf(key));
        int count = 0;
        for (Worker w : collectionManager.getWorkerMap().values()) {
            if (w.getPosition() == position) {
                count++;
            }
        }
        if (count == 0) {
            return new Response("The collection doesn't contain elements with such position value.");
        } else {
            return new Response("The collection contains " + count + " element(s) with position = " + position + ".");
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


}
