package lines;

import foundation.InvalidTypeException;
import foundation.Type;
import foundation.Variable;
import validator.ValidatorDeclartionLine;
import validator.ValidatorStrategy;

/**
 * A class that represents a VariableDeclerationLine.
 */
public class VariableDeclerationLine extends Line {

    private static ValidatorDeclartionLine validator = ValidatorDeclartionLine.getInstance();
    private Variable variable;
    private Type varType;
    private boolean isFinal;

    /**
     * A constructor that creates a VarialbeDeclerationLine
     * @param type a String representation of what should be the type of the variable
     * @param name the name of the variable;
     * @param isFinal if the variable is final
     * @throws InvalidTypeException if the type is not a type that exists.
     */
    public VariableDeclerationLine(String type, String name, boolean isFinal)throws InvalidTypeException{
        Type this.varType = Type.strToType(type);
        this.isFinal = isFinal;
        variable = new Variable(name,varType, isFinal);


    }

    public Variable getVariable() {
        return variable;
    }
}
