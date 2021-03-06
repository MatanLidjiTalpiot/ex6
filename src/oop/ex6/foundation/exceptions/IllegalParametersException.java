package oop.ex6.foundation.exceptions;

/**
 * A class that represents an Illegal parameter exception in a method call
 */
public class IllegalParametersException extends FileException {
    /**
     * A constructor for an illegal parameter
     * @param message the message to display with the exception.
     */
    public IllegalParametersException(String message){
        super(message + " IllegalParametersException");
    }
}
