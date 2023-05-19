package me.lab6.server.commands;


import me.lab6.common.network.Response;
import me.lab6.common.workerRelated.*;

import java.time.LocalDate;
import java.util.*;

/**
 * Executes commands in the given script.
 */
public class ExecuteScript implements Command {

    private final Map<String, Command> commands;

    public ExecuteScript(Map<String, Command> commands) {
        this.commands = commands;
        this.commands.put("execute_script", this);
    }

    @Override
    public Response execute(Object arg) {
        String script = (String) arg;
        Iterator<String> iter = Arrays.asList(script.split("\n")).iterator();
        StringBuilder sb = new StringBuilder("Executing script.\n");
        while (iter.hasNext()) {
            Response response = emulateExecution(iter);
            if (response.message() != null) {
                sb.append(response).append("\n");
            }
        }
        return new Response(sb.append("Script finished execution.\n").toString());
    }

    private Response emulateExecution(Iterator<String> iter) {
        String currentString = iter.next();
        if (currentString.isBlank()) {
            return new Response(null);
        }
        String[] words = currentString.split("\\s+", 2);
        if (words[0].equalsIgnoreCase("insert") || words[0].equalsIgnoreCase("update")
                || words[0].equalsIgnoreCase("replace_if_lower")) {
            Worker worker = buildWorker(iter, Long.parseLong(words[1]));
            return commands.get(words[0]).execute(worker);
        } else if (words[0].equalsIgnoreCase("filter_greater_than_organization")) {
            Organization organization = buildOrganization(iter);
            return commands.get(words[0]).execute(organization);
        } else {
            return commands.get(words[0]).execute(words[1]);
        }
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
