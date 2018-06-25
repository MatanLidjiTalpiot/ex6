package foundation.Exceptions;

/**
 * A class that represents an invalid assignment exception.
 */
public class InvalidAssignmentException extends Exception{
    /**
     * A constructor method that builds the exception.
     * @param message An informative message.
     */
    public InvalidAssignmentException(String message){
        super(message);
    }
}
