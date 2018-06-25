package foundation.exceptions;

/**
 * A class that represents an invalid assignment exception.
 */
public class InvalidAssignmentException extends FileException {
    /**
     * A constructor method that builds the exception.
     * @param message An informative message.
     */
    public InvalidAssignmentException(String message){
        super(message + "InvalidAssignmentException");
    }
}
