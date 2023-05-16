package me.lab6.server.commands;

import me.lab6.common.Response;
import me.lab6.common.utility.Limitations;
import me.lab6.common.workerRelated.Worker;
import me.lab6.server.managers.CollectionManager;

public class RemoveKey extends AbstractCommand{
    private final CollectionManager collectionManager;

    public RemoveKey(CollectionManager collectionManager) {
        super("remove_key", "removes collection element by its id");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String arg) {
        long key = Long.parseLong(arg);
        for (Worker worker : collectionManager.getWorkerMap().values()) {
            if (worker.getId() == key) {
                collectionManager.getWorkerMap().values().remove(worker);
                return new Response("Collection element with key " + key + " has been successfully deleted.\n");
            }
        }
        return new Response("The collection doesn't contain an element with key = " + key + ".\n");
    }

    @Override
    public Limitations argLimitations() {
//        return new DataLimitations(DataType.LONG,
//                UserInteractionManager.wrongArgMessage(this) + "Please, use a proper long value.",
//                UserInteractionManager.noArgMessage(this)).limitations();
        return null;
    }
}
