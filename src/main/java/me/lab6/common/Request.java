package me.lab6.common;

import java.io.Serializable;

public class Request implements Serializable {
    private String command;
    private Object something;
    public Request(String command, Object something){
        this.command = command;
        this.something = something;
    }

    public String getCommand() {
        return command;
    }
    public Object getSomething(){
        return something;
    }
}
