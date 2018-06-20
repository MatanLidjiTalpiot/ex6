package validator;

public class ValidorConditionBlock  implements ValidatorStrategy{
    private static ValidorConditionBlock ourInstance = new ValidorConditionBlock();

    public static ValidorConditionBlock getInstance() {
        return ourInstance;
    }

    private ValidorConditionBlock() {
    }
}
