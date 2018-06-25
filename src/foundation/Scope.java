package foundation;

import foundation.Exceptions.NoSuchVariableException;

import java.util.LinkedList;

/**
 * A class that represents a Scope.
 */
public class Scope {

    private LinkedList<Variable> variablesOfScope = new LinkedList<>();
    private LinkedList<Method> methodsOfScope = new LinkedList<>();
    private Scope father;
    private boolean hasFather;

    /**
     * A constructor for a foundation.Scope
     * @param father the scope that this scope is contained in
     */
    public Scope(Scope father){
       this.father = father;
       hasFather = true;
    }
    public Scope(){
        hasFather = false;
    }


    public Scope getFather() {
        return father;
    }

    public boolean hasFather(){
        return hasFather;
    }

    /**
     * A method that adds a variable
     * @param toAdd a Variable.
     */
    public void addVariable(Variable toAdd){
        variablesOfScope.add(toAdd);
    }

    /**
     * A mehtod that adds a linked list if variables
     * @param toAdd A linked list of Variables
     */
    public void addVariables(LinkedList<Variable> toAdd){
        variablesOfScope.addAll(toAdd);
    }

    public Variable getVariableByName(String varName) throws NoSuchVariableException {
        for(Variable var: variablesOfScope){
            if (var.getName().equals(varName)){
                return var;
            }
        }
        if (this.hasFather){
            return father.getVariableByName(varName);
        }
        throw new NoSuchVariableException(varName);
    }

    public void addMethod(Method toAdd){
        methodsOfScope.add(toAdd);
    }

    public boolean containsVar(String varName){
        for (Variable var:variablesOfScope){
            if (var.getName().equals(varName)){
                return true;
            }
        }
        if (this.hasFather){
            return father.containsVar(varName);
        }
        else{
            return false;
        }

    }

    /**
     * A method that checks if there is access from the scope to a method by it's name.
     * @param methodName the name of the method that we are looking for.
     * @return true if there is access, false otherwise.
     */
    public boolean containsMethod(String methodName){
        for (Method method: methodsOfScope) {
            if (method.getMethodName().equals(methodName)) {
                return true;
            }
        }
        if (hasFather){
            return father.containsMethod(methodName);
        }
        else{
            return false;
        }
    }

    /**
     * A method that returns a method by it's name, if there is no access form this scope than an
     * exception is thrown.
     * @param methodName the name of the method to return
     * @return a method with name of the methodName we are looking for.
     * @throws NoSuchMethodException if there is no access to a method with this name from this scope.
     */
    public Method getMethodByName(String methodName) throws NoSuchMethodException{
        if (containsMethod(methodName)){
            for (Method method: methodsOfScope){
                if (method.getMethodName().equals(methodName)){
                    return method;
                }
            }
            if (hasFather){
                return father.getMethodByName(methodName);
            }
            else {
                throw new NoSuchMethodException(methodName);
            }
        }
        else {
            throw new NoSuchMethodException(methodName);
        }
    }



}
