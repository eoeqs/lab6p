package managers;

import commands.Command;
import exceptions.*;
import utilities.DataLimitations;
import utilities.DataType;
import workerRelated.*;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A class that represents the user interface for the application.
 * It is responsible for handling user input and sending it to the appropriate command manager.
 */
public class UserInteractionManager {
    private static Scanner scanner = new Scanner(System.in);
    private final CommandManager commandManager;
    private static boolean mode;

    /**
     * Constructs a new UIMan object with a given Command Manager object.
     *
     * @param commandManager The Command Manager object.
     */
    public UserInteractionManager(CommandManager commandManager) {
        this.commandManager = commandManager;
        mode = commandManager.getMode();
    }

    /**
     * Sets the scanner and mode to the given values.
     *
     * @param newScanner The new scanner object.
     * @param newMode    The new mode value.
     */
    public static void setSource(Scanner newScanner, boolean newMode) {
        scanner = newScanner;
        mode = newMode;
    }

    /**
     * Returns the current mode.
     *
     * @return The current mode value.
     */

    public static boolean getMode() {
        return mode;
    }

    /**
     * Reads the user input and returns it as a string after removing any leading or trailing white spaces.
     * Throws InputEndException or ScriptEndException if the input has reached its end.
     *
     * @return The user input as a string.
     * @throws InputEndException  if the input has reached its end.
     * @throws ScriptEndException if the input is being read from a script and it has reached its end.
     */
    private static String getInput() throws InputEndException, ScriptEndException {
        try {
            return scanner.nextLine().strip();
        } catch (NoSuchElementException e) {
            if (mode) {
                throw new InputEndException();
            }
            throw new ScriptEndException();
        }
    }

    private void handleInput() throws ScriptEndException, InputEndException, ExitException {
        String[] input;
        input = getInput().strip().split("\\s+");
        if (!input[0].isEmpty()) {
            try {
                sendCommand(prepareCommand(input));
            } catch (NonExistentCommandException e) {
                if (mode) {
                    System.out.println("Non-existent command. Use 'help' to see the information about available commands.\n");
                } else {
                    System.out.println("Attempt to enter a non-existent command.\n");
                }
            } catch (WrongTypeException e) {
                System.out.println(commandManager.getCommandMap().get(input[0]).argLimitations()[4]);
            } catch (NoArgumentException e) {
                System.out.println(commandManager.getCommandMap().get(input[0]).argLimitations()[5]);
            }
        }
    }

    private Object[] prepareCommand(String[] input)
            throws NonExistentCommandException, WrongTypeException, NoArgumentException {
        if (!commandManager.hasCommand(input[0].toLowerCase())) {
            throw new NonExistentCommandException();
        } else {
            Command command = commandManager.getCommandMap().get(input[0].toLowerCase());
            String arg = input.length == 1 ? null : input[1];
            try {
                if (DataManager.commandCheck(command.argLimitations(), arg)) {
                    return new Object[]{command, arg};
                } else {
                    throw new WrongTypeException();
                }
            } catch (WrongTypeException e) {
                throw new WrongTypeException();
            } catch (NoArgumentException e) {
                throw new NoArgumentException();
            }
        }
    }

    public void interact() throws InputEndException, ScriptEndException, ExitException {
        if (mode) {
            System.out.println(startMessage());
        }
        while (true) {
            if (mode) {
                System.out.print("> ");
            }
            handleInput();
        }
    }

    private void sendCommand(Object[] commandAndArg) throws ExitException {
        Command command = (Command) commandAndArg[0];
        String arg = (String) commandAndArg[1];
        commandManager.handleCommand(command, arg);
    }

    private static String getData(Object[] limitations, String input)
            throws WrongTypeException, NoArgumentException, ExitException {
        DataManager.handleExit(input);
        if (DataManager.check((DataType) limitations[0], (boolean) limitations[1], (boolean) limitations[2], input)) {
            return input;
        } else {
            throw new WrongTypeException();
        }
    }

    private static String getField(Object[] limitations) throws ExitException {
        while (true) {
            if (mode) {
                System.out.print(">> ");
            }
            try {
                return getData(limitations, getInput());
            } catch (WrongTypeException e) {
                System.out.println(limitations[4]);
            } catch (NoArgumentException e) {
                System.out.println(limitations[5]);
            } catch (ScriptEndException e) {
                throw new ExitException();
            } catch (InputEndException e) {
                setSource(new Scanner(System.in), true);
                throw new ExitException();
            }
        }
    }

    private static String getName() throws ExitException {
        Object[] nameLimitations = new DataLimitations(DataType.STRING, false, false,
                "Enter worker's name:", null, "Name can't be null.").limitations();
        if (mode) {
            System.out.println(nameLimitations[3]);
        }
        return getField(nameLimitations);
    }

    private static double getX() throws ExitException {
        Object[] xLimitations = new DataLimitations(DataType.DOUBLE, true, false,
                "Enter worker's X coordinate:", "X coordinate has to be a double precision value.",
                null).limitations();
        double x = 0.0;
        if (mode) {
            System.out.println(xLimitations[3]);
        }
        String preX = getField(xLimitations);
        if (!preX.isEmpty()) {
            x = Double.parseDouble(preX);
        }
        return x;
    }

    private static double getY() throws ExitException {
        Object[] yLimitations = new DataLimitations(DataType.DOUBLE, false, false,
                "Enter worker's Y coordinate:", "Y coordinate has to be a double precision value.",
                "Y coordinate can't be null.").limitations();
        if (mode) {
            System.out.println(yLimitations[3]);
        }
        return Double.parseDouble(getField(yLimitations));
    }

