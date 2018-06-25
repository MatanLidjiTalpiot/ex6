package foundation.exceptions;

/**
 * A class that is an exception when there is a method call for a method that does not exists in the scope.
 */
public class NoSuchMethodException extends FileException {
    /**
     * A constructor for the no such method exception
     * @param message the message that will be shown in the exception.
     */
    public NoSuchMethodException(String message){
        super(message);
    }
}
