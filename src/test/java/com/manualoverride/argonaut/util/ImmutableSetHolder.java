package com.manualoverride.argonaut.util;

import com.google.common.collect.ImmutableSet;

public class ImmutableSetHolder {

    private ImmutableSet<String> immutableSet;

    public ImmutableSetHolder(ImmutableSet<String> immutableSet) {

        this.immutableSet = immutableSet;
    }

    public ImmutableSetHolder() {

    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((immutableSet == null) ? 0 : immutableSet.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ImmutableSetHolder)) {
            return false;
        }
        ImmutableSetHolder other = (ImmutableSetHolder) obj;
        if (immutableSet == null) {
            if (other.immutableSet != null) {
                return false;
            }
        } else if (!immutableSet.equals(other.immutableSet)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "ImmutableSetHolder [immutableSet=" + immutableSet + "]";
    }

    /**
     * @return the immutableSet
     */
    public ImmutableSet<String> getImmutableSet() {

        return immutableSet;
    }

}
