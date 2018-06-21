package foundation;
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
        if (type == DOUBLE){
            return "double";
        }
        if(type == STRING)){
            return "String";
        }
        if(type == BOOLEAN)){
            return "boolean";
        }
        if(type == CHAR)){
            return "char";
        }

    }

    /**
     * A method that gets the type of a String
     * @param toCheck the String value to check
     * @return the type that the String represents.
     */
    public static Type getTypeOf(String toCheck){
        //TODO build this method
    }

    /**
     * A method that checks whether a string value has a type that it represents.
      * @param toCheck the String value to check
     * @return true if it does, false othrewise.
     */
    public static boolean isType(String toCheck){
        //TODO build this method
    }
}


