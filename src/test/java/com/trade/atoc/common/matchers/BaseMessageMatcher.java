package com.trade.atoc.common.matchers;

/**
 * This Interface helps subclasses to create the match conditions to compare expected Object and targetObject for testing.
 *
 * @author khoabui
 */

public abstract class BaseMessageMatcher<E> {

    protected final E actualObject;

    public BaseMessageMatcher(final E actualObject) {

        this.actualObject = actualObject;
    }

    /**
     * This match will compare all of the contents of actualObject match with expectedObject
     *
     * @param expectedObject
     * @return true if match else return false
     */
    public abstract boolean match(E expectedObject);

    public E getActualObject() {

        return this.actualObject;
    }
}
