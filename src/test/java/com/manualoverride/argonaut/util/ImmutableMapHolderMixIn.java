package com.manualoverride.argonaut.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;

public abstract class ImmutableMapHolderMixIn {
    public ImmutableMapHolderMixIn(@JsonProperty("map") ImmutableMap<String, Long> map) {
    }
}
