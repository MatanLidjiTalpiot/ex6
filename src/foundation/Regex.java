package foundation;
import java.util.regex.*;

public class Regex {

    static Matcher isStartBlockLine(String str) {
        Pattern ptrn = Pattern.compile("^(.+)[{]$");
        Matcher matcher = ptrn.matcher(str);
        matcher.matches();
        return matcher;
    }

    static Matcher isConditionBlock(String str){
        Pattern ptrn = Pattern.compile("^\\s*(if|while)\\s*\\((.+)\\)\\s*");
        Matcher matcher = ptrn.matcher(str);
        matcher.matches();
        return matcher;
    }

    static Matcher isMethodBlock(String str){
        Pattern ptrn = Pattern.compile("^\\s*void\\s+([a-z A-Z]+\\w*)\\s*\\((.*)\\)\\s*$");
        Matcher matcher = ptrn.matcher(str);
        matcher.matches();
        return matcher;
    }

    static Matcher isAssimentLine(String str){
        Pattern ptrn = Pattern.compile("^\\s*([^\\d]+\\w+|[a-z A-Z])\\s*=\\s*(.*)\\s*;$");
        Matcher matcher = ptrn.matcher(str);
        matcher.matches();
        return matcher;
    }

    static Matcher isAssiment(String str){
        Pattern ptrn = Pattern.compile("^\\s*([^\\d]+\\w+|[a-z A-Z])\\s*=\\s*(.*)\\s*$");
        Matcher matcher = ptrn.matcher(str);
        matcher.matches();
        return matcher;
    }

    static Matcher isDeclerationLine(String str){
        Pattern ptrn = Pattern.compile("^\\s*(\\S+)\\s+(.+);\\s*$");
        Matcher matcher = ptrn.matcher(str);
        matcher.matches();
        return matcher;
    }

    static Matcher isFinalDeclerationLine(String str){
        Pattern ptrn = Pattern.compile("^\\s*final\\s*(\\S+)\\s+(.+);\\s*$");
        Matcher matcher = ptrn.matcher(str);
        matcher.matches();
        return matcher;
    }

    static Matcher isMethodCallLine(String str){
        Pattern ptrn = Pattern.compile("^([a-z A-Z]+\\w*)\\s*\\((.*)\\)\\s*;\\s*$");
        Matcher matcher = ptrn.matcher(str);
        matcher.matches();
        return matcher;
    }

    static boolean isEndBlockLine(String str){
        Pattern ptrn = Pattern.compile("^\\s*}\\s*$");
        return ptrn.matcher(str).matches();
    }

    static boolean isLineEmpthy(String str){
        Pattern ptrn = Pattern.compile("^\\s*$");
        return ptrn.matcher(str).matches();
    }

    static boolean isCommentLine(String str){
        Pattern ptrn = Pattern.compile("^\\s*\\\\\\\\$");
        return ptrn.matcher(str).matches();
    }

    static boolean isReturnLine(String str){
        Pattern ptrn = Pattern.compile("return\\s*;");
        return ptrn.matcher(str).matches();
    }

    static boolean isMethodName(String str){
        Pattern ptrn = Pattern.compile("^([a-z A-Z]+\\w*)$");
        return ptrn.matcher(str).matches();
    }

    static Matcher varDeclerationInMethodeBlock(String str){
        Pattern ptrn = Pattern.compile("^\\s*(final\\s+)?([S a-z]+)\\s+([^\\d]+\\w+|[a-z A-Z])$");
        Matcher matcher = ptrn.matcher(str);
        matcher.matches();
        return matcher;
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
        Pattern ptrn = Pattern.compile("[\\s]*-?[\\d]+[\\s]*");
        return ptrn.matcher(str).matches();
    }

    static boolean isDoubleValue(String str){
        Pattern ptrn = Pattern.compile("[\\s]*-?[\\d]+.[\\d]*[\\s]*");
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
        Matcher matcher = ptrn.matcher(str);
        matcher.matches();
        return matcher;
    }











}
//TODO - figure out how and if the ststic functions are cool ; also whitespace and \n are cool ?
