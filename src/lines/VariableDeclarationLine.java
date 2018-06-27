package lines;

import foundation.*;
import foundation.exceptions.AlreadyDeclaredVariableExcpetion;
import foundation.exceptions.FinalNotInitializedException;
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
    public VariableDeclarationLine(Type varType, String name, boolean isFinal, boolean hasAssignment)
            throws FinalNotInitializedException{
        variable = new Variable(name, varType,isFinal, hasAssignment);
    }

    /**
     * A constructor for a decleration line.
     * @param varType the type of the variable
     * @param name the name of the variable
     */
    public VariableDeclarationLine(Type varType, String name){
        variable = new Variable(name, varType);
    }

    /**
     * A method that gives the variable that is declared
     * @return the variable that is declared.
     */
    public Variable getVariable() {
        return variable;
    }


    @Override
    public boolean check(Scope scope) throws AlreadyDeclaredVariableExcpetion{
        return ValidatorDeclarationLine.validate(scope,this);
    }


    @Override
    public TypesOfCheckable getTypeOfCheckable() {
        return typeOfCheckable;
    }
}
