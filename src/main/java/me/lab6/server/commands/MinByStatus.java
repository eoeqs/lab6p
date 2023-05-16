package me.lab6.server.commands;


import me.lab6.common.Response;
import me.lab6.common.utility.Limitations;
import me.lab6.common.workerRelated.Status;
import me.lab6.common.workerRelated.Worker;
import me.lab6.server.managers.CollectionManager;

public class MinByStatus extends AbstractCommand {
    CollectionManager collectionManager;


    public MinByStatus(CollectionManager collectionManager) {
        super("min_by_status", "prints out all elements with the lowest status value");
        this.collectionManager = collectionManager;
    }


    @Override
    public Response execute(String arg) {
        int count = 0;
        StringBuilder s = new StringBuilder();
        for (Worker w : collectionManager.getWorkerMap().values()) {
            if (w.getStatus() == Status.FIRED) {
                s.append(w).append("\n");
                count++;
            }
        }
        if (count == 0) {
            return new Response("The collection doesn't contain elements with the minimal status value.\n");
        } else {
            return new Response(s.toString());
        }
    }

    @Override
    public Limitations argLimitations() {
        return new Limitations();
    }
}
