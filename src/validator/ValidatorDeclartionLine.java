package validator;
import foundation.Exceptions.AlreadyDeclaredVariableExcpetion;
import foundation.Scope;
import lines.VariableDeclarationLine;

public class ValidatorDeclartionLine {
    private static ValidatorDeclartionLine ourInstance = new ValidatorDeclartionLine();

    public static ValidatorDeclartionLine getInstance() {
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

    private ValidatorDeclartionLine() {
    }
}
