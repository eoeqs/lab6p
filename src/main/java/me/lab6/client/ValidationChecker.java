package me.lab6.client;

import java.util.HashSet;

public class ValidationChecker {
    private static final HashSet<String> commandWithoutArg = new HashSet<>();
    private static final HashSet<String> commandWithArg = new HashSet<>();

    static {
        commandWithoutArg.add("help");
        commandWithoutArg.add("info");
        commandWithoutArg.add("show");
        commandWithoutArg.add("history");
        commandWithoutArg.add("clear");
        commandWithoutArg.add("min_by_status");
//        commandWithoutArg.add("filter_greater_than_organization");
        commandWithArg.add("insert");
        commandWithArg.add("remove_key");
        commandWithArg.add("update");
        commandWithArg.add("remove_lower_key");
//        commandWithArg.add("count_by_position");
        commandWithArg.add("replace_if_lower");
//        commandWithArg.add("execute_script");
    }

    public static boolean checker(String[] input) {
        try {
            if (commandWithoutArg.contains(input[0])) {
                if (!input[1].isEmpty()) {
                    return false;
                }
                return true;
            } else if (commandWithArg.contains(input[0])) {
                if (input[1].isEmpty()) {

                    return false;
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;

    }
}
