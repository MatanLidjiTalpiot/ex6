package oop.ex6.foundation.exceptions;

/**
 * An invalid condition exception.
 */
public class InvalidConditionException extends FileException {

    public InvalidConditionException(String condition){
        super(condition + " InvalidConditionException");
    }

}
