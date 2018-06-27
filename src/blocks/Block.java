package blocks;
import foundation.*;
import java.util.LinkedList;
import foundation.exceptions.FileException;
import lines.MethodCallLine;

/**
 * An abstract class that represents a block in the file.
 */
public class Block implements Checkable{
    protected Scope fatherScope;
    protected Scope scope;
    protected boolean hasFatherScope;
    protected LinkedList<Checkable> content = new LinkedList<>();
    private static TypesOfCheckable typeOfCheckable = TypesOfCheckable.BLOCK;



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
    public boolean check(Scope scope)throws FileException{
        LinkedList<Integer> uncheckedCheckables = new LinkedList<>();
        for (int i = 0; i < content.size(); i++) {
            if (content.get(i).getTypeOfCheckable() == TypesOfCheckable.METHOD_BLOCK) {
                uncheckedCheckables.add(i);
                continue;
            }
            content.get(i).check(scope);
        }
        for(int i : uncheckedCheckables){
            content.get(i).check(scope);
        }
        return (true);
    }

    public Scope getScope() {
        return scope;
    }

    public TypesOfCheckable getTypeOfCheckable() {
        return typeOfCheckable;
    }


}
