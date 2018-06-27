package oop.ex6.foundation;
import com.sun.deploy.security.ValidationState;
import oop.ex6.foundation.exceptions.InvalidTypeException;

/**
 * An enum class that represents all the types that are used in S java
 */

public enum Type {
    INT, DOUBLE, STRING, BOOLEAN, CHAR;

    /**
     * A method that matches a string to its type.
     * @param strToMatch the string to match
     * @return the type the the string represents.
     */
    public static Type strToType(String strToMatch) throws InvalidTypeException {
        strToMatch = strToMatch.trim();
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

    /**
     * A method that matches a type to its name in string.
     * @param type the string to match
     * @return the string representation of a type.
     */
    public static String typeToStr(Type type) throws InvalidTypeException {
        if (type == INT) {
            return "int";
        }
        if (type == DOUBLE) {
            return "double";
        }
        if (type == STRING) {
            return "String";
        }
        if (type == BOOLEAN) {
            return "boolean";
        }
        else{
            return "char";
        }
    }

    /**
     * A method that returns the type of a String, if the String doesn't have a type than it throws an
     * Invalid type exception.
     * @param toCheck the String value to check
     * @return the type that the String represents.
     */
    public static Type getTypeOf(String toCheck) throws InvalidTypeException{
        toCheck = toCheck.trim();
        if (Regex.isBooleanValue(toCheck))
            return BOOLEAN;

        if (Regex.isIntValue(toCheck))
            return INT;

        if (Regex.isDoubleValue(toCheck))
            return DOUBLE;

        if (Regex.isStringValue(toCheck))
            return STRING;

        if (Regex.isCharValue(toCheck))
            return CHAR;

        throw new InvalidTypeException(toCheck);
    }

    /**
     * A method that returns if a given value is a certain type.
     * @param toCheck the String value to check
     * @return true if it belongs to a type
     */
    public static boolean isType(String toCheck) throws InvalidTypeException{
        if (Regex.isBooleanValue(toCheck) || Regex.isIntValue(toCheck) || Regex.isDoubleValue(toCheck) ||
                Regex.isStringValue(toCheck)||Regex.isCharValue(toCheck))
            return true;
        return false;
    }

}


