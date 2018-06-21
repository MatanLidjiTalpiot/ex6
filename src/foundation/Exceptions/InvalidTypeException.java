package foundation.Exceptions;

public class InvalidTypeException extends Exception {

    public InvalidTypeException(String strToMatch){
        super(strToMatch + "it not a valid Type");
    }

}
