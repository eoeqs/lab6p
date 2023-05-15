package me.lab6.client;

public class DataManager {

//    private static boolean checkInt(String input) {
//        try {
//            Integer.parseInt(input);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    private static boolean checkLong(String input) {
//        try {
//            Long.parseLong(input);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    private static boolean checkFloat(String input) {
//        try {
//            Float f = Float.parseFloat(input);
//            return f.compareTo(Float.MAX_VALUE) > 0;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    private static boolean checkDouble(String input) {
//        try {
//            Double d = Double.parseDouble(input);
//            if (d.equals(Double.POSITIVE_INFINITY)) {
//                System.out.println("This number is too large, it should be less than " + Double.MAX_VALUE);
//                return false;
//            }
//            if (d.equals(Double.NEGATIVE_INFINITY)) {
//                System.out.println("This number is too small, it should be greater than " + Double.MIN_VALUE);
//                return false;
//            }
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    private static boolean checkPosition(String input) {
//        try {
//            Position.valueOf(input.toUpperCase());
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    private static boolean checkStatus(String input) {
//        try {
//            Status.valueOf(input.toUpperCase());
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    private static boolean checkDate(String input) {
//        try {
//            LocalDate.parse(input);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    private static boolean checkFilePath(String input) {
//        try {
//            new File(input);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    protected static void handleExit(String input) throws ExitException {
//        if (input.equalsIgnoreCase("!e")) {
//            throw new ExitException();
//        }
//    }
//
//    public static boolean check(DataType targetType, boolean nullable, boolean positive, String inputStr)
//            throws NoArgumentException, WrongTypeException {
//        if (nullChecks(targetType, nullable, inputStr)) {
//            return true;
//        }
//        if (typeCheck(targetType, inputStr)) {
//            if (positive) {
//                if (positiveCheck(inputStr)) {
//                    return true;
//                }
//            }
//            return true;
//        } else {
//            throw new WrongTypeException();
//        }
//    }
//
//    protected static boolean commandCheck(Object[] limitations, String arg) throws WrongTypeException, NoArgumentException {
//        DataType dataType = (DataType) limitations[0];
//        boolean nullable = (boolean) limitations[1];
//        boolean positive = (boolean) limitations[2];
//        return check(dataType, nullable, positive, arg);
//    }
//
//    private static boolean nullChecks(DataType targetType, boolean nullable, String inputStr) throws NoArgumentException {
//        if (targetType == null) {
//            return true;
//        }
//        if (inputStr == null || inputStr.isBlank()) {
//            if (nullable) {
//                return true;
//            } else {
//                throw new NoArgumentException();
//            }
//        }
//        return false;
//    }
//
//
//    private static boolean typeCheck(DataType target, String inputStr) {
//        return switch (target) {
//            case INT -> checkInt(inputStr);
//            case LONG -> checkLong(inputStr);
//            case FLOAT -> checkFloat(inputStr);
//            case DOUBLE -> checkDouble(inputStr);
//            case POSITION -> checkPosition(inputStr);
//            case STATUS -> checkStatus(inputStr);
//            case DATE -> checkDate(inputStr);
//            case FILEPATH -> checkFilePath(inputStr);
//            case STRING -> true;
//        };
//    }
//
//    private static boolean positiveCheck(String inputStr) throws WrongTypeException {
//        if (Float.compare(Float.parseFloat(inputStr), 0) > 0) {
//            return true;
//        } else {
//            throw new WrongTypeException();
//        }
//    }
//
//    public static void ensureHas(JsonObject jOb, String field) {
//        if (!jOb.has(field)) {
//            throw new IncorrectWorkerFieldException();
//        }
//    }
//
//    public static void ensureCorrect(DataType type, boolean nullable, boolean positive, String string) {
//        try {
//            if (!DataManager.check(type, nullable, positive, string)) {
//                throw new IncorrectWorkerFieldException();
//            }
//        } catch (NoArgumentException | WrongTypeException e) {
//            throw new IncorrectWorkerFieldException();
//        }
//    }
}
