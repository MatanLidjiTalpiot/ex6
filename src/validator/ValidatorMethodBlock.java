package validator;

public class ValidatorMethodBlock{
    private static ValidatorMethodBlock ourInstance = new ValidatorMethodBlock();

    public static ValidatorMethodBlock getInstance() {
        return ourInstance;
    }

    private ValidatorMethodBlock() {
    }
}
