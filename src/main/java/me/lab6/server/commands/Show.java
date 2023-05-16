package me.lab6.server.commands;


import me.lab6.common.Response;
import me.lab6.common.utility.Limitations;
import me.lab6.common.workerRelated.Worker;
import me.lab6.server.managers.CollectionManager;

import java.util.stream.Collectors;


public class Show extends AbstractCommand {
    CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        super("show", "prints out the collection.");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String arg) {
        if (collectionManager.getWorkerMap().size() == 0) {
            return new Response("Collection is empty.");
        }
        return new Response(collectionManager.getWorkerMap().entrySet()
                .stream()
                .map(entry -> entry.getKey() + " -> " + entry.getValue())
                .collect(Collectors.joining("\n")));
    }

    @Override
    public Limitations argLimitations() {
        return new Limitations();
    }
}
