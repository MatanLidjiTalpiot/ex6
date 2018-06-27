package oop.ex6.validator;
import oop.ex6.foundation.exceptions.FileException;
import oop.ex6.foundation.exceptions.InvalidAssignmentException;
import oop.ex6.foundation.exceptions.NoSuchVariableException;
import oop.ex6.foundation.Variable;
import oop.ex6.lines.VariableAssignmentLine;
import oop.ex6.foundation.Scope;

/**
 * A singleton class that is composed in a variable assignment line that the check action is delegated to.
 */
public class ValidatorVariableAssignmentLine {
    private static ValidatorVariableAssignmentLine ourInstance = new ValidatorVariableAssignmentLine();

    public static ValidatorVariableAssignmentLine getInstance() {
        return ourInstance;
    }

    /**
     * A method that checks whether an assignment line is valid.
     * @param scope the scope that the line is in.
     * @param lineToCheck the line that we are checking
     * @return true of the assignment is legal, false otherwise.
     * @throws FileException exception that may accrue of the assignment line is illegal
     */
    public static boolean validate(Scope scope,VariableAssignmentLine lineToCheck) throws
            FileException{
        String left = lineToCheck.getLeft();

        Variable leftVar = scope.getVariableByName(left);
        String right = lineToCheck.getRight();
        Variable rightVar;
            if (scope.containsVar(right)) {
                rightVar = scope.getVariableByName(right);
                return (leftVar.assign(rightVar));
            } else {
                return (leftVar.assign(right));
            }

    }

    private ValidatorVariableAssignmentLine() {
    }
}
