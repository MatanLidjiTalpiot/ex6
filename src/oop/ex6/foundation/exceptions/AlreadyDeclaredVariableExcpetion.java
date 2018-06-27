package oop.ex6.foundation.exceptions;

/**
 * A class that represents an excpetion that is throed when there is an attempt to declare a variable
 * that has already been declared.
 */
public class AlreadyDeclaredVariableExcpetion extends FileException {
    /**
     * A constructor for the already declared variable exception
     * @param message
     */
    public AlreadyDeclaredVariableExcpetion(String message){
        super(message + " AlreadyDeclaredVariableExcpetion");
    }
}
