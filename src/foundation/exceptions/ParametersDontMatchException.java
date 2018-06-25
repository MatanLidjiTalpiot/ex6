package foundation.exceptions;

/**
 * An exception for when a call for a method does not match the demands of the method.
 */
public class ParametersDontMatchException extends FileException {
    /**
     * A constructor for this Exception
     * @param message the message to display with the exception.
     */
    public ParametersDontMatchException(String message){
        super(message);
    }
}
