package commands;

import exceptions.ExitException;
import utilities.DataLimitations;

/**
 * A command that stops the application without saving.
 * Implements the {@link Command} interface.
 */
public class Exit implements Command {
    /**
     * Throws an ExitException to stop the application without saving.
     *
     * @param arg a string argument (not used in this command)
     */
    @Override
    public void execute(String arg) throws ExitException {
        throw new ExitException();
    }
    /**
     * Returns the name of this command.
     *
     * @return a string representing the name of the command ("exit")
     */
    @Override
    public String name() {
        return "exit";
    }
    /**
     * Returns a string representing the argument syntax of this command.
     *
     * @return an empty string (as this command does not require any arguments)
     */
    @Override
    public String argDesc() {
        return "";
    }
    /**
     * Returns a string representing the description of this command.
     *
     * @return a string describing the functionality of the command ("stop the application without saving")
     */
    @Override
    public String desc() {
        return "stop the application without saving";
    }
    /**
     * Returns an array of objects representing the limitations of the arguments that can be passed to this command.
     *
     * @return an array of DataLimitations objects (in this case, an empty array as no arguments are required)
     */
    @Override
    public Object[] argLimitations() {
        return new DataLimitations().limitations();
    }
}
