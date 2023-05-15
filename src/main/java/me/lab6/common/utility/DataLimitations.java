package utilities;

/**
 * A class that represents the limitations for user input data. It includes information about the data type,
 * nullability, positivity, invite message, wrong type message, and no input message.
 */
public class DataLimitations {
    private final DataType type;
    private final boolean nullable;
    private final boolean positive;
    private final String inviteMessage;
    private final String wrongTypeMessage;
    private final String noInputMessage;

    //Для команд без аргумента

    /**
     * Constructs a new instance of DataLimitations for commands without an argument.
     */
    public DataLimitations() {
        type = null;
        nullable = true;
        positive = false;
        inviteMessage = null;
        wrongTypeMessage = null;
        noInputMessage = null;
    }

    //Для команд с аргументом

    /**
     * Constructs a new instance of DataLimitations for commands with an argument.
     *
     * @param type             the data type
     * @param wrongTypeMessage the message that is displayed when the user enters data of a wrong type
     * @param noInputMessage   the message that is displayed when the user does not enter any input data
     */
    public DataLimitations(DataType type, String wrongTypeMessage, String noInputMessage) {
        this.type = type;
        nullable = false;
        positive = false;
        inviteMessage = null;
        this.wrongTypeMessage = wrongTypeMessage;
        this.noInputMessage = noInputMessage;
    }

    //Для вводимых данных

    /**
     * Constructs a new instance of DataLimitations for user input data.
     *
     * @param type             the data type
     * @param nullable         a flag indicating whether the data is nullable or not
     * @param positive         a flag indicating whether the data is positive or not
     * @param inviteMessage    the message that invites the user to enter input data
     * @param wrongTypeMessage the message that is displayed when the user enters data of a wrong type
     * @param noInputMessage   the message that is displayed when the user does not enter any input data
     */
    public DataLimitations(DataType type, boolean nullable, boolean positive, String inviteMessage, String wrongTypeMessage,
                           String noInputMessage) {
        this.type = type;
        this.nullable = nullable;
        this.positive = positive;
        this.inviteMessage = inviteMessage;
        this.wrongTypeMessage = wrongTypeMessage;
        this.noInputMessage = noInputMessage;
    }

    /**
     * Returns an array of the data limitations.
     *
     * @return an array of the data limitations
     */
    public Object[] limitations() {
        return new Object[]{type, nullable, positive, inviteMessage, wrongTypeMessage, noInputMessage};
    }

}
