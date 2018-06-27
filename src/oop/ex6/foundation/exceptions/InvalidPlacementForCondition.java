package oop.ex6.foundation.exceptions;

public class InvalidPlacementForCondition extends FileException {

    public InvalidPlacementForCondition(String message){
        super(message + " InvalidPlacementForCondition");
    }
}
