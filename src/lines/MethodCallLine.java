package lines;
import foundation.*;
import java.util.LinkedList;
import validator.*;
/**
 * A class that represents a method call line.
 */
public class MethodCallLine extends Line{
    private String methodName;
    private LinkedList<String> varNamesByOrder;
    private LinkedList<Type> typesByOrder;
    private static ValidatorStrategy validator = ValidatorMethodCallLine.getInstance();

    /**
     * A constuctor for the MethodCallLine
     * @param methodName the name of the method
     * @param varNamesByOrder the variables Names By order
     * @param typesByOrder the variables type by order.
     */
    public MethodCallLine(String methodName, LinkedList<String> varNamesByOrder, LinkedList<Type>
            typesByOrder){
        this.methodName = methodName;
        this.varNamesByOrder = varNamesByOrder;
        this.typesByOrder = typesByOrder;
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
}
