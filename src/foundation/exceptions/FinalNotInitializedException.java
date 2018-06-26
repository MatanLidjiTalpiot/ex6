package foundation.exceptions;

/**
 * A class that is a final ot initialized exception.
 */
public class FinalNotInitializedException extends FileException {
    /**
     * An exception for a final variable that is not initialized.
     * @param message the message.
     */
    public FinalNotInitializedException(String message){
        super(message);
    }
}
