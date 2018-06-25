package blocks;
import foundation.*;
import foundation.exceptions.FileException;
import foundation.exceptions.NestedMethodException;


import java.util.LinkedList;

/**
 * A class that represents a method block
 */
public class MethodBlock extends Block implements Checkable {

    private String methodName;
    private LinkedList<String> paramNamesByOrder;
    private LinkedList<Type> typeNamesByOrder;
    private LinkedList<Boolean> isFinalByOrder;
    private LinkedList<Variable> params = new LinkedList<>();

    /**
     * A constuctor that builds a method block
     * @param methodName the name of the method
     * @param varNamesByOrder the names of the parameters of the method by order.
     * @param typeNamesByOrder the types of the parameter of the method by order.
     * @param isFinalByOrder if the parameters is final or not by order.
     * @param fatherScope the scope of the father.
     */
    public MethodBlock(String methodName,LinkedList<String> varNamesByOrder,LinkedList<Type>
            typeNamesByOrder, LinkedList<Boolean> isFinalByOrder,
                       Scope
            fatherScope) throws NestedMethodException {

        super(fatherScope);
        if(fatherScope.hasFather()){ // according to this exercise demands are that a method can not be
            // written within another method or some kind of a condition block. hence, the block that the
            // method block is in is the block of all the file, and does not have a father.
            throw new NestedMethodException("illegal nesting of blocks");
        }
        this.methodName = methodName;
        this.paramNamesByOrder = varNamesByOrder;
        this.typeNamesByOrder = typeNamesByOrder;
        this.isFinalByOrder = isFinalByOrder;
        createParam();
        scope.addVariables(params);
        createMethod();
    }

    /**
     * A method that creates the parameters of the method.
     */
    private void createParam() {
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
    private void createMethod(){
        Method method = new Method(methodName, params);
        fatherScope.addMethod(method);
    }

    @Override
    public boolean check(Scope scope) throws FileException {
        return super.check(scope);
    }
}
