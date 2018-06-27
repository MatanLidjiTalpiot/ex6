package lines;

import foundation.Checkable;
import foundation.Type;
import foundation.TypesOfCheckable;
import foundation.exceptions.FileException;
import foundation.exceptions.InvalidAssignmentException;
import foundation.Scope;
import validator.*;

/**
 * A class that represent A VariableAssignmentLine
 */
public class VariableAssignmentLine implements Checkable {
    private static TypesOfCheckable typeOfCheckable = TypesOfCheckable.VARIABLE_ASSIGNMENT_LINE;
    private String left;
    private String right;
    /**
     * A constructor for A VariableAssignmentLine
     * @param left the name of the variable on the left side of the assignment line.
     * @param right the right side of the assignment line.
     */
    public VariableAssignmentLine(String left, String right){
        this.left = left;
        this.right = right;
    }

    /**
     * a method that gives the right side of the assignment line.
     * @return the right side of the assignemt line.
     */
    public String getRight() {
        return right;
    }

    /**
     * A method that gives the left side of the assignment line.
     * @return the left side of the assignment line.
     */
    public String getLeft(){
        return left;
    }

    @Override
    public boolean check(Scope scope)throws FileException{
        return ValidatorVariableAssignmentLine.validate(scope, this);
    }

    @Override
    public boolean isBlock(){
        return false;
    }
    @Override
    public boolean isLine(){
        return true;
    }

    @Override
    public TypesOfCheckable getTypeOfCheckable() {
        return typeOfCheckable;
    }
}
