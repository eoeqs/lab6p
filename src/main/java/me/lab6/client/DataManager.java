package managers;

import com.google.gson.JsonObject;
import exceptions.*;
import utilities.DataType;
import workerRelated.Position;
import workerRelated.Status;

import java.io.File;
import java.time.LocalDate;

/**
 * The DataMan class provides methods for checking input data against given criteria.
 */
public class DataManager {

    private static boolean checkInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean checkLong(String input) {
        try {
            Long.parseLong(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean checkFloat(String input) {
        try {
            Float f = Float.parseFloat(input);
            return f.compareTo(Float.MAX_VALUE) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean checkDouble(String input) {
        try {
            Double d = Double.parseDouble(input);
            if (d.equals(Double.POSITIVE_INFINITY)) {
                System.out.println("This number is too large, it should be less than " + Double.MAX_VALUE);
                return false;
            }
            if (d.equals(Double.NEGATIVE_INFINITY)) {
                System.out.println("This number is too small, it should be greater than " + Double.MIN_VALUE);
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean checkPosition(String input) {
        try {
            Position.valueOf(input.toUpperCase());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean checkStatus(String input) {
        try {
            Status.valueOf(input.toUpperCase());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean checkDate(String input) {
        try {
            LocalDate.parse(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean checkFilePath(String input) {
        try {
            new File(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if the input equals the exit command and throws an ExitException if it does.
     *
     * @param input the input string to check
     * @throws ExitException if the input equals the exit command
     */
    protected static void handleExit(String input) throws ExitException {
        if (input.equalsIgnoreCase("!e")) {
            throw new ExitException();
        }
    }

    /**
     * Checks if the input string matches the given data type and whether it is nullable and positive.
     *
     * @param targetType the expected data type of the input
     * @param nullable   a flag indicating whether the input is nullable or not
     * @param positive   a flag indicating whether the input must be a positive value or not
     * @param inputStr   the input string to check
     * @return true if the input matches the given criteria, false otherwise
     * @throws NoArgumentException if the input is null or blank and not nullable
     * @throws WrongTypeException  if the input does not match the expected data type
     */
    public static boolean check(DataType targetType, boolean nullable, boolean positive, String inputStr)
            throws NoArgumentException, WrongTypeException {
        if (nullChecks(targetType, nullable, inputStr)) {
            return true;
        }
        if (typeCheck(targetType, inputStr)) {
            if (positive) {
                if (positiveCheck(inputStr)) {
                    return true;
                }
            }
            return true;
        } else {
            throw new WrongTypeException();
        }
    }

    /**
     * Checks if the input string matches the given data type.
     *
     * @param arg the input string to check
     * @return true if the input matches the expected data type, false otherwise
     * @throws WrongTypeException  if the input does not match the expected data type
     * @throws NoArgumentException if the input is null or blank and not nullable
     */
    protected static boolean commandCheck(Object[] limitations, String arg) throws WrongTypeException, NoArgumentException {
        DataType dataType = (DataType) limitations[0];
        boolean nullable = (boolean) limitations[1];
        boolean positive = (boolean) limitations[2];
        return check(dataType, nullable, positive, arg);
    }

    private static boolean nullChecks(DataType targetType, boolean nullable, String inputStr) throws NoArgumentException {
        if (targetType == null) {
            return true;
        }
        if (inputStr == null || inputStr.isBlank()) {
            if (nullable) {
                return true;
            } else {
                throw new NoArgumentException();
            }
        }
        return false;
    }


    private static boolean typeCheck(DataType target, String inputStr) {
        return switch (target) {
            case INT -> checkInt(inputStr);
            case LONG -> checkLong(inputStr);
            case FLOAT -> checkFloat(inputStr);
            case DOUBLE -> checkDouble(inputStr);
            case POSITION -> checkPosition(inputStr);
            case STATUS -> checkStatus(inputStr);
            case DATE -> checkDate(inputStr);
            case FILEPATH -> checkFilePath(inputStr);
            case STRING -> true;
        };
    }

    private static boolean positiveCheck(String inputStr) throws WrongTypeException {
        if (Float.compare(Float.parseFloat(inputStr), 0) > 0) {
            return true;
        } else {
            throw new WrongTypeException();
        }
    }

    /**
     * This method checks whether a given JsonObject contains the specified field.
     * If the field is not present, it throws an IncorrectWorkerFieldException.
     *
     * @param jOb   The JsonObject to be checked.
     * @param field The field to check for in the JsonObject.
     * @throws IncorrectWorkerFieldException if the specified field is not present in the JsonObject.
     */
    public static void ensureHas(JsonObject jOb, String field) {
        if (!jOb.has(field)) {
            throw new IncorrectWorkerFieldException();
        }
    }

    /**
     * This method ensures that a given data type, nullable, positive, and string value are correct.
     * It calls the DataMan.check method to verify the data and throws an IncorrectWorkerFieldException
     * if the data is incorrect or if an exception occurs.
     *
     * @param type     The data type to be checked.
     * @param nullable Whether the data is nullable.
     * @param positive Whether the data is positive.
     * @param string   The string value to be checked.
     * @throws IncorrectWorkerFieldException if the data is incorrect or if an exception occurs.
     */
    public static void ensureCorrect(DataType type, boolean nullable, boolean positive, String string) {
        try {
            if (!DataManager.check(type, nullable, positive, string)) {
                throw new IncorrectWorkerFieldException();
            }
        } catch (NoArgumentException | WrongTypeException e) {
            throw new IncorrectWorkerFieldException();
        }
    }
}
