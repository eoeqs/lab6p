package me.lab6.server.commands;


import me.lab6.common.Response;
import me.lab6.common.exceptions.ArgMustBeEmptyException;

import me.lab6.server.managers.CollectionManager;

public class Clear extends AbstractCommand {
    CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "clears the collection.");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(String arg) {
        try {
            if (!arg.isEmpty()) throw new ArgMustBeEmptyException();
            if (collectionManager.getWorkerMap().isEmpty()) {
                return new Response("This collection is already empty.\n");
            }
            collectionManager.getWorkerMap().clear();
            return new Response("Collection has been cleared.\n");
        } catch (ArgMustBeEmptyException e) {
            return new Response("Command argument must be empty.");
        }
    }


}