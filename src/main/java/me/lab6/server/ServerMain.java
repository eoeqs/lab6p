package me.lab6.server;

import com.google.gson.JsonParseException;
import me.lab6.common.exceptions.*;
import me.lab6.common.workerRelated.Worker;
import me.lab6.server.managers.CollectionManager;
import me.lab6.server.managers.CommandManager;
import me.lab6.server.managers.FileManager;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class ServerMain {
    private final static int port = 26566;

    public static void main(String[] args) {
        String fileName = System.getenv("workers");
        if (fileName != null) {
            FileManager fileManager = new FileManager(fileName);
            try {
                if (!new File(fileName).createNewFile()) {
                    HashMap<Long, Worker> workerMap = fileManager.readWorkersFromFile();
                    prepareAndStart(fileManager, workerMap);
                } else {
                    prepareAndStart(fileManager, new HashMap<>());
                }
            } catch (IOException e) {
                System.out.println("Can't read the source file. Make sure that the environmental variable 'workers' stores a proper path to an existent file.");
            } catch (SameIDException e) {
                System.out.println("The source file contains two or more workers with the same ID. ID has to be unique.");
            } catch (JsonParseException e) {
                System.out.println("Failed to read the source file: content doesn't meet json standards.");
            } catch (IncorrectWorkerFieldException e) {
                System.out.println("The source file's worker representation is incorrect.");
            } catch (NullPointerException e) {
                prepareAndStart(fileManager, new HashMap<>());
            }
        } else {
            System.out.println("Can't read the source file. Environmental variable 'workers' is null.");
        }
    }

    private static void prepareAndStart(FileManager fileManager, HashMap<Long, Worker> workerMap) {
        CollectionManager collectionManager = new CollectionManager(workerMap);
        CommandManager commandManager = new CommandManager(collectionManager, fileManager);
        try {
            Scanner scanner = new Scanner(System.in);
            ServerConsole serverConsole = new ServerConsole(scanner, commandManager);
            UDPServer server = new UDPServer(InetAddress.getLocalHost(), port, commandManager, serverConsole);
            server.run();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
