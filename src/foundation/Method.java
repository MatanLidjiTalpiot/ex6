package foundation;

import java.util.LinkedList;

/**
 * A class that represents a Method.
 */
public class Method {
    private LinkedList<Variable> inputVarialbes;
    private String methodName;
    private int numberOfVals;

    /**
     * A constructor for A method object
     * @param name the name of the method
     * @param inputVariables the input variables the method demands.
     */
    public Method(String name, LinkedList<Variable> inputVariables){
        this.methodName = name;
        this.inputVarialbes = inputVariables;
        this.numberOfVals = inputVariables.size();
    }

    /**
     * A method that Matches the call to the method call
     * @param variablesToMatch the variables in the call
     * @return true if the match is right, false otherwise.
     */
    public boolean callingMatching(LinkedList<Variable> variablesToMatch){
        numberOfVals = inputVarialbes.size();
        if (numberOfVals != variablesToMatch.size()){//if the number of variables in the input is
            // different than the number the method calls - return false.
            return false;
        }
        for (int i = 0; i < numberOfVals; i++){
            if (inputVarialbes.get(i).getType() != variablesToMatch.get(i).getType()){// if the types of
                // two variables in the same place doesn't match - return false.
                return false;
            }
        }
        return true;
    }

    public String getMethodName() {
        return methodName;
    }

    public LinkedList<Variable> getInputVariables() {
        return inputVarialbes;
    }
}
