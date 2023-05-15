package me.lab6.client.clientIO;


import me.lab6.client.network.UDPClient;
import me.lab6.common.Request;
import me.lab6.common.utility.Validator;

import java.util.NoSuchElementException;
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
            System.out.println(Messages.hello());
        }
        while (true) {
            if (mode) {
                System.out.print("> ");
            }
            try {
                handleInput();
            } catch (NoSuchElementException e) {
                System.exit(0);
            }
        }
    }

    private String getInput() throws NoSuchElementException {
        return scanner.nextLine().trim();
    }

    private void handleInput() throws NoSuchElementException {
        String inputStr = getInput();
        if (!inputStr.isBlank()) {
            String[] input = (scanner.nextLine().trim() + " ").split("\\s+", 2);
            input[0] = input[0].toLowerCase();
            if (Validator.validateCommandAndArg(input)) {
                sendMessage(input);
            }
        }
    }

    public void sendMessage(String[] input) {

            client.sendMessage(new Request(input[0], input[1]));

    }

}
