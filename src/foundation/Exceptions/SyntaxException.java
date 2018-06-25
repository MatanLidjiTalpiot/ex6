package foundation.Exceptions;

public class SyntaxException  extends Exception {
    public SyntaxException(int rowNumber) {
        super("The code is illegal.  The syntax error was in row" + String.valueOf(rowNumber));
    }
}