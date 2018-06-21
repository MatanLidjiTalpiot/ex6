package blocks;
import foundation.Exceptions.NoSuchVariableException;
import foundation.Scope;

import java.util.LinkedList;

/**
 * A class that represents a Condition Block.
 */
public class ConditionBlock extends Block {

    private LinkedList<String> conditions;

    /**
     * A constructor for the ConditionBlock.
     * @param conditions A linked list of conditions supposed to be variables - if it is a number or
     *                   (true\false) it doesn't matter.
     * @param fatherScope - the scope that the method is within.
     */
    public ConditionBlock(LinkedList<String> conditions, Scope fatherScope)throws NoSuchVariableException{
        super(fatherScope);
        this.conditions = conditions;
        checkConditions(this.conditions);
    }
    public ConditionBlock(Scope fatherScope){
        super(fatherScope);
    }


    /**
     * A method that checks the validity of the conditions.
     * @param conditions A linked list of conditions.
     * @throws NoSuchVariableException
     */
    private void checkConditions(LinkedList<String> conditions)throws NoSuchVariableException{
        for (String condition:conditions){
            if (!fatherScope.contains(condition)){
                throw new NoSuchVariableException(condition);
            }
        }
    }
}
