package me.lab6.server.commands;


import me.lab6.common.Response;

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
    public Response execute(Object arg) {

        StringBuilder sb = new StringBuilder(name());
        sb.append(" - ").append(desc()).append("\n");
        for (Command command : commands) {
            sb.append(command.name());
            if (command.argDesc() != null) {
                sb.append(" ").append(command.argDesc());
            }
            sb.append(" - ").append(command.desc()).append("\n");
        }

        return new Response(sb.toString());
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
        return null;
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
