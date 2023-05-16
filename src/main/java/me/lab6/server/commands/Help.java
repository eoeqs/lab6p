package me.lab6.server.commands;

import me.lab6.common.Response;
import me.lab6.common.exceptions.ArgMustBeEmptyException;
import me.lab6.common.utility.Limitations;

import java.util.ArrayList;

public class Help extends AbstractCommand {
    ArrayList<Command> commands;

    public Help(ArrayList<Command> commands) {
        super("help", "returns a list of commands.");
        this.commands = commands;
    }

    @Override
    public Response execute(String arg) {
        try {
            StringBuilder s = new StringBuilder();
            if (arg.isEmpty()) throw new ArgMustBeEmptyException();
            for (Command c : commands) {
                s.append(c.getName()).append(" ").append(c.getDesc()).append("\n");
            }

            return new Response(s.toString());
        } catch (ArgMustBeEmptyException e) {
            return new Response("Command argument must be empty.");
        }
    }
}

