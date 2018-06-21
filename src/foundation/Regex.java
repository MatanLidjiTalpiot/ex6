package foundation;
import java.util.regex.*;

public class Regex {

    static Pattern firstWordInLine = Pattern.compile("^([^\\s]+).*$");

    public static Pattern declerationLine(Type type) {
        return Pattern.compile(Type.typeToStr(type)+"\\s+([^\\d]+\\w+|[a-z A-Z])\\s*;\\s*$");
    }

    public static Pattern finalDeclerationLine(Type type) {
        return Pattern.compile("^final\\s"+Type.typeToStr(type)+"\\s+([^\\d]+\\w+|[a-z A-Z])\\s*;\\s*$");
    }

    static Pattern methodCallLine = Pattern.compile("^([a-z A-Z]+\\w*)\\s*\\((.*)\\)\\s*;\\s*$");

    static Pattern isMethodName = Pattern.compile("^([a-z A-Z]+\\w*)$");

    static Pattern isVariableName = Pattern.compile("^([^\\d]+\\w+|[a-z A-Z])$");

    static Pattern isStartBlockLine = Pattern.compile("^(.+)[{]$");

    static Pattern isIfBlock = Pattern.compile("^if\\s*\\((.+)\\)\\s");

    static Pattern isWhileBlock = Pattern.compile("^while\\s*\\((.+)\\)\\s*");

    static Pattern isMethodBlock = Pattern.compile("^\\s*([a-z A-Z]+\\w*)\\s*\\((.*)\\)\\s*;\\s*$");

    public static Pattern declerationLineForMethodBlock(Type type) {
        return Pattern.compile(Type.typeToStr(type)+"\\s+([^\\d]+\\w+|[a-z A-Z])\\s*$");
    }

    static Pattern isBooleanValue = Pattern.compile("(\\s*true\\s*|\\s*false\\s*)");

    static Pattern isIntValue = Pattern.compile("\\s*\\d+\\s*");

    static Pattern isDoubleValue = Pattern.compile("\\s*\\d+.\\d*\\s*");

    static Pattern isStringValue = Pattern.compile("\\s*\".*\"\\s*");

    static Pattern isCharValue = Pattern.compile("\\s*\".?\"\\s*");











}
//TODO - figure out how and if the ststic functions are cool ; also whitespace and \n are cool ?
