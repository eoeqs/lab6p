package me.lab6.client;


import me.lab6.client.io.Console;
import me.lab6.client.network.UDPClient;

import java.net.InetAddress;

public class ClientMain {
    private static final int PORT = 26566;
    public static void main(String[] args) {
        try {
            var client = new UDPClient(InetAddress.getLocalHost(), PORT);
            Console console = new Console(client);

            console.interact();
        } catch (Exception e) {

            System.out.println("fuck");
        }
    }
}
