package lines;
import foundation.*;
import java.util.LinkedList;

import foundation.exceptions.FileException;
import foundation.exceptions.IllegalParametersException;
import foundation.exceptions.ParametersDontMatchException;
import foundation.exceptions.NoSuchMethodException;
import validator.ValidatorMethodCallLine;
/**
 * A class that represents a method call line.
 */
public class MethodCallLine implements Checkable{
    private String methodName;
    private LinkedList<String> paramsByOrder = new LinkedList<>();
    private static TypesOfCheckable typeOfCheckable = TypesOfCheckable.METHOD_CALL_LINE;

    /**
     * A constructor for the MethodCallLine
     * @param methodName the name of the method
     * @param parametersByOrder the variables Names By order
     */
    public MethodCallLine(String methodName, LinkedList<String> parametersByOrder){
        this.methodName = methodName;
        this.paramsByOrder = parametersByOrder;
    }

    /**
     * A method that gives the name of the method that is called.
     * @return the name of the method.
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * A method that gives the names parameters in the call of the method.
     * @return the names of the parameters in the call line.
     */
    public LinkedList<String> getParamsByOrder() {
        return paramsByOrder;
    }

    @Override
    public boolean check(Scope scope) throws FileException{
        return ValidatorMethodCallLine.validate(scope, this);
    }



    @Override
    public TypesOfCheckable getTypeOfCheckable() {
        return typeOfCheckable;
    }
}
