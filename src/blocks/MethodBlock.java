package blocks;
import foundation.*;
import foundation.Exceptions.InvalidTypeException;


import java.util.LinkedList;

/**
 * A class that represents a method block
 */
public class MethodBlock extends Block implements Checkable {

    private String methodName;
    private LinkedList<String> paramNamesByOrder;
    private LinkedList<Type> typeNamesByOrder;
    private LinkedList<Boolean> isFinalByOrder;
    private LinkedList<Variable> params;

    /**
     * A constuctor that builds a method block
     * @param methodName the name of the method
     * @param varNamesByOrder the names of the parameters of the method by order.
     * @param typeNamesByOrder the types of the parameter of the method by order.
     * @param isFinalByOrder if the parameters is final or not by order.
     * @param fatherScope the scope of the father.
     * @throws InvalidTypeException
     */
    public MethodBlock(String methodName,LinkedList<String> varNamesByOrder,LinkedList<Type>
            typeNamesByOrder, LinkedList<Boolean> isFinalByOrder,
                       Scope
            fatherScope) throws InvalidTypeException{

        super(fatherScope);
        this.methodName = methodName;
        this.paramNamesByOrder = varNamesByOrder;
        this.typeNamesByOrder = typeNamesByOrder;
        this.isFinalByOrder = isFinalByOrder;
        createParam();
        scope.addVariables(params);
    }

    /**
     * A method that creates the parameters of the method.
     * @throws InvalidTypeException
     */
    private void createParam() throws InvalidTypeException{
        for (int i = 0; i < typeNamesByOrder.size(); i++){
            String name = paramNamesByOrder.get(i);
            Type type = typeNamesByOrder.get(i);
            boolean isFinal = isFinalByOrder.get(i);
            params.add(new Variable(name,type, isFinal));
        }
    }

    /**
     * a method that adds a method to the scope
     */
    public void createMethod(){
        Method method = new Method(methodName, params);
        fatherScope.addMethod(method);
    }

    //TODO check
}
