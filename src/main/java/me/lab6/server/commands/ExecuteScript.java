package me.lab6.server.commands;


import me.lab6.common.network.Response;
import me.lab6.common.workerRelated.*;
import me.lab6.server.managers.CommandManager;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Executes commands in the given script.
 */
public class ExecuteScript implements Command {

    private final CommandManager commandManager;

    public ExecuteScript(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public Response execute(Object arg) {
        String script = (String) arg;
        Iterator<String> iter = Arrays.asList(script.split("\n")).iterator();
        StringBuilder sb = new StringBuilder();
        while (iter.hasNext()) {
            Response response;
            String currentString = iter.next();
            if (currentString.isBlank()) {
                continue;
            }
            String[] words = iter.next().split("\\s+", 2);
            if (words[0].equalsIgnoreCase("insert") || words[0].equalsIgnoreCase("update")
                    || words[0].equalsIgnoreCase("replace_if_lower")) {
                response = commandManager.executeCommand(words[0], buildWorker(iter, Long.parseLong(words[1])));
            } else if (words[0].equalsIgnoreCase("filter_greater_than_organization")) {
                response = commandManager.executeCommand(words[0], buildOrganization(iter));
            } else {
                response = commandManager.executeCommand(words[0], words[1]);
            }
            sb.append(response.toString()).append("\n");
        }
        return new Response(sb.toString());
    }

    private Worker buildWorker(Iterator<String> iter, long key) {
        String name = iter.next();
        String xStr = iter.next();
        double x = xStr.isBlank() ? 0 : Double.parseDouble(xStr);
        String yStr = iter.next();
        Double y = yStr.isBlank() ? 0D : Double.parseDouble(yStr);
        int salary = Integer.parseInt(iter.next());
        LocalDate startDate = LocalDate.parse(iter.next());
        String posStr = iter.next();
        Position pos = posStr.isBlank() ? null : Position.valueOf(posStr.toUpperCase());
        String statusStr = iter.next();
        Status status = statusStr.isBlank() ? null : Status.valueOf(statusStr.toUpperCase());
        Organization org = null;
        if (!iter.next().isBlank()) {
            org = buildOrganization(iter);
        }
        return new Worker(key, name, new Coordinates(x, y), LocalDate.now(), salary, startDate, pos, status, org);
    }

    private Organization buildOrganization(Iterator<String> iter) {
        String name = iter.next();
        int turnover = Integer.parseInt(iter.next());
        long empCount = Integer.parseInt(iter.next());
        String street = iter.next();
        street = street.isBlank() ? null : street;
        String zipCode = iter.next();
        return new Organization(name, turnover, empCount, new Address(street, zipCode));
    }

    @Override
    public String name() {
        return "execute_script";
    }

    @Override
    public String argDesc() {
        return "{file_path}";
    }

    @Override
    public String desc() {
        return "execute the sequence of commands from a file";
    }
}
