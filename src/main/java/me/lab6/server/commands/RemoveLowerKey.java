package me.lab6.server.commands;

import me.lab6.common.Response;
import me.lab6.common.utility.Limitations;
import me.lab6.server.managers.CollectionManager;

public class RemoveLowerKey extends AbstractCommand {
    CollectionManager collectionManager;
    public RemoveLowerKey(CollectionManager collectionManager) {
        super("remove_lower_key", "removes all elements with the key lower than given from the collection\"");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String arg) {
        if (collectionManager.getWorkerMap().isEmpty()) {
            return new Response("This collection is empty.\n");

        }
        Long key = Long.parseLong(arg);
        int count = collectionManager.getWorkerMap().size();
        collectionManager.getWorkerMap().entrySet().removeIf(entry -> entry.getKey() < key);
        count -= collectionManager.getWorkerMap().size();
        return new Response(count + " elements were removed from the collection.\n");

    }
    @Override
    public Limitations argLimitations() {
        return null;
    }

}
