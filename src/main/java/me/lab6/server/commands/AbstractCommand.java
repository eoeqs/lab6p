package me.lab6.server.commands;

public abstract class AbstractCommand implements Command{
    private final String name;
    private final String description;
    public AbstractCommand(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return description;
    }
}
