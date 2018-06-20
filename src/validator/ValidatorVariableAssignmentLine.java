package validator;

public class ValidatorVariableAssignmentLine implements ValidatorStrategy {
    private static ValidatorVariableAssignmentLine ourInstance = new ValidatorVariableAssignmentLine();

    public static ValidatorVariableAssignmentLine getInstance() {
        return ourInstance;
    }

    private ValidatorVariableAssignmentLine() {
    }
}
