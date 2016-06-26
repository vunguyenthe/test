package com.manualoverride.argonaut.util;

import com.google.common.base.Objects;

public class Immutable {
    private final String id;
    private final Integer quantity;
    private final boolean interesting;

    public Immutable(String id, Integer quantity, boolean interesting) {
        this.id = id;
        this.quantity = quantity;
        this.interesting = interesting;
    }

    public String getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public boolean isInteresting() {
        return interesting;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Immutable other = (Immutable) obj;
        return Objects.equal(this.id, other.id)
                && Objects.equal(this.quantity, other.quantity)
                && Objects.equal(this.interesting, other.interesting);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, quantity, interesting);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("quantity", quantity)
                .add("interesting", interesting)
                .toString();
    }
}
