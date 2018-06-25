package lines;

import foundation.Exceptions.AlreadyDeclaredVariableExcpetion;
import foundation.Exceptions.InvalidTypeException;
import foundation.Type;
import foundation.Variable;
import validator.ValidatorDeclartionLine;
import foundation.Scope;

/**
 * A class that represents a VariableDeclarationLine.
 */
public class VariableDeclarationLine extends Line {

    private Variable variable;
    /**
     * A constructor that creates a VarialbeDeclerationLine
     * @param varType a String representation of what should be the type of the variable
     * @param name the name of the variable;
     * @param isFinal if the variable is final
     * @throws InvalidTypeException if the type is not a type that exists.
     */
    public VariableDeclarationLine(Type varType, String name, boolean isFinal)throws InvalidTypeException {
        variable = new Variable(name, varType,isFinal);
    }

    public Variable getVariable() {
        return variable;
    }

    public boolean check(Scope scope) throws AlreadyDeclaredVariableExcpetion{
        return ValidatorDeclartionLine.validate(scope,this);
    }
}
