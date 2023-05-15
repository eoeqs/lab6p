package me.lab6.client;


import java.net.InetAddress;
import java.util.Scanner;

public class Client {
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
