package validator;
import foundation.Exceptions.InvalidAssignmentException;
import foundation.Exceptions.NoSuchVariableException;
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
     * @throws NoSuchVariableException
     */
    public static boolean validate(Scope scope,VariableAssignmentLine lineToCheck) throws
            NoSuchVariableException, InvalidAssignmentException{
        String left = lineToCheck.getRight();
        Variable leftVar = scope.getVariableByName(left);
        String right = lineToCheck.getRight();
        Variable rightVar;

        if (scope.contains(right)){
            rightVar = scope.getVariableByName(right);
            return (leftVar.assign(rightVar));
        }
        else{
            return (leftVar.assign(right));
        }
    }

    private ValidatorVariableAssignmentLine() {
    }
}
