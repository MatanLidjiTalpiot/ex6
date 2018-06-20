package lines;

import foundation.Variable;
import validator.ValidatorDeclartionLine;
import validator.ValidatorStrategy;

public class VariableDeclerationLine extends Line {

    private static ValidatorDeclartionLine validator = ValidatorDeclartionLine.getInstance();
    private Variable variable;

    public VariableDeclerationLine(String type, String name){

    }
}
