package foundation.Exceptions;

/**
 * A class that is an exception when there is a method call for a method that does not exists in the scope.
 */
public class NoSuchMethodException extends Exception {
    /**
     * A constructor for the no such method exception
     * @param message the message that will be shown in the exception.
     */
    public NoSuchMethodException(String message){
        super(message);
    }
}
