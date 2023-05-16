package me.lab6.server.commands;

import me.lab6.common.Response;
import me.lab6.common.exceptions.ArgMustBeEmptyException;
import me.lab6.common.utility.Limitations;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class History extends AbstractCommand {
    ArrayList<String> history;

    public History(ArrayList<String> history) {
        super("history", "prints out last 6 commands.");
        this.history = history;
    }

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

    @Override
    public Limitations argLimitations() {
        return null;
    }

}


