package me.lab6.server.commands;

import me.lab6.common.Response;
import me.lab6.common.utility.Limitations;
import me.lab6.server.managers.CollectionManager;


public class Info extends AbstractCommand {
    CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        super("info", "Returns info about collection.");
        this.collectionManager=collectionManager;
    }

    @Override
    public Response execute(String arg) {
        return new Response(String.format("InitializationDate : %s, type : %s, element count : %d", collectionManager.getCreationDate(), " stores Workers.",collectionManager.getWorkerMap().size()));

    }

    @Override
    public Limitations argLimitations() {
        return new Limitations();
    }
}
