package validator;
import foundation.Scope;
import lines.MethodCallLine;

public class ValidatorMethodCallLine {
    private static ValidatorMethodCallLine ourInstance = new ValidatorMethodCallLine();

    public static ValidatorMethodCallLine getInstance() {
        return ourInstance;
    }

    public boolean validate(Scope scope, MethodCallLine line)throws NoSuchMethodException{
        isMethod(scope, line);

    }

    private void isMethod(Scope scope, MethodCallLine line) throws NoSuchMethodException{
        scope.containsMethod(line.getMethodName());

    }

    private void checkParameters(Scope scope, MethodCallLine line)throws IllegalParametersException {}

    private ValidatorMethodCallLine() {
    }
}
