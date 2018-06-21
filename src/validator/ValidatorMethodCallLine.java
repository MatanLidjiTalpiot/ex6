package validator;

public class ValidatorMethodCallLine {
    private static ValidatorMethodCallLine ourInstance = new ValidatorMethodCallLine();

    public static ValidatorMethodCallLine getInstance() {
        return ourInstance;
    }

    private ValidatorMethodCallLine() {
    }
}
