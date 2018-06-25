package validator;
import foundation.exceptions.*;
import foundation.Method;
import foundation.Scope;
import foundation.Type;
import foundation.exceptions.NoSuchMethodException;
import lines.MethodCallLine;
import java.util.LinkedList;

public class ValidatorMethodCallLine {
    private static ValidatorMethodCallLine ourInstance = new ValidatorMethodCallLine();

    public static ValidatorMethodCallLine getInstance() {
        return ourInstance;
    }

    public static boolean validate(Scope scope, MethodCallLine line)throws FileException{
            isMethod(scope, line);
            LinkedList<Type> validTypesByOrder = checkParameters(scope, line);
            Method method = scope.getMethodByName(line.getMethodName());
            return (method.callingMatching(validTypesByOrder) && method.getMethodBlock().check(method
                    .getMethodBlock().getScope()));
    }

    private static void isMethod(Scope scope, MethodCallLine line) throws NoSuchMethodException{
        scope.containsMethod(line.getMethodName());

    }

    private static LinkedList<Type> checkParameters(Scope scope, MethodCallLine line)throws
            IllegalParametersException{
        LinkedList<String> parameters = line.getParamsByOrder();
        LinkedList<Type> validTypesByOrder = new LinkedList<>();
        for (int i = 0; i< parameters.size(); i++) {
                String parameter = parameters.get(i);
            try {
                if (Type.isType(parameter)) {
                    Type type = Type.getTypeOf(parameter);//not supposed to throw an exception because it was
                    // checked beforehand
                    validTypesByOrder.add(i, type);
                } else if (scope.containsVar(parameter)) {
                    Type type = scope.getVariableByName(parameter).getType();//not supposed to throw an
                    // exception because it was checked beforehand.
                    if (!scope.getVariableByName(parameter).isAssigned()) {
                        throw new IllegalParametersException(parameter + "doesn't have an assigned value");
                    }
                    validTypesByOrder.add(i, type);
                } else {
                    throw new IllegalParametersException(parameter);
                }
            }
            catch (NoSuchVariableException e){
                throw new IllegalParametersException(parameter + "is not a var that can be accessed from " +
                        "the scope");
            }
            catch (InvalidTypeException e){
                throw new IllegalParametersException(parameter + "is not a type that can be used and is " +
                        "not a variable, this exception shouldn't be throwed becasue there was a check " +
                        "beforehand to insure that there is a type, we have a bug");
                //TODO remove this message after debugging.
            }
        }

            return validTypesByOrder;
        }




        }
