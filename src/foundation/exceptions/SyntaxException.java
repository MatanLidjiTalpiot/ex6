package foundation.exceptions;

public class SyntaxException  extends FileException {
    public SyntaxException(int rowNumber) {
        super("The code is illegal.  The syntax error was in row" + String.valueOf(rowNumber));
    }
}