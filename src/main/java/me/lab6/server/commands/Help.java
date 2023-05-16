package me.lab6.server.commands;


import me.lab6.common.Response;
import me.lab6.common.exceptions.ArgMustBeEmptyException;

import java.util.ArrayList;

/**
 * Represents the Help command that prints out the list of available commands with their descriptions.
 * Implements the {@link Command} interface.
 */
public class Help implements Command {
    ArrayList<Command> commands;

    /**
     * Constructs a new Help command with the given list of commands.
     *
     * @param commands the list of commands to be displayed.
     */

    public Help(ArrayList<Command> commands) {
        this.commands = commands;
    }

    /**
     * Prints out the list of available commands with their descriptions.
     *
     * @param arg the argument for the command (not used in this case).
     */
    @Override
    public Response execute(String arg) {

        StringBuilder s = new StringBuilder();
        for (Command c : commands) {
            if (c.argDesc().isEmpty()) {
                s.append(c.name()).append(" ").append(c.desc()).append("\n");
            } else {
                s.append(c.name()).append(" ").append(c.argDesc()).append(c.desc()).append("\n");
            }
        }
        return new Response(s.toString());
    }


    /**
     * Returns the name of the command.
     *
     * @return the name of the command.
     */
    @Override
    public String name() {
        return "help";
    }

    /**
     * Returns the argument description for the command.
     *
     * @return an empty string, as the command does not require an argument.
     */
    @Override
    public String argDesc() {
        return "";
    }

    /**
     * Returns the description of the command.
     *
     * @return the description of the command.
     */
    @Override
    public String desc() {
        return "print out the list of available commands";
    }
}
