package oop.ex6.foundation.exceptions;

/**
 * An exception class.
 */
public class NoSuchVariableException extends FileException {
    /**
     * A constructor for NoSuchVariableException
     * @param varName the name of the variable that doesn't exists.
     */
    public NoSuchVariableException(String varName){
        super(varName + " NoSuchVariableException");
    }
}
