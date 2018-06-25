package foundation.exceptions;

/**
 * An invalid condition exception.
 */
public class InvalidConditionException extends FileException {

    public InvalidConditionException(String condition){
        super(condition + " is not a valid condition");
    }

}
