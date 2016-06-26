package com.manualoverride.adapter;

import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author barnaby
 */
public class ImmutableSetJsonDeserializer implements JsonDeserializer<ImmutableSet<?>> {

    @Override
    public ImmutableSet<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ParameterizedType parameterizedTypeOfT = (ParameterizedType) typeOfT;
        Type[] typeArguments = parameterizedTypeOfT.getActualTypeArguments();

        Type setType = new TreeSetType(typeArguments);
        Set<?> set = context.deserialize(json, setType);
        return ImmutableSet.copyOf(set);
    }

    private static class TreeSetType implements ParameterizedType {

        private final Type[] typeArguments;

        private TreeSetType(Type[] typeArguments) {
            this.typeArguments = typeArguments;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return typeArguments;
        }

        @Override
        public Type getRawType() {
            return TreeSet.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
