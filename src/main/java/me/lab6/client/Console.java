package me.lab6.client;


import me.lab6.common.Request;

import java.io.IOException;
import java.util.Scanner;

public class Console {
    private final Scanner scanner = new Scanner(System.in);
    private static boolean mode;
    private UDPClient client;

    public Console(UDPClient client) {
        this.client = client;
    }

    public void interact() {
        if (mode) {
            System.out.println(startMessage());
        }
        while (true) {
            if (mode) {
                System.out.print("> ");
            }
            handleInput();
        }
    }


    private void handleInput() {
        String[] input = {"", ""};
        input = (scanner.nextLine().trim() + " ").split("\s", 2);
        if (ValidationChecker.checker(input)) sendMessage(input);

    }

    public void sendMessage(String[] input) {

            client.sendMessage(new Request(input[0], input[1]));

    }


    private String startMessage() {
        return """
                -------------------------------------------------------------
                 Welcome to WorkerManager. Please, start typing in commands.
                 Use 'help' to see the information about available commands.
                -------------------------------------------------------------""";
    }
}
