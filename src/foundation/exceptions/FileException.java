package foundation.exceptions;

abstract public class FileException extends Exception {
    /**
     * An exception that all of the exceptions that happen while scanning the file inherit from.
     * @param message
     */
    public FileException(String message){
        super(message);
    }
}
