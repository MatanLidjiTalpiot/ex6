package validator;
import foundation.Checkable;
import foundation.Scope;

import java.util.LinkedList;

public interface ValidatorStrategy {

    boolean validate(Scope scope, Checkable toCheck);


}
