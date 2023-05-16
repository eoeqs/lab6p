package me.lab6.server.commands;
import me.lab6.common.Response;
import me.lab6.common.utility.Limitations;

public interface Command {

    Response execute(String arg);

    String getName();

    String getDesc();

    Limitations argLimitations();
}
