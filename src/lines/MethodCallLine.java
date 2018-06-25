package lines;
import foundation.*;
import java.util.LinkedList;

import foundation.exceptions.IllegalParametersException;
import foundation.exceptions.ParametersDontMatchException;
import foundation.exceptions.NoSuchMethodException;
import validator.ValidatorMethodCallLine;
/**
 * A class that represents a method call line.
 */
public class MethodCallLine implements Checkable{
    private String methodName;
    private LinkedList<String> paramsByOrder;


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
        return ValidatorMethodCallLine.validate(scope, this);
    }
}
