package validator;
import foundation.exceptions.InvalidAssignmentException;
import foundation.exceptions.NoSuchVariableException;
import foundation.Variable;
import lines.VariableAssignmentLine;
import foundation.Scope;

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
     * @throws InvalidAssignmentException An exception when the assignment is illegal.
     */
    public static boolean validate(Scope scope,VariableAssignmentLine lineToCheck) throws
            InvalidAssignmentException{
        String left = lineToCheck.getLeft();
        try{
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
        catch (NoSuchVariableException e){ // not supposed to be catched...
            throw new InvalidAssignmentException("no variable" + left);
        }
    }

    private ValidatorVariableAssignmentLine() {
    }
}
