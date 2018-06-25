package lines;

import foundation.Exceptions.InvalidTypeException;
import foundation.Type;
import foundation.Variable;
import validator.ValidatorDeclartionLine;
import validator.ValidatorStrategy;
import foundation.Scope;

/**
 * A class that represents a VariableDeclerationLine.
 */
public class VariableDeclerationLine extends Line {

    private static ValidatorStrategy validator = ValidatorDeclartionLine.getInstance();
    private Variable variable;
    private Type varType;
    /**
     * A constructor that creates a VarialbeDeclerationLine
     * @param type a String representation of what should be the type of the variable
     * @param name the name of the variable;
     * @param isFinal if the variable is final
     * @throws InvalidTypeException if the type is not a type that exists.
     */
    public VariableDeclerationLine(Type varType, String name, boolean isFinal)throws InvalidTypeException {
        variable = new Variable(name, varType,isFinal);
    }

    public VariableDeclerationLine(Type varType, String name) throws InvalidTypeException{
        variable = new Variable(name, varType);
    }



    public Variable getVariable() {
        return variable;
    }

    public boolean check(Scope scope){
        return validator.validate(scope,this);
    }
}
