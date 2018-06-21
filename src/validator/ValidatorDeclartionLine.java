package validator;

public class ValidatorDeclartionLine {
    private static ValidatorDeclartionLine ourInstance = new ValidatorDeclartionLine();

    public static ValidatorDeclartionLine getInstance() {
        return ourInstance;
    }

    private ValidatorDeclartionLine() {
    }
}
