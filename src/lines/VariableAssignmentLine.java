package lines;

import foundation.Variable;
import validator.ValidatorStrategy;
import validator.ValidatorVariableAssignmentLine;

/**
 * A class that represent A VariableAssignmentLine
 */
public class VariableAssignmentLine extends Line {

    private Variable left;
    private Variable toAssign;
    private String leftName;
    private String right;
    private static ValidatorStrategy validator = ValidatorVariableAssignmentLine.getInstance();
    /**
     * A constructor for A VariableAssignmentLine
     * @param leftName the name of the variable on the left side of the assignment line.
     * @param right the right side of the assignment line.
     */
    public VariableAssignmentLine(String leftName, String right){
        this.leftName = leftName;
        this.right = right;
    }
}
