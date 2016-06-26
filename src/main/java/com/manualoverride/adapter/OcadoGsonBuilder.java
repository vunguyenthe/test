package com.manualoverride.adapter;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.gson.GsonBuilder;

/**
 * @author barnaby
 */
public class OcadoGsonBuilder {

    public static GsonBuilder getGsonBuilder() {

        return new GsonBuilder()
                .registerTypeAdapter(ImmutableMap.class, new ImmutableMapJsonDeserializer())
                .registerTypeAdapter(ImmutableList.class, new ImmutableListJsonDeserializer())
                .registerTypeAdapter(ImmutableSet.class, new ImmutableSetJsonDeserializer())
                .enableComplexMapKeySerialization()
                .disableHtmlEscaping()
                .serializeNulls();
    }

}
