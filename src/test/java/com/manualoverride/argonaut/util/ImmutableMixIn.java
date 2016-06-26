package com.manualoverride.argonaut.util;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ImmutableMixIn {
    public ImmutableMixIn(@JsonProperty("id") String id,
                          @JsonProperty("quantity") Integer quantity,
                          @JsonProperty("interesting") boolean interesting) {
    }
}
