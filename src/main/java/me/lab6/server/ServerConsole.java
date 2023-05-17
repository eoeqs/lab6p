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
    public void handleServerInput() {
        try {
            if (System.in.available() > 0) {
                String input = scanner.nextLine().trim();
                switch (input) {
                    case "save" -> {
                        try {
                            commandManager.save();
                            System.out.println(Messages.saved());
                        } catch (Exception e) {
                            System.out.println(Messages.failedToSave());
                        }
                    }
                    case "exit" ->  exit();
                    default -> System.out.println("You can only use 'save' and 'exit' in the Server console.");
                }
            }
        } catch (IOException ignored) {
        } catch (NoSuchElementException e) {
            exit();
        }
    }

    private void exit() {
        try {
            commandManager.save();
            System.out.println(Messages.saved());
            System.out.println(Messages.serverGoodbye());
            System.exit(0);
        } catch (Exception e) {
            System.out.println(Messages.failedToSave());
            System.out.println("Exit anyway? (Enter anything to exit, press Enter without typing anything to cancel)");
            try {
                if (!scanner.nextLine().isEmpty()) {
                    System.out.println(Messages.serverGoodbye());
                    System.exit(0);
                }
            } catch (NoSuchElementException n) {
                System.out.println(Messages.serverGoodbye());
                System.exit(0);
            }
        }
    }
}
