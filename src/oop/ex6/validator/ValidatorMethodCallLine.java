package oop.ex6.validator;
import oop.ex6.foundation.exceptions.*;
import oop.ex6.foundation.Method;
import oop.ex6.foundation.Scope;
import oop.ex6.foundation.Type;
import oop.ex6.foundation.exceptions.NoSuchMethodException;
import oop.ex6.lines.MethodCallLine;
import java.util.LinkedList;

/**
 * A singleton class the is composed in the method call line that does the check is delegated to.
 */
public class ValidatorMethodCallLine {
    private static ValidatorMethodCallLine ourInstance = new ValidatorMethodCallLine();

    public static ValidatorMethodCallLine getInstance() {
        return ourInstance;
    }

    /**
     * A method that does the check for a method call line.
     * @param scope the scope that the line is in.
     * @param line the method call line to check
     * @return true if the line is a legal line
     * @throws FileException exceptions that may accrue if the line is illegal.
     */
    public static boolean validate(Scope scope, MethodCallLine line)throws FileException{
            isMethod(scope, line);
            LinkedList<Type> validTypesByOrder = checkParameters(scope, line);
            Method method = scope.getMethodByName(line.getMethodName());
            if (method.hasBeenChecked()) {
                return (method.callingMatching(validTypesByOrder));
            }
            else {
                method.check();
                return (method.callingMatching(validTypesByOrder) && method.getMethodBlock().run(method
                        .getMethodBlock().getScope()));
            }
    }

    /**
     * A method that checks if the method that is called is accessible from the line that it was called in.
     * @param scope the scope that the method is called in.
     * @param line the method call line
     * @throws NoSuchMethodException an exception the means that there is no access.
     */
    private static void isMethod(Scope scope, MethodCallLine line) throws NoSuchMethodException{
        scope.containsMethod(line.getMethodName());

    }

    /**
     * A method that checks if the parameters in the method call line are compatible.
     * @param scope the scope that the line is in
     * @param line the method call line that we are checking
     * @return a linked list of the types of the parameters.
     * @throws FileException excpetion that may accrue that means that the parameters are not compatible.
     */
    private static LinkedList<Type> checkParameters(Scope scope, MethodCallLine line)throws
            FileException{
        LinkedList<String> parameters = line.getParamsByOrder();
        LinkedList<Type> validTypesByOrder = new LinkedList<>();
        for (int i = 0; i< parameters.size(); i++) {
                String parameter = parameters.get(i);
                if (Type.isType(parameter)) {
                    Type type = Type.getTypeOf(parameter);
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
                    throw new IllegalParametersException(parameter = "is not a var that can be accessed " +
                            "from the scope");
                }
        }

            return validTypesByOrder;
        }




        }
