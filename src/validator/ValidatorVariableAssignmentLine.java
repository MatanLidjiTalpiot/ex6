package validator;
import foundation.Exceptions.NoSuchVariableException;
import foundation.Type;
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
            NoSuchVariableException{
        String right = lineToCheck.getRight();
        Variable rightVar = scope.getVariableByName(right);
        Type leftType;
        String left = lineToCheck.getLeft();
        Variable leftVar;
        if (Type.isType(left)){
            leftType = Type.getTypeOf(left);
            return (rightVar.canAssign(leftType));
        }
        else{
            if(scope.contains(left)){
                leftVar = scope.getVariableByName(left);
                return rightVar.canAssign(leftVar);
            }
        }
        return false;
    }

    private ValidatorVariableAssignmentLine() {
    }
}
