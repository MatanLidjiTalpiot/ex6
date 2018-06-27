package oop.ex6.foundation;

import oop.ex6.foundation.exceptions.FileException;

/**
 * An interface that makes an object checkable
 */
public interface Checkable {

    boolean check(Scope scope)throws FileException;

    TypesOfCheckable getTypeOfCheckable();
}


