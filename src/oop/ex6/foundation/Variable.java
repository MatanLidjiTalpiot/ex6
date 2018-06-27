package oop.ex6.foundation;

import oop.ex6.foundation.exceptions.FinalNotInitializedException;
import oop.ex6.foundation.exceptions.InvalidAssignmentException;
import oop.ex6.foundation.exceptions.InvalidTypeException;

/**
 * A class that represents a variable
 */
public class Variable {

    private String name;
    private Type type;
    private boolean isAssigned = false;
    private boolean isFinal = false;

    /**
     * A constructor for a virable
     * @param name the name of the virable
     * @param type the type of the virable
     */
    public Variable(String name, Type type, boolean isFinal, boolean hasAssignment) throws

            FinalNotInitializedException {
        if (!hasAssignment){
            throw new FinalNotInitializedException(name);
        }
        this.name = name;
        this.type = type;
        this.isFinal = isFinal;
    }

    /**
     * A constructor for Variable
     * @param name the name of the variable
     * @param type the type of the variable
     * @param isFinal whether the variable is final or not, in this constructor it is not supposed to be.
     * @throws FinalNotInitializedException an exception fot when a final variable is not initialized.
     */

    /**
     * A constructor for variable
     * @param name
     * @param type
     */
    public Variable(String name, Type type){
        this.name = name;
        this.type = type;
    }


    /**
     * A method that gets the name of a variable
     * @return the name of the variable
     */
    public String getName(){
        return name;
    }

    /**
     * A method that gets the type of the variable
     * @return the type of the variable;
     */
    public Type getType() {
        return type;
    }

    /**
     * A method that gives whether the variable is assigned or not.
     * @return true if assigned, false otherwise.
     */
    public boolean isAssigned() {
        return this.isAssigned;
    }

    /**
     * A method that gives whether the variable is final or not.
     * @return true if the variable is final, false otherwise.
     */
    public boolean isFinal(){
        return isFinal;
    }

    /**
     * A method that checks if it is possible to assign a variable to into a different variable
     * @param toAssign the variable to check
     * @return true if it is possible, false otherwise.
     */
    public boolean canAssign(Variable toAssign) {
        if (isFinal && isAssigned){
            return false;
        }
        if (!toAssign.isAssigned()){
            return false;
        }
        if (toAssign.getType() == this.type) {
            return true;
        }
        if (this.type == Type.DOUBLE && toAssign.getType() == Type.INT) {
            return true;
        }

        if (this.type == Type.BOOLEAN && (toAssign.getType() == Type.INT || toAssign.getType() == Type.DOUBLE)){
            return true;
        }
        return false;
    }

    /**
     * A method that checks if it is legal to assign the given type to the vatiable
     * @param type the type to check
     * @return true if it legal, false otherwise.
     */
    public boolean canAssign(Type type){
        if (isFinal && isAssigned){
            return false;
        }
        if(type == this.type){
            return true;
        }
        if (this.type == Type.DOUBLE && type == Type.INT){
            return true;
        }
        if (this.type == Type.BOOLEAN && (type == Type.INT || type == Type.DOUBLE)){
            return true;
        }
        return false;

    }

    /**
     * A method that assigns into the variable
     * @param var the variable to assign
     * @throws InvalidAssignmentException
     */
    public boolean assign(Variable var)throws InvalidAssignmentException{
        if (canAssign(var)){
            isAssigned = true;
            return true;
        }
        else{
            throw new InvalidAssignmentException(var.getName());
        }
    }

    /**
     * A method that tries to assign into the variable, if it doesn't succeed it throws an Invalid
     * Assignment Exception.
     * @param toAssign the value to assign.
     * @return true if succeeded to assign, otherwise throws exception.
     * @throws InvalidAssignmentException
     */
    public boolean assign(String toAssign)throws InvalidAssignmentException{
        try {
            Type toAssignType = Type.getTypeOf(toAssign);

            if (canAssign(toAssignType)) {
                isAssigned = true;
                return true;
            }
            else {
                throw new InvalidAssignmentException(toAssign);
            }
        }
        catch(InvalidTypeException e){
            throw new InvalidAssignmentException(toAssign);
        }

    }

    /**
     * A method that changes the status of the variable to "assigned", relevent to parameters in methods.
     */
    public void treatAsAssigned(){
        isAssigned = true;
    }

    /**
     * A method that sets the isAssigned member to false.
     */
    public void setNotAssigned(){
        isAssigned = false;
    }

}

