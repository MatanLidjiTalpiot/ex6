package foundation;
import blocks.MethodBlock;
import foundation.exceptions.ParametersDontMatchException;
import java.util.LinkedList;

/**
 * A class that represents a Method.
 */
public class Method {
    private LinkedList<Variable> inputVariables = new LinkedList<>();
    private String methodName;
    private int numberOfVals;
    private MethodBlock methodBlock;
    /**
     * A constructor for A method object
     * @param name the name of the method
     * @param inputVariables the input variables the method demands.
     */
    public Method(String name, LinkedList<Variable> inputVariables, MethodBlock methodBlock){
        this.methodName = name;
        this.inputVariables = inputVariables;
        this.numberOfVals = inputVariables.size();
        this.methodBlock = methodBlock;
    }

    /**
     * A method that Matches the call to the method call
     * @param typesToMatch the variables in the call
     * @return true if the match is right, false otherwise.
     */
    public boolean callingMatching(LinkedList<Type> typesToMatch) throws ParametersDontMatchException{
        numberOfVals = inputVariables.size();
        if (numberOfVals != typesToMatch.size()){//if the number of variables in the input is
            // different than the number the method calls - throw exception.
            throw new ParametersDontMatchException("number of parameters does not match");
        }
        for (int i = 0; i < numberOfVals; i++){
            if (inputVariables.get(i).getType() != typesToMatch.get(i)){// if the types of
                // two variables in the same place doesn't match - throw exception
                throw new ParametersDontMatchException("the parameter in the " + i + "place does not match" +
                        " the method parameter in the " + i + "place");
            }
        }
        return true;
    }

    public String getMethodName() {
        return methodName;
    }

    public LinkedList<Variable> getInputVariables() {
        return inputVariables;
    }

    public MethodBlock getMethodBlock() {
        return methodBlock;
    }
}
