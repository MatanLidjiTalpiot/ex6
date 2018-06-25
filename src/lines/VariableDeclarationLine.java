package lines;

import foundation.*;
import foundation.exceptions.AlreadyDeclaredVariableExcpetion;
import validator.ValidatorDeclarationLine;

/**
 * A class that represents a VariableDeclarationLine.
 */
public class VariableDeclarationLine implements Checkable {

    private Variable variable;
    private static TypesOfCheckable typeOfCheckable = TypesOfCheckable.VARIABLE_DECLARATION_LINE;
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

    @Override
    public boolean isBlock(){
        return false;
    }
    @Override
    public boolean isLine(){
        return true;
    }

    @Override
    public TypesOfCheckable getTypeOfCheckable() {
        return typeOfCheckable;
    }
}
