//package me.lab6.server.commands;
//
//import me.lab6.common.utility.DataLimitations;
//import me.lab6.common.utility.DataType;
//import me.lab6.server.managers.CollectionManager;
//import me.lab6.server.managers.UserInteractionManager;
//
///**
// * A command that removes all elements from the collection with the key less than the specified one.
// * Implements the {@link Command} interface.
// */
//public class RemoveLowerKey implements Command {
//    CollectionManager collectionManager;
//    /**
//     * Constructs a new instance of the RemoveLowerKey command with the given Collection Manager.
//     * @param collectionManager the Collection Manager
//     */
//    public RemoveLowerKey(CollectionManager collectionManager) {
//        this.collectionManager = collectionManager;
//    }
//    /**
//     * Executes the RemoveLowerKey command. Removes all elements from the collection with the key less than the specified one.
//     *
//     * @param arg the argument of the command (key of the element to be removed)
//     */
//    @Override
//    public void execute(String arg) {
//        if (collectionManager.getWorkerMap().isEmpty()) {
//            System.out.println("This collection is empty.\n");
//            return;
//        }
//        Long key = Long.parseLong(arg);
//        int count = collectionManager.getWorkerMap().size();
//        collectionManager.getWorkerMap().entrySet().removeIf(entry -> entry.getKey() < key);
//        count -= collectionManager.getWorkerMap().size();
//        System.out.println(count + " elements were removed from the collection.\n");
//    }
//    /**
//     * Returns the name of the command.
//     * @return the name of the command
//     */
//    @Override
//    public String name() {
//        return "remove_lower_key";
//    }
//    /**
//     * Returns the argument of the command needed for correct usage in the console.
//     * @return the argument of the command
//     */
//    @Override
//    public String argDesc() {
//        return "{key(long value)}";
//    }
//    /**
//     * Returns the description of the command for displaying help information.
//     * @return the description of the command
//     */
//    @Override
//    public String desc() {
//        return "remove all elements with the key lower than given from the collection";
//    }
//    /**
//     * Returns the limitations of the argument of the command.
//     * @return the limitations of the argument of the command
//     */
//    @Override
//    public Object[] argLimitations() {
//        return new DataLimitations(DataType.LONG,
//                UserInteractionManager.wrongArgMessage(this) + "Please, use a proper long value.",
//                UserInteractionManager.noArgMessage(this)).limitations();
//    }
//}
