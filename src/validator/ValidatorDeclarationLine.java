package validator;
import foundation.exceptions.AlreadyDeclaredVariableExcpetion;
import foundation.Scope;

import lines.VariableDeclarationLine;

/**
 * A singleton class that we compose on the Declaration line in order to to the check action.
 */
public class ValidatorDeclarationLine {

    private static ValidatorDeclarationLine ourInstance = new ValidatorDeclarationLine();

    public static ValidatorDeclarationLine getInstance() {
        return ourInstance;
    }

    /**
     * A method that does the check action on a declaration line.
     * @param scope the scope to check in.
     * @param line the declaration line to check
     * @return true if the line is a s java legal line.
     * @throws AlreadyDeclaredVariableExcpetion an exception that accrues when the declaration is illegal.
     */
    public static boolean validate(Scope scope, VariableDeclarationLine line) throws
            AlreadyDeclaredVariableExcpetion {
        if(!scope.canAddVar(line.getVariable())){
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
