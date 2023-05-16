//package me.lab6.server.commands;
//
//
//import me.lab6.common.exceptions.ExitException;
//import me.lab6.common.utility.DataLimitations;
//import me.lab6.common.utility.DataType;
//import me.lab6.common.workerRelated.Worker;
//import me.lab6.server.managers.CollectionManager;
//import me.lab6.server.managers.UserInteractionManager;
//
///**
// * The {@code Update} class represents the update command used to replace the worker with the given key in the collection
// * with a newly described worker.
// * Implements the {@link Command} interface.
// */
//public class Update extends AbstractCommand{
//
//    CollectionManager collectionManager;
//
//    /**
//     * Constructs a new {@code Update} command with the given collection manager.
//     *
//     * @param collectionManager the collection manager to be used
//     */
//
//    public Update(CollectionManager collectionManager) {
//        this.collectionManager = collectionManager;
//    }
//
//    /**
//     * Replaces the worker with the given key in the collection with a newly described worker.
//     *
//     * @param arg the argument for the command
//     */
//    @Override
//    public void execute(String arg) {
//        long key = Long.parseLong(arg);
//        if (!collectionManager.getWorkerMap().containsKey(key)) {
//            System.out.println("The collection doesn't contain an element with key = " + key + ".\n");
//        } else {
//            if (UserInteractionManager.getMode()) {
//                System.out.println("To cancel the worker description process, use '!e'.");
//                System.out.println("To keep the old value of any field, use '!s'.");
//                System.out.println("Empty input will result in assigning a field to null where it's possible.");
//            }
//            try {
//                Worker worker = UserInteractionManager.createNewWorker(key);
//                collectionManager.replace(worker);
//                System.out.println();
//            } catch (ExitException e) {
//                System.out.println("The updating process was cancelled.\n");
//            }
//        }
//    }
//
//    /**
//     * Returns the name of the command.
//     *
//     * @return the name of the command
//     */
//    @Override
//    public String name() {
//        return "update";
//    }
//
//    /**
//     * Returns the argument format for the command.
//     *
//     * @return the argument format for the command
//     */
//    @Override
//    public String argDesc() {
//        return "{id(long value)}";
//    }
//
//    /**
//     * Returns the description of the command.
//     *
//     * @return the description of the command
//     */
//    @Override
//    public String desc() {
//        return "update an element with the given id field value";
//    }
//
//    /**
//     * @return an array of DataLimitations objects
//     */
//    @Override
//    public Object[] argLimitations() {
//        return new DataLimitations(DataType.LONG,
//                UserInteractionManager.wrongArgMessage(this) + "Please, use a proper long value.",
//                UserInteractionManager.noArgMessage(this)).limitations();
//    }
//}
