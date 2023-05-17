package me.lab6.server;

import me.lab6.common.utility.Messages;
import me.lab6.server.managers.CommandManager;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ServerConsole {
    private final Scanner scanner;
    private final CommandManager commandManager;
    public ServerConsole(Scanner scanner, CommandManager commandManager){
        this.scanner = scanner;
        this.commandManager = commandManager;
    }
    public boolean handleServerInput() {
        try {
            if (System.in.available() > 0) {
                String input = scanner.nextLine().trim();
                switch (input) {
                    case "save" -> {
                        try {
                            commandManager.save();
                            System.out.println(Messages.saved());
                            return false;
                        } catch (Exception e) {
                            System.out.println(Messages.failedToSave());
                            return false;
                        }
                    }
                    case "exit" -> {
                        return exit();
                    }
                    default -> {
                        System.out.println("You can only use 'save' and 'exit' in the Server console.");
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            return false;
        } catch (NoSuchElementException e) {
            return exit();
        }
        return false;
    }

    private boolean exit() {
        try {
            commandManager.save();
            System.out.println(Messages.saved());
            System.out.println(Messages.serverGoodbye());
            return true;
        } catch (Exception e) {
            System.out.println(Messages.failedToSave());
            System.out.println("Exit anyway? (Enter anything to exit, press Enter without typing anything to cancel)");
            try {
                if (!scanner.nextLine().isEmpty()) {
                    System.out.println(Messages.serverGoodbye());
                    return true;
                }
            } catch (NoSuchElementException n) {
                System.out.println(Messages.serverGoodbye());
                return true;
            }
        }
        return false;
    }
}
