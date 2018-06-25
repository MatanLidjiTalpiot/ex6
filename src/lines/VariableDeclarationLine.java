package lines;

import foundation.Checkable;
import foundation.exceptions.AlreadyDeclaredVariableExcpetion;
import foundation.Type;
import foundation.Variable;
import validator.ValidatorDeclarationLine;
import foundation.Scope;

/**
 * A class that represents a VariableDeclarationLine.
 */
public class VariableDeclarationLine implements Checkable {

    private Variable variable;
    /**
     * A constructor that creates a VarialbeDeclerationLine
     * @param varType a String representation of what should be the type of the variable
     * @param name the name of the variable;
     * @param isFinal if the variable is final
     */
    public VariableDeclarationLine(Type varType, String name, boolean isFinal){
        variable = new Variable(name, varType,isFinal);
    }

    public Variable getVariable() {
        return variable;
    }

    public boolean check(Scope scope) throws AlreadyDeclaredVariableExcpetion{
        return ValidatorDeclarationLine.validate(scope,this);
    }
}
