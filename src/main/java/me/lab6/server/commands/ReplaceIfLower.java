//package me.lab6.server.commands;
//
//
//import me.lab6.common.Response;
//import me.lab6.common.exceptions.ExitException;
//
//import me.lab6.common.utility.Limitations;
//import me.lab6.common.workerRelated.Worker;
//import me.lab6.server.managers.CollectionManager;
//
//public class ReplaceIfLower extends AbstractCommand {
//    CollectionManager collectionManager;
//
//    public ReplaceIfLower(CollectionManager collectionManager) {
//        super("replace_if_lower", "replaces an element with the given key if the newly described element is lower than the current.");
//        this.collectionManager = collectionManager;
//    }
//
//    @Override
//    public Response execute(String arg) {
//        long key = Long.parseLong(arg);
//        if (!collectionManager.getWorkerMap().containsKey(key)) {
//            return  new Response("The collection doesn't contain an element with key = " + key + ".\n");
//        } else {
////            if (UserInteractionManager.getMode()) {
////                System.out.println("To cancel the worker description process, use '!e'.");
////                System.out.println("Empty input will result in assigning a field to null where it's possible.");
////            }
//            try {
//                Worker worker = UserInteractionManager.createNewWorker(key);
//                if (collectionManager.getWorkerMap().get(key).compareTo(worker) < 0) {
//                    collectionManager.replace(worker);
//                    System.out.println();
//                } else {
//                    System.out.println("Current element under the key = " + key + " is equal to or greater than the given one.\n");
//                }
//            } catch (ExitException e) {
//                System.out.println("The updating process was cancelled.\n");
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Limitations argLimitations() {
////        return new DataLimitations(DataType.LONG,
////                UserInteractionManager.wrongArgMessage(this) + "Please, use a proper long value.",
////                UserInteractionManager.noArgMessage(this)).limitations();
////    }
//        return null;
//    }
//}
