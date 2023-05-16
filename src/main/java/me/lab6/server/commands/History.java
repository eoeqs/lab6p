package me.lab6.server.commands;


import me.lab6.common.Response;

import java.util.ArrayList;

/**
 * The {@code History} class implements the {@code Command} interface
 * and represents the command for printing out the 6 last executed commands.
 */
public class History implements Command {
    ArrayList<String> history;

    /**
     * Constructs a new {@code History} object with the specified history of commands.
     *
     * @param history the history of commands to be used
     */
    public History(ArrayList<String> history) {
        this.history = history;
    }

    /**
     * Executes the command by printing out the last 6 executed commands.
     *
     * @param arg the command argument (not used in this command)
     */
    @Override
    public Response execute(String arg) {

        StringBuilder s = new StringBuilder();
        int historyLength = history.size();
        if (historyLength == 0) {
            return new Response("History is yet empty.\n");
        }
        for (String i : history) {
            s.append(i).append("\n");
        }
        return new Response(s.toString());
    }

    /**
     * Returns the name of the command.
     *
     * @return the name of the command
     */
    @Override
    public String name() {
        return "history";
    }

    /**
     * Returns the argument of the command for help information (not used in this command).
     *
     * @return an empty string
     */
    @Override
    public String argDesc() {
        return "";
    }

    /**
     * Returns the description of the command for help information.
     *
     * @return the description of the command for help information
     */
    @Override
    public String desc() {
        return "print out 6 last executed commands";
    }

}


