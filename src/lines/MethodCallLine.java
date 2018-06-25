package lines;
import foundation.*;
import java.util.LinkedList;

import foundation.Exceptions.IllegalParametersException;
import foundation.Exceptions.ParametersDontMatchException;
import validator.ValidatorMethodCallLine;
/**
 * A class that represents a method call line.
 */
public class MethodCallLine extends Line{
    private String methodName;
    private LinkedList<String> paramsByOrder;
    private static ValidatorMethodCallLine validator = ValidatorMethodCallLine.getInstance();

    /**
     * A constructor for the MethodCallLine
     * @param methodName the name of the method
     * @param parametersByOrder the variables Names By order
     */
    public MethodCallLine(String methodName, LinkedList<String> parametersByOrder){
        this.methodName = methodName;
        this.paramsByOrder = parametersByOrder;
    }


    public String getMethodName() {
        return methodName;
    }

    public LinkedList<String> getParamsByOrder() {
        return paramsByOrder;
    }

    public boolean check(Scope scope) throws NoSuchMethodException, IllegalParametersException,
            ParametersDontMatchException{
        return validator.validate(scope, this);
    }
}
