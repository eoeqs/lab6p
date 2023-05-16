package me.lab6.server;

import me.lab6.common.exceptions.ExitException;
import me.lab6.server.managers.CommandManager;

import java.io.IOException;
import java.util.Scanner;

public class ServerConsole {
    private final Scanner scanner;
    private final CommandManager commandManager;
    public ServerConsole(Scanner scanner, CommandManager commandManager){
        this.scanner = scanner;
        this.commandManager = commandManager;
    }
    public void checkServerConsole() throws IOException {
        if (System.in.available() > 0) {
            String command = scanner.nextLine().trim();
            switch (command) {
                case "save":
                    commandManager.save();
                    break;
                case "exit":
                    commandManager.exit();
                default:
            }
        }
    }
}
