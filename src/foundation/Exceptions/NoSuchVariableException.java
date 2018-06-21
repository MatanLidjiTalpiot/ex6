package foundation.Exceptions;

/**
 * An exception class.
 */
public class NoSuchVariableException extends Exception {
    /**
     * A constructor for NoSuchVariableException
     * @param varName the name of the variable that doesn't exists.
     */
    public NoSuchVariableException(String varName){
        super(varName + "is not a declared Variable");
    }
}
