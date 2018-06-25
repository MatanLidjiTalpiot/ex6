package foundation.Exceptions;

/**
 * A class that represents an excpetion that is throed when there is an attempt to declare a variable
 * that has already been declared.
 */
public class AlreadyDeclaredVariableExcpetion extends Exception {
    /**
     * A constructor for the already declared variable exception
     * @param message
     */
    public AlreadyDeclaredVariableExcpetion(String message){
        super(message + ": is already a declared variable");
    }
}
