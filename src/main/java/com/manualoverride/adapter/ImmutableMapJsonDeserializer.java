package com.manualoverride.adapter;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

public class ImmutableMapJsonDeserializer implements JsonDeserializer<ImmutableMap<?, ?>> {

    @Override
    public ImmutableMap<?, ?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ParameterizedType parameterizedTypeOfT = (ParameterizedType) typeOfT;
        Type[] typeArguments = parameterizedTypeOfT.getActualTypeArguments();

        Type mapType = new LinkedHashMapType(typeArguments);
        Map<?, ?> map = context.deserialize(json, mapType);
        return ImmutableMap.copyOf(map);
    }

    private static class LinkedHashMapType implements ParameterizedType {
        private final Type[] typeArguments;

        private LinkedHashMapType(Type[] typeArguments) {
            this.typeArguments = typeArguments;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return typeArguments;
        }

        @Override
        public Type getRawType() {
            return LinkedHashMap.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
