package me.lab6.client.clientIO;

public class Messages {

    public static String hello() {
        return """
                ------------------------------------------------------------------------
                 Welcome to WorkerManager CS Edition! Please, start typing in commands.
                       Use 'help' to see the information about available commands.
                ------------------------------------------------------------------------""";
    }

    public static String goodbye() {
        return "Ending session.\nGoodbye!";
    }

    public static String wrongType(String commandName, String requiredType) {
        return "Wrong argument type for command '" + commandName +
                "'. " + requiredType + " required.";
    }

    public static String noInput(String commandName, String requiredType) {
        return "Command '" + commandName + "' requires an argument (" + requiredType + ").";
    }

    public static String serverCommunicationError() {
        return "There was an error during the communication with server. Please, try again.";
    }
}
