package oop.ex6.lines;

import oop.ex6.foundation.Checkable;
import oop.ex6.foundation.Type;
import oop.ex6.foundation.TypesOfCheckable;
import oop.ex6.foundation.exceptions.FileException;
import oop.ex6.foundation.exceptions.InvalidAssignmentException;
import oop.ex6.foundation.Scope;
import oop.ex6.validator.*;

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
    public TypesOfCheckable getTypeOfCheckable() {
        return typeOfCheckable;
    }
}
