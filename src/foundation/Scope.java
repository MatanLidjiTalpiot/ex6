package foundation;

import java.util.LinkedList;

/**
 * A class that represents a Scope.
 */
public class Scope {

    private LinkedList<Variable> variablesOfScope = new LinkedList<>();
    private Scope father;

    /**
     * A constructor for a foundation.Scope
     * @param father the scope that this scope is contained in
     */
    public Scope(Scope father){
       this.father = father;
    }


    public Scope getFather() {
        return father;
    }


    public void addVariable(Variable toAdd){
        variablesOfScope.add(toAdd);
    }
}
