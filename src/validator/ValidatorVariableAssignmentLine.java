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
        String left = lineToCheck.getRight();
        Variable leftVar = scope.getVariableByName(left);
        Type rightType;
        String right = lineToCheck.getRight();
        Variable rightVar;
        if (Type.isType(right)){
            rightType = Type.getTypeOf(right);
            return (leftVar.canAssign(rightType));
        }
        else{
            if(scope.contains(right)){
                rightVar = scope.getVariableByName(right);
                return leftVar.canAssign(rightVar);
            }
        }
        return false;
    }

    private ValidatorVariableAssignmentLine() {
    }
}
