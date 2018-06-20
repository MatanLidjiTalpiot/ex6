package validator;

public class ValidatorMethodBlock implements ValidatorStrategy {
    private static ValidatorMethodBlock ourInstance = new ValidatorMethodBlock();

    public static ValidatorMethodBlock getInstance() {
        return ourInstance;
    }

    private ValidatorMethodBlock() {
    }
}
