package validator;
import foundation.Exceptions.IllegalParametersException;
import foundation.Method;
import foundation.Scope;
import foundation.Type;
import foundation.Variable;
import lines.MethodCallLine;
import java.util.LinkedList;

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

    private LinkedList<Type> checkParameters(Scope scope, MethodCallLine line)throws
            IllegalParametersException{
        LinkedList<String> parameters = line.getParamsByOrder();
        LinkedList<Type> validTypesByOrder = new LinkedList<>();
        for (int i = 0; i< parameters.size(); i++){
            String parameter = parameters.get(i);
            if (Type.isType(parameter)){
                Type type = Type.getTypeOf(parameter);
                validTypesByOrder.add(i,type);
            }
            else if(scope.containsVar(parameter))
        }




        }
    }

    private ValidatorMethodCallLine() {
    }
}
