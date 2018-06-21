package foundation;
import
/**
 * A class that represents a virable
 */
public class Variable {

    private String name;
    private Type type;
    private boolean isAssigned = false;
    private boolean isFinal;

    /**
     * A constructor for a virable
     * @param name the name of the virable
     * @param type the type of the virable
     */
    public Variable(String name, Type type, boolean isFinal){
        this.name = name;
        this.type = type;
        this.isFinal = isFinal;
    }



    public String getName(){
        return name;
    }

    public Type getType() {
        return type;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    /**
     * A method that checks if it is possible to assign a variable to into a different variable
     * @param toAssign the variable to check
     * @return true if it is possible, false otherwise.
     */
    public boolean canAssign(Variable toAssign) {
        if (isFinal){
            return false;
        }
        if (toAssign.getType() == this.type) {
            return true;
        }
        if (this.type == Type.DOUBLE && toAssign.getType() == Type.INT) {
            return true;
        }
        if (this.type == Type.STRING && toAssign.getType() == Type.CHAR) {
            return true;
        }
        if (this.type == Type.BOOLEAN && (toAssign.getType() == Type.INT || toAssign.getType() == Type.DOUBLE)){
            return true;
        }
        return false;
    }
}

