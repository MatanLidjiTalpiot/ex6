package foundation.exceptions;

/**
 * A class thar represents a nested method exception.
 */
public class NestedMethodException extends FileException {
    /**
     *
     * @param message the message to dispaly when this exception is throwed.
     */
    public NestedMethodException(String message){
        super(message);
    }
}
