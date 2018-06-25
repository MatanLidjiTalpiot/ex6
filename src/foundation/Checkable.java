package foundation;

import foundation.exceptions.FileException;

public interface Checkable {

    boolean check(Scope scope)throws FileException;

    boolean isBlock();

    boolean isLine();

    TypesOfCheckable getTypeOfCheckable();
}


