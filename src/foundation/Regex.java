package foundation;
import foundation.exceptions.InvalidTypeException;

import java.util.regex.*;

/**
 * This class is holding all the complex regexes for this code.
 */
public class Regex {
    /**
     * @param str - the string we want to match
     * @return a matcher for if is a start block line
     */
    static Matcher isStartBlockLine(String str) {
        Pattern ptrn = Pattern.compile("^(.+)[{]\\s*$");
        Matcher matcher = ptrn.matcher(str);
        matcher.matches();
        return matcher;
    }
    /**
     * @param str - the string we want to match
     * @return a matcher for if it is the start of a condition block(if aor while)
     */
    static Matcher isConditionBlock(String str){
        Pattern ptrn = Pattern.compile("^\\s*(if|while)\\s*\\((.+)\\)\\s*");
        Matcher matcher = ptrn.matcher(str);
        matcher.matches();
        return matcher;
    }
    /**
     * @param str - the string we want to match
     * @return a matcher for ifi s the start of a method deceleration block
     */
    static Matcher isMethodBlock(String str){
        Pattern ptrn = Pattern.compile("^\\s*void\\s+([a-zA-Z]+\\w*)\\s*\\(([a-zA-Z\\s_0-9,]*)\\)\\s*$");
        Matcher matcher = ptrn.matcher(str);
        matcher.matches();
        return matcher;
    }
    /**
     * @param str - the string we want to match
     * @return a matcher for if it is assignment line
     */
    static Matcher isAssimentLine(String str){
        Pattern ptrn = Pattern.compile("^\\s*([^\\d]+\\w+|[a-z A-Z])\\s*=\\s*(.*)\\s*;$");//^([a-z A-Z _]+\w+|[a-z A-Z]\w*)\s*=\s*(.+);$
        Matcher matcher = ptrn.matcher(str);
        matcher.matches();
        return matcher;
    }
    /**
     * @param str - the string we want to match
     * @return a matcher for if it is assignment
     */
    static Matcher isAssiment(String str){
        Pattern ptrn = Pattern.compile("^\\s*([^\\d]+\\w+|[a-z A-Z])\\s*=\\s*(.*)\\s*$");
        Matcher matcher = ptrn.matcher(str);
        matcher.matches();
        return matcher;
    }
    /**
     * @param str - the string we want to match
     * @return a matcher for if it is Decleration line
     */
    static Matcher isDeclerationLine(String str) throws InvalidTypeException {
        Type[] types = Type.values();
        String s = "";
        for (int i = 0; i<types.length -1;i++) {
            s += Type.typeToStr(types[i]);
            s += "|";
        }
        s += Type.typeToStr(types[types.length -1]);
        Pattern ptrn = Pattern.compile("^\\s*(" + s + ")\\s+([^;]+);\\s*$");
        Matcher matcher = ptrn.matcher(str);
        matcher.matches();
        return matcher;
    }
    /**
     * @param str - the string we want to match
     * @return a matcher for if it is final Decleration line
     */
    static Matcher isFinalDeclerationLine(String str){
        Pattern ptrn = Pattern.compile("^\\s*final\\s*(\\S+)\\s+(.+);\\s*$");
        Matcher matcher = ptrn.matcher(str);
        matcher.matches();
        return matcher;
    }
    /**
     * @param str - the string we want to match
     * @return a matcher for if it is a method call line
     */
    static Matcher isMethodCallLine(String str){
        Pattern ptrn = Pattern.compile("^([a-z A-Z]+\\w*)\\s*\\((.*)\\)\\s*;\\s*$");
        Matcher matcher = ptrn.matcher(str);
        matcher.matches();
        return matcher;
    }
    /**
     * @param str - the string we want to match
     * @return a matcher for if it is a valid variable deceleration line in an method block
     */
    static Matcher varDeclerationInMethodeBlock(String str){
        Pattern ptrn = Pattern.compile("^\\s*(final\\s+)?([S a-z]+)\\s+([^\\d]+\\w+|[a-z A-Z])$");
        Matcher matcher = ptrn.matcher(str);
        matcher.matches();
        return matcher;
    }
    /**
     * @param str - the string we want to match
     * @return true if it is an end block line
     */
    static boolean isEndBlockLine(String str){
        Pattern ptrn = Pattern.compile("^\\s*}\\s*$");
        return ptrn.matcher(str).matches();
    }
    /**
     * @param str - the string we want to match
     * @return true if is empthy line
     */
    static boolean isLineEmpthy(String str){
        Pattern ptrn = Pattern.compile("^\\s*$");
        return ptrn.matcher(str).matches();
    }
    /**
     * @param str - the string we want to match
     * @return true if it is a comment line
     */
    static boolean isCommentLine(String str){
        Pattern ptrn = Pattern.compile("^\\/\\/.*$");
        return ptrn.matcher(str).matches();
    }
    /**
     * @param str - the string we want to match
     * @return true if  it is a return line
     */
    static boolean isReturnLine(String str){
        Pattern ptrn = Pattern.compile("return\\s*;");
        return ptrn.matcher(str).matches();
    }
    /**
     * @param str - the string we want to match
     * @return true if it is an okay variable name
     */
    static boolean isVariableName(String str){
        Pattern ptrn = Pattern.compile("^([a-zA-Z_]+\\w+|[a-zA-Z]\\w*)$");
        return ptrn.matcher(str).matches();
    }
    /**
     * @param str - the string we want to match
     * @return true if is a boolean value
     */
    static boolean isBooleanValue(String str){
        Pattern ptrn = Pattern.compile("^(true|false)$");
        return ptrn.matcher(str.trim()).matches();
    }
    /**
     * @param str - the string we want to match
     * @return true if it is int value
     */
    static boolean isIntValue(String str){
        Pattern ptrn = Pattern.compile("^-?[\\d]+$");
        return ptrn.matcher(str.trim()).matches();
    }
    /**
     * @param str - the string we want to match
     * @return true if it is double value
     */
    static boolean isDoubleValue(String str){
        Pattern ptrn = Pattern.compile("^-?[\\d]+\\.[\\d]*$");
        return ptrn.matcher(str.trim()).matches();
    }
    /**
     * @param str - the string we want to match
     * @return true if it is a string value
     */
    static boolean isStringValue(String str){
        Pattern ptrn = Pattern.compile("^\".*\"$");
        return ptrn.matcher(str.trim()).matches();
    }
    /**
     * @param str - the string we want to match
     * @return true if it is char value
     */
    static boolean isCharValue(String str){
        Pattern ptrn = Pattern.compile("^\'.?\'$");
        return ptrn.matcher(str.trim()).matches();
    }
}

