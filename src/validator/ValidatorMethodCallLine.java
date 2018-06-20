package validator;

public class ValidatorMethodCallLine implements ValidatorStrategy {
    private static ValidatorMethodCallLine ourInstance = new ValidatorMethodCallLine();

    public static ValidatorMethodCallLine getInstance() {
        return ourInstance;
    }

    private ValidatorMethodCallLine() {
    }
}
