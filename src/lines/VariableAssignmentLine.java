package lines;

import foundation.Exceptions.NoSuchVariableException;
import foundation.Scope;
import validator.ValidatorVariableAssignmentLine;

/**
 * A class that represent A VariableAssignmentLine
 */
public class VariableAssignmentLine extends Line {

    private String left;
    private String right;
    private static ValidatorVariableAssignmentLine validator = ValidatorVariableAssignmentLine.getInstance();
    /**
     * A constructor for A VariableAssignmentLine
     * @param left the name of the variable on the left side of the assignment line.
     * @param right the right side of the assignment line.
     */
    public VariableAssignmentLine(String left, String right){
        this.left = left;
        this.right = right;
    }

    public String getRight() {
        return right;
    }

    public String getLeft(){
        return left;
    }


    public boolean check(Scope scope)throws NoSuchVariableException{
        return validator.validate(scope, this);
    }
}
