package blocks;
import foundation.*;
import validator.ValidatorBlock;
import validator.ValidatorStrategy;

import java.util.LinkedList;

/**
 * An abstract class that represents a block in the file.
 */
public class Block implements Checkable{
    protected Scope fatherScope;
    protected Scope scope;
    protected boolean hasFatherScope;
    protected LinkedList<Checkable> content;
    /**
     * A constructor for a block within a block
     * @param fatherScope
     */
    public Block(Scope fatherScope){
        this.fatherScope = fatherScope;
        hasFatherScope = true;
        this.scope = new Scope(fatherScope);
    }

    /**
     * A constructor for a block without a father.
     */
    public Block(){
        hasFatherScope = false;
        this.scope = new Scope();
    }
    /**
     * A method that allows addition to the content
     * @param toAdd A Checkable to add to the content.
     */
    public void addCheckable(Checkable toAdd){
        content.add(toAdd);
    }

    /**
     * A method that checks the validity of a block.
     * @param scope the scope that the block is in.
     * @return true if the block is valid, false otherwise.
     */
    public boolean check(Scope scope)throws Exception{
        int i =0;
        while(content.get(i).check(scope)){
            i++;
        }
        return (i == content.size());
    }

    public Scope getScope() {
        return scope;
    }
}
