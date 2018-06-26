package foundation;

import foundation.exceptions.InvalidAssignmentException;
import foundation.exceptions.InvalidTypeException;

/**
 * A class that represents a virable
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
    public Variable(String name, Type type, boolean isFinal){
        this.name = name;
        this.type = type;
        this.isFinal = isFinal;
    }

    /**
     * A constructor for variable
     * @param name
     * @param type
     */
    public Variable(String name, Type type){
        this.name = name;
        this.type = type;
    }



    public String getName(){
        return name;
    }

    public Type getType() {
        return type;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

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
        if (this.type == Type.STRING && toAssign.getType() == Type.CHAR) {
            return true;
        }
        if (this.type == Type.BOOLEAN && (toAssign.getType() == Type.INT || toAssign.getType() == Type.DOUBLE)){
            return true;
        }
        return false;
    }
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
        if(this.type == Type.STRING && type == Type.CHAR){
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

}

