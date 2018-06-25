package foundation;
import java.util.regex.*;

public class Regex {

    static Matcher isStartBlockLine(String str) {
        Pattern ptrn = Pattern.compile("^(.+)[{]$");
        return ptrn.matcher(str);
    }

    static Matcher isConditionBlock(String str){
        Pattern ptrn = Pattern.compile("^(if|while)\\s*\\((.+)\\)\\s*");
        return ptrn.matcher(str);
    }

    static Matcher isMethodBlock(String str){
        Pattern ptrn = Pattern.compile("^\\s*void\\s+([a-z A-Z]+\\w*)\\s*\\((.*)\\)\\s*$");
        return ptrn.matcher(str);
    }

    static Matcher isAssimentLine(String str){
        Pattern ptrn = Pattern.compile("^\\s*([^\\d]+\\w+|[a-z A-Z])\\s*=\\s*(.*)\\s*;$");
        return ptrn.matcher(str);
    }
    static Matcher isAssiment(String str){
        Pattern ptrn = Pattern.compile("^\\s*([^\\d]+\\w+|[a-z A-Z])\\s*=\\s*(.*)\\s*$");
        return ptrn.matcher(str);
    }

    static Matcher isDeclerationLine(String str){
        Pattern ptrn = Pattern.compile("^\\s*(\\S+)\\s+(.+);\\s*$");
        return ptrn.matcher(str);
    }

    static Matcher isFinalDeclerationLine(String str){
        Pattern ptrn = Pattern.compile("^\\s*final\\s*(\\S+)\\s+(.+);\\s*$");
        return ptrn.matcher(str);
    }

    static Matcher isMethodCallLine(String str){
        Pattern ptrn = Pattern.compile("^([a-z A-Z]+\\w*)\\s*\\((.*)\\)\\s*;\\s*$");
        return ptrn.matcher(str);
    }

    static boolean isEndBlockLine(String str){
        Pattern ptrn = Pattern.compile("^\\s*}\\s*$");
        return ptrn.matcher(str).matches();
    }

    static Matcher isLineEmpthy(String str){
        Pattern ptrn = Pattern.compile("^\\s*$");
        return ptrn.matcher(str);
    }

    static Matcher isCommentLine(String str){
        Pattern ptrn = Pattern.compile("^\\s*\\\\\\\\$");
        return ptrn.matcher(str);
    }

    static boolean isMethodName(String str){
        Pattern ptrn = Pattern.compile("^([a-z A-Z]+\\w*)$");
        return ptrn.matcher(str).matches();
    }

    static Matcher varDeclerationInMethodeBlock(String str){
        Pattern ptrn = Pattern.compile("^\\s*(final\\s+)?([S a-z]+)\\s+([^\\d]+\\w+|[a-z A-Z])$");
        return ptrn.matcher(str);
    }

    static boolean isVariableName(String str){
        Pattern ptrn = Pattern.compile("^([^\\d]+\\w+|[a-z A-Z])$");
        return ptrn.matcher(str).matches();
    }

    static boolean isBooleanValue(String str){
        Pattern ptrn = Pattern.compile("(\\s*true\\s*|\\s*false\\s*)");
        return ptrn.matcher(str).matches();
    }

    static boolean isIntValue(String str){
        Pattern ptrn = Pattern.compile("\\s*\\d+\\s*");
        return ptrn.matcher(str).matches();
    }

    static boolean isDoubleValue(String str){
        Pattern ptrn = Pattern.compile("\\s*\\d+.\\d*\\s*");
        return ptrn.matcher(str).matches();
    }

    static boolean isStringValue(String str){
        Pattern ptrn = Pattern.compile("\\s*\".*\"\\s*");
        return ptrn.matcher(str).matches();
    }

    static boolean isCharValue(String str){
        Pattern ptrn = Pattern.compile("\\s*\".?\"\\s*");
        return ptrn.matcher(str).matches();
    }

    static Matcher stringFinder(String str){
        Pattern ptrn = Pattern.compile("\"(.*?)\"");
        return ptrn.matcher(str);
    }











}
//TODO - figure out how and if the ststic functions are cool ; also whitespace and \n are cool ?
