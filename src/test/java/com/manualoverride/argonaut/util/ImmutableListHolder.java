package com.manualoverride.argonaut.util;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class ImmutableListHolder {

    private ImmutableList<Immutable> immutableList;

    public ImmutableListHolder(List<Immutable> immutableList) {

        this.immutableList = ImmutableList.copyOf(immutableList);
    }

    public ImmutableListHolder() {

    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((immutableList == null) ? 0 : immutableList.hashCode());
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
        if (!(obj instanceof ImmutableListHolder)) {
            return false;
        }
        ImmutableListHolder other = (ImmutableListHolder) obj;
        if (immutableList == null) {
            if (other.immutableList != null) {
                return false;
            }
        } else if (!immutableList.equals(other.immutableList)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {

        return "ImmutableListHolder [immutableList=" + immutableList + "]";
    }

    /**
     * @return the immutableList
     */
    public ImmutableList<Immutable> getImmutableList() {

        return immutableList;
    }

}
