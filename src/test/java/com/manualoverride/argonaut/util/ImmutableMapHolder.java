package com.manualoverride.argonaut.util;

import com.google.common.collect.ImmutableMap;

import java.util.Objects;

import static com.google.common.base.Objects.toStringHelper;

public class ImmutableMapHolder {
    private final ImmutableMap<String, Long> map;

    public ImmutableMapHolder(ImmutableMap<String, Long> map) {
        this.map = map;
    }

    public ImmutableMap<String, Long> getMap() {
        return map;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ImmutableMapHolder other = (ImmutableMapHolder) obj;
        return Objects.equals(this.map, other.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("map", map)
                .toString();
    }
}
