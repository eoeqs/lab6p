package me.lab6.common.utility;

import me.lab6.client.clientIO.Messages;
import me.lab6.common.workerRelated.Position;
import me.lab6.common.workerRelated.Status;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Validator {

    private static final Map<String, Limitations> commandsWithoutArg = new HashMap<>();
    private static final Map<String, Limitations> commandsWithArg = new HashMap<>();

    static {
        commandsWithoutArg.put("help", new Limitations());
        commandsWithoutArg.put("info", new Limitations());
        commandsWithoutArg.put("show", new Limitations());
        commandsWithoutArg.put("history", new Limitations());
        commandsWithoutArg.put("clear", new Limitations());
        commandsWithoutArg.put("min_by_status", new Limitations());
        commandsWithoutArg.put("filter_greater_than_organization", new Limitations());
        commandsWithArg.put("insert", new Limitations(
                DataType.LONG, Messages.wrongType("insert", "A proper long value"),
                Messages.noInput("insert", "a long value")));
        commandsWithArg.put("remove_key", new Limitations(
                DataType.LONG, Messages.wrongType("remove_key", "A proper long value"),
                Messages.noInput("remove_key", "a long value")));
        commandsWithArg.put("update", new Limitations(
                DataType.LONG, Messages.wrongType("update", "A proper long value"),
                Messages.noInput("update", "a long value")));
        commandsWithArg.put("remove_lower_key", new Limitations(
                DataType.LONG, Messages.wrongType("remove_lower_key",
                "A proper long value"),
                Messages.noInput("remove_lower_key", "a long value")));
        commandsWithArg.put("count_by_position", new Limitations(
                DataType.POSITION, Messages.wrongType("count_by_position",
                "A proper Position value (" + Position.allPositions() + ")"),
                Messages.noInput("count_by_position", Position.allPositions())));
        commandsWithArg.put("replace_if_lower", new Limitations(
                DataType.LONG, Messages.wrongType("replace_if_lower", "A proper long value"),
                Messages.noInput("replace_if_lower", "a long value")));
        commandsWithArg.put("execute_script", new Limitations(DataType.FILEPATH,
                Messages.wrongType("execute_script", "A path to an existing file"),
                Messages.noInput("execute_script", "a file path")));
    }

    public static boolean validateCommandAndArg(String[] input) {
        String commandStr = input[0];
        int existence = validateCommand(commandStr);
        if (existence == 0) {
            System.out.println("Non-existent command. Please, use 'help' to see information about available commands.");
            return false;
        }
        if (existence > 0) {
            Limitations limitations = commandsWithArg.get(commandStr);
            if (input.length == 1) {
                System.out.println(limitations.getNoInputMessage());
                return false;
            }
            return (validateArg(input[1], limitations));
        }
        return true;
    }

    private static int validateCommand(String str) {
        if (commandsWithArg.containsKey(str)) {
            return 1;
        }
        if (commandsWithoutArg.containsKey(str)) {
            return -1;
        }
        return 0;
    }

    private static boolean validateArg(String argStr, Limitations limitations) {
        if (!validateData(argStr, limitations) || !positiveCheck(argStr, limitations.isPositive())) {
            System.out.println(limitations.getWrongTypeMessage());
            return false;
        }
        return true;
    }

    public static boolean validateData(String data, Limitations limitations) {
        switch (limitations.getDataType()) {
            case LONG -> {
                return checkLong(data);
            }
            case POSITION -> {
                return checkPosition(data);
            }
            case FILEPATH -> {
                return checkFilePath(data);
            }
            case INT -> {
                return checkInt(data);
            }
            case DATE -> {
                return checkDate(data);
            }
            case FLOAT -> {
                return checkFloat(data);
            }
            case DOUBLE -> {
                return checkDouble(data);
            }
            case STATUS -> {
                return checkStatus(data);
            }
            default -> {
                return true;
            }
        }
    }

    private static boolean positiveCheck(String number, boolean positive) {
        if (!positive) {
            return true;
        }
        return Double.compare(Double.parseDouble(number), 0) > 0;
    }

    private static boolean checkLong(String data) {
        try {
            Long.parseLong(data);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static boolean checkInt(String data) {
        try {
            Integer.parseInt(data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean checkFloat(String data) {
        try {
            float f = Float.parseFloat(data);
            return !Float.isInfinite(f);
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean checkDouble(String data) {
        try {
            Double d = Double.parseDouble(data);
            if (d.equals(Double.POSITIVE_INFINITY)) {
                return false;
            }
            return !d.equals(Double.NEGATIVE_INFINITY);
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean checkPosition(String data) {
        try {
            Position.valueOf(data.toUpperCase());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static boolean checkStatus(String data) {
        try {
            Status.valueOf(data.toUpperCase());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean checkDate(String data) {
        try {
            LocalDate ld = LocalDate.parse(data);
            return ld.isAfter(LocalDate.parse("-0001-12-31"));
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean checkFilePath(String data) {
        try {
            new Scanner(new File(data).getAbsoluteFile());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
