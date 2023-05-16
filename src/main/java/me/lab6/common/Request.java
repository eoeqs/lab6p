package me.lab6.common;

import java.io.Serializable;

public record Request(String command, Object argument) implements Serializable {

    @Override
    public String toString() {
        return "Command: '" + command + "'; arg: " + argument.toString();
    }

}
