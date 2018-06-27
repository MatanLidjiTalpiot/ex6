package oop.ex6.foundation.exceptions;

/**
 * A class that is the father of all the exception we throw
 */
abstract public class FileException extends Exception {
    /**
     * An exception that all of the exceptions that happen while scanning the file inherit from.
     * @param message
     */
    public FileException(String message){
        super(message);
    }
}
