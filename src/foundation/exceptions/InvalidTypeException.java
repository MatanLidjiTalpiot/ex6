package foundation.exceptions;

public class InvalidTypeException extends FileException {

    public InvalidTypeException(String strToMatch){
        super(strToMatch + " InvalidTypeException");
    }

}
