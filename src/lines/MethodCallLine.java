package lines;
import foundation.*;
import java.util.LinkedList;
import validator.ValidatorMethodCallLine;
/**
 * A class that represents a method call line.
 */
public class MethodCallLine extends Line{
    private String methodName;
    private LinkedList<String> varNamesByOrder;
    private LinkedList<Type> typesByOrder;

    private static ValidatorMethodCallLine validator = ValidatorMethodCallLine.getInstance();

    /**
     * A constuctor for the MethodCallLine
     * @param methodName the name of the method
     * @param parametersNamesByOrder the variables Names By order
     */
    public MethodCallLine(String methodName, LinkedList<String> parametersNamesByOrder){
        this.methodName = methodName;
        this.varNamesByOrder = parametersNamesByOrder;
    }


    public String getMethodName() {
        return methodName;
    }

    public LinkedList<String> getVarNamesByOrder() {
        return varNamesByOrder;
    }

    public LinkedList<Type> getTypesByOrder() {
        return typesByOrder;
    }

    public boolean check(Scope scope){
        return validator.validate(scope, this);
    }
}
