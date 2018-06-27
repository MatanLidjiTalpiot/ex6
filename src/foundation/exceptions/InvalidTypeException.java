package foundation.exceptions;

/**
 * A class that is an inavlid type exception.
 */
public class InvalidTypeException extends FileException {
    /**
     * A constrctor for the Invalid type exception
     * @param strToMatch the message that will display when printing the exception
     */
    public InvalidTypeException(String strToMatch){
        super(strToMatch + " InvalidTypeException");
    }

}
