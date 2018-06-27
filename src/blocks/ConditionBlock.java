package blocks;
import foundation.*;
import foundation.exceptions.*;

import java.util.LinkedList;

/**
 * A class that represents a Condition Block.
 */
public class ConditionBlock extends Block implements Checkable{
    private static TypesOfCheckable typeOfCheckable = TypesOfCheckable.CONDITION_BLOCK;
    private LinkedList<String> conditions = new LinkedList<>();
    /**
     * A constructor for the ConditionBlock.
     * @param conditions A linked list of conditions supposed to be variables - if it is a number or
     *                   (true\false) it doesn't matter.
     * @param fatherScope - the scope that the method is within.
     */
    public ConditionBlock(LinkedList<String> conditions, Scope fatherScope)throws InvalidConditionException,
            InvalidPlacementForCondition{
        super(fatherScope);
        if(!fatherScope.hasFather()){
            throw new InvalidPlacementForCondition("can't place condition here");
        }
        this.conditions = conditions;

    }



    /**
     * A method that checks the validity of the conditions.
     * @param conditions A linked list of conditions.
     * @throws InvalidConditionException thrown if the condition is not valid.
     */
    private void checkConditions(LinkedList<String> conditions)throws FileException{
        for (String condition:conditions){
                if (Type.isType(condition)) {
                    Type conditionType = Type.getTypeOf(condition);
                    if (!isValidCondition(conditionType)) {
                        throw new InvalidConditionException(condition);
                    }
                } else if (!fatherScope.containsVar(condition) || !fatherScope.getVariableByName(condition)
                        .isAssigned()) {
                    throw new InvalidConditionException(condition);
                }
                else{
                    Variable var = fatherScope.getVariableByName(condition);
                    if(var.getType() == Type.STRING || var.getType() == Type.CHAR){
                        throw new InvalidConditionException(condition);
                    }
                }
            }

        }

    /**
     * A method that checks if the condition is from the right type.
     * @param conditionType the type of the condition
     * @return true if the type is a type that can be in a condition, false otherwise.
     */
    private boolean isValidCondition(Type conditionType){
        return(conditionType == Type.BOOLEAN || conditionType == Type.DOUBLE || conditionType == Type.INT);
    }

    public boolean check(Scope scope)throws FileException{
        checkConditions(this.conditions);// note that this throws an Exception
        return super.check(this.scope);
    }


    @Override
    public TypesOfCheckable getTypeOfCheckable(){
        return typeOfCheckable;
    }

}