    private static int getSalary() throws ExitException {
        Object[] salaryLimitations = new DataLimitations(DataType.INT, false, true,
                "Enter worker's salary:", "Salary has to be a long value greater than 0.",
                "Salary can't be null.").limitations();
        if (mode) {
            System.out.println(salaryLimitations[3]);
        }
        return Integer.parseInt(getField(salaryLimitations));
    }

    private static LocalDate getStartDate() throws ExitException {
        Object[] startDateLimitations = new DataLimitations(DataType.DATE, false, false,
                "Enter worker's start date:", "Please, enter a correct date value (YYYY-MM-DD).",
                "Start date can't be null.").limitations();
        LocalDate startDate;
        if (mode) {
            System.out.println(startDateLimitations[3]);
        }
        while (true) {
            startDate = LocalDate.parse(getField(startDateLimitations));
            if (startDate.compareTo(LocalDate.now()) < 0) {
                System.out.println("Start date has to be equal to or greater than the current date.");
            } else {
                break;
            }
        }
        return startDate;
    }

    private static Position getPosition() throws ExitException {
        Object[] positionLimitations = new DataLimitations(DataType.POSITION, true, false,
                "Enter worker's position:",
                "A proper position value (" + Position.allPositions() + ") has to be entered.",
                null).limitations();
        Position position = null;
        if (mode) {
            System.out.println(positionLimitations[3]);
        }
        String prePosition = getField(positionLimitations);
        if (!prePosition.isEmpty()) {
            position = Position.valueOf(prePosition.toUpperCase());
        }
        return position;
    }

    private static Status getStatus() throws ExitException {
        Object[] statusLimitations = new DataLimitations(DataType.STATUS, true, false,
                "Enter worker's status:",
                "A proper status value (" + Status.allStatuses() + ") has to be entered.",
                null).limitations();
        Status status = null;
        if (mode) {
            System.out.println(statusLimitations[3]);
        }
        String preStatus = getField(statusLimitations);
        if (!preStatus.isEmpty()) {
            status = Status.valueOf(preStatus.toUpperCase());
        }
        return status;
    }

    private static String getOrgName() throws ExitException {
        Object[] orgNameLimitations = new DataLimitations(DataType.STRING, false, false,
                "Enter organization name:", null,
                "Organization name can't be null.").limitations();
        if (mode) {
            System.out.println(orgNameLimitations[3]);
        }
        return getField(orgNameLimitations);
    }

    private static int getTurnover() throws ExitException {
        Object[] turnoverLimitations = new DataLimitations(DataType.INT, false, true,
                "Enter organization's annual turnover:",
                "Annual turnover has to be an integer value greater than 0.",
                "Annual turnover can't be null.").limitations();
        if (mode) {
            System.out.println(turnoverLimitations[3]);
        }
        return Integer.parseInt(getField(turnoverLimitations));
    }

    private static long getEmpCount() throws ExitException {
        Object[] empCountLimitations = new DataLimitations(DataType.LONG, false, true,
                "Enter organization's employee count:",
                "Employee count has to be a long value greater than 0.",
                "Employee count can't be null.").limitations();
        if (mode) {
            System.out.println(empCountLimitations[3]);
        }
        return Long.parseLong(getField(empCountLimitations));
    }

    private static String getStreet() throws ExitException {
        Object[] streetLimitations = new DataLimitations(DataType.STRING, true, false,
                "Enter organization's street:", null, null).limitations();
        String street = null;
        if (mode) {
            System.out.println(streetLimitations[3]);
        }
        String preStreet = getField(streetLimitations);
        if (!preStreet.isEmpty()) {
            street = preStreet;
        }
        return street;
    }

    private static String getZipCode() throws ExitException {
        Object[] zipCodeLimitations = new DataLimitations(DataType.STRING, false, false,
                "Enter organization's zip code:", null,
                "Zip code can't be null.").limitations();
        if (mode) {
            System.out.println(zipCodeLimitations[3]);
        }
        return getField(zipCodeLimitations);
    }

    /**
     * Creates a new Organization instance based on user input.
     *
     * @return a new Organization instance with the provided information.
     * @throws ExitException if the user chooses to exit the program.
     */
    public static Organization createNewOrganization() throws ExitException {
        String orgName = getOrgName();
        int turnover = getTurnover();
        long empCount = getEmpCount();
        String street = getStreet();
        String zipCode = getZipCode();
        Address address = new Address(street, zipCode);
        return new Organization(orgName, turnover, empCount, address);
    }

    /**
     * The interact method enables interaction with the user through command-line input.
     *
     * @throws ExitException      if the user inputs the exit command to terminate the program.
     */
    public static Worker createNewWorker(long id) throws ExitException {
        String name = getName();
        double x = getX();
        Double y = getY();
        Coordinates coordinates = new Coordinates(x, y);
        int salary = getSalary();
        LocalDate startDate = getStartDate();
        Position position = getPosition();
        Status status = getStatus();
        Organization organization = createNewOrganization();
        return new Worker(id, name, coordinates, LocalDate.now(), salary, startDate, position, status, organization);
    }

    /**
     * This method returns a message indicating that the argument provided for a command is incorrect.
     *
     * @param command the command for which the argument is incorrect.
     * @return a String message indicating that the argument provided for a command is incorrect.
     */
    public static String wrongArgMessage(Command command) {
        return "Wrong argument for command '" + command.name() + "'. ";
    }

    /**
     * This method returns a message indicating that a command requires an argument.
     *
     * @param command the command that requires an argument.
     * @return a String message indicating that a command requires an argument.
     */
    public static String noArgMessage(Command command) {
        return "Command '" + command.name() + "' requires and argument.";
    }

    private static String startMessage() {
        return """
                -------------------------------------------------------------
                 Welcome to WorkerManager. Please, start typing in commands.
                 Use 'help' to see the information about available commands.
                -------------------------------------------------------------""";
    }
}
