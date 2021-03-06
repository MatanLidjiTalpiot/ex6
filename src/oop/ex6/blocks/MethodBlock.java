package oop.ex6.blocks;
import oop.ex6.foundation.*;
import oop.ex6.foundation.exceptions.AlreadyDeclaredVariableExcpetion;
import oop.ex6.foundation.exceptions.FileException;
import oop.ex6.foundation.exceptions.NestedMethodException;


import java.util.LinkedList;

/**
 * A class that represents a method block
 */
public class MethodBlock extends Block implements Checkable {
    private static TypesOfCheckable typeOfcheckable = TypesOfCheckable.METHOD_BLOCK;
    private String methodName;
    private LinkedList<String> paramNamesByOrder = new LinkedList<>();
    private LinkedList<Type> typeNamesByOrder = new LinkedList<>();
    private LinkedList<Boolean> isFinalByOrder = new LinkedList<>();
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
            fatherScope) throws FileException {

        super(fatherScope);
        if(fatherScope.hasFather()){ // according to this exercise demands are that a method can not be
            // written within another method or some kind of a condition block. hence, the block that the
            // method block is in is the block of all the file, and does not have a father.
            throw new NestedMethodException("illegal nesting of oop.ex6.blocks");
        }
        this.methodName = methodName;
        this.paramNamesByOrder = varNamesByOrder;
        this.typeNamesByOrder = typeNamesByOrder;
        this.isFinalByOrder = isFinalByOrder;
        createParam();
        scope.addVariables(params);
        createMethod();
        for (Variable param : params){
            param.treatAsAssigned();
        }
    }

    /**
     * A method that creates the parameters of the method.
     */
    private void createParam() throws FileException{
        for (int i = 0; i < typeNamesByOrder.size(); i++){
            String name = paramNamesByOrder.get(i);
            Type type = typeNamesByOrder.get(i);
            boolean isFinal = isFinalByOrder.get(i);
            if(isFinal){
                params.add(new Variable(name, type, isFinal, true));
            }
            else{
                params.add(new Variable(name,type));
            }
        }


    }

    /**
     * a method that adds a method to the scope
     */
    private void createMethod(){
        Method method = new Method(methodName, params, this);
        fatherScope.addMethod(method);
    }

    public boolean run(Scope scope) throws FileException {
        return super.check(this.scope);
    }

    @Override
    public boolean check(Scope scope) throws FileException{
        return methodBlockCheck(this.scope);
    }

    private boolean methodBlockCheck(Scope scope)throws FileException{
        LinkedList<Variable> toSetBackToNotAssigned = new LinkedList<>();

        for (int i = 0; i < content.size(); i++){
            Checkable c = content.get(i);
            if(c.getTypeOfCheckable() == TypesOfCheckable.VARIABLE_ASSIGNMENT_LINE){
                LinkedList<Variable> checkIfNotAssigned = scope.getVariablesOfAllScope(new
                        LinkedList<Variable>());
                for(Variable var : checkIfNotAssigned){
                    if(!var.isAssigned() && !toSetBackToNotAssigned.contains(var)){
                        toSetBackToNotAssigned.add(var);
                    }
                }
            }
            c.check(scope);
        }
        for (Variable var : toSetBackToNotAssigned){
            var.setNotAssigned();
        }



        return true;
    }


    @Override
    public TypesOfCheckable getTypeOfCheckable() {
        return typeOfcheckable;
    }
}
