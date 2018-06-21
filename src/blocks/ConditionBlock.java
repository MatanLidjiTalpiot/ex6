package blocks;
import foundation.Scope;

public class ConditionBlock extends Block {

    private String condition;

    public ConditionBlock(String Condition, Scope fatherScope){
        super(fatherScope);
        this.condition = condition;

    }
}
