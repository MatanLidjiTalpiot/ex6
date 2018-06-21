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
}
