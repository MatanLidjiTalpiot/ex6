package validator;
import foundation.Exceptions.AlreadyDeclaredVariableExcpetion;
import foundation.Scope;
import lines.VariableDeclarationLine;

public class ValidatorDeclarationLine {
    private static ValidatorDeclarationLine ourInstance = new ValidatorDeclarationLine();

    public static ValidatorDeclarationLine getInstance() {
        return ourInstance;
    }
    public static boolean validate(Scope scope, VariableDeclarationLine line) throws
            AlreadyDeclaredVariableExcpetion{
        if(scope.containsVar(line.getVariable().getName())){
            throw new AlreadyDeclaredVariableExcpetion(line.getVariable().getName());
        }
        else{
            scope.addVariable(line.getVariable());
            return true;
        }
    }

    private ValidatorDeclarationLine() {
    }
}
