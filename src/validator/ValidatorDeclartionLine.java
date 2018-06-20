package validator;

public class ValidatorDeclartionLine implements ValidatorStrategy {
    private static ValidatorDeclartionLine ourInstance = new ValidatorDeclartionLine();

    public static ValidatorDeclartionLine getInstance() {
        return ourInstance;
    }

    private ValidatorDeclartionLine() {
    }
}
