package me.lab6.client.clientIO;


import me.lab6.client.network.UDPClient;
import me.lab6.common.Request;
import me.lab6.common.Response;
import me.lab6.common.utility.Validator;
import me.lab6.common.workerRelated.Organization;
import me.lab6.common.workerRelated.Worker;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Console {
    private final Scanner scanner = new Scanner(System.in);
    private static boolean mode;
    private final UDPClient client;
    private final EntityConstructor constructor;

    public Console(UDPClient client) {
        this.client = client;
        constructor = new EntityConstructor(this);
    }

    public void interact() {
        System.out.println(Messages.hello());
        while (true) {
            System.out.print("> ");
            try {
                handleInput();
            } catch (NoSuchElementException e) {
                System.exit(0);
            }
        }
    }

    public String getInput() throws NoSuchElementException {
        return scanner.nextLine().trim();
    }

    private void handleInput() throws NoSuchElementException {
        String inputStr = getInput();
        if (!inputStr.isBlank()) {
            String[] input = inputStr.split("\\s+", 2);
            input[0] = input[0].toLowerCase();
            String validationResult = Validator.validateCommandAndArg(input);
            if (validationResult == null) {
                if (input[0].equals("insert") || input[0].equals("replace_if_lower") || input[0].equals("update")) {
                    Worker worker = constructor.constructWorker(Long.parseLong(input[1]));
                    try {
                        System.out.println(getResponseForRequest(input[0], worker));
                    } catch (IOException e) {
                        System.out.println(Messages.serverCommunicationError());
                    }
                } else if (input[0].equals("filter_greater_than_organization")) {
                    Organization organization = constructor.constructOrganization();
                    try {
                        System.out.println(getResponseForRequest(input[0], organization));
                    } catch (IOException e) {
                        System.out.println(Messages.serverCommunicationError());
                    }
                } else {
                    if (input.length > 1) {
                        try {
                            System.out.println(getResponseForRequest(input));
                        } catch (IOException e) {
                            System.out.println(Messages.serverCommunicationError());
                        }
                    } else {
                        try {
                            System.out.println(getResponseForRequest(input[0]));
                        } catch (IOException e) {
                            System.out.println(Messages.serverCommunicationError());
                        }
                    }
                }
            } else {
                System.out.println(validationResult);
            }
        }
    }

    private Response getResponseForRequest(String command) throws IOException {
        return client.communicateWithServer(new Request(command, null));
    }

    private Response getResponseForRequest(String command, Object argument) throws IOException {
        return client.communicateWithServer(new Request(command, argument));
    }

    private Response getResponseForRequest(String[] input) throws IOException {
        return client.communicateWithServer(new Request(input[0], input[1]));
    }

}
