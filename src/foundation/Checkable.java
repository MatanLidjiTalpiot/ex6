package foundation;

import foundation.Exceptions.NoSuchVariableException;

public interface Checkable {

    boolean check(Scope scope)throws Exception;
}


