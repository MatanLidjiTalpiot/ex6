package foundation;

import com.sun.deploy.security.ValidationState;
import foundation.Exceptions.InvalidTypeException;

/**
 * An enum class that represents all the types that are used in S java
 */

public enum Type {
    INT, DOUBLE, STRING, BOOLEAN, CHAR;

    /**
     * A method that matches a string to its type.
     * @param strToMatch the string to match
     * @return the type the the string represents, if the string does not represent a type - null.
     */
    public static Type strToType(String strToMatch) throws InvalidTypeException {
        if (strToMatch.equals("int")){
            return INT;
        }
        else if (strToMatch.equals("double")){
            return DOUBLE;
        }
        else if(strToMatch.equals("String")){
            return STRING;
        }
        else if(strToMatch.equals("boolean")){
            return BOOLEAN;
        }
        else if(strToMatch.equals("char")){
            return CHAR;
        }
        else throw new InvalidTypeException(strToMatch);
    }

    public static String typeToStr(Type type){
        if (type == INT){
            return "int";
        }
        else if (type == DOUBLE){
            return "double";
        }
        else if(type == STRING)){
            return "String";
        }
        else if(type == BOOLEAN)){
            return "boolean";
        }
        else if(type == CHAR)){
            return "char";
        }

    }
}


