package com.manualoverride.adapter;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class ImmutableListJsonDeserializer implements JsonDeserializer<ImmutableList<?>> {

    @Override
    public ImmutableList<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ParameterizedType parameterizedTypeOfT = (ParameterizedType) typeOfT;
        Type[] typeArguments = parameterizedTypeOfT.getActualTypeArguments();

        Type listType = new LinkedListType(typeArguments);
        List<?> list = context.deserialize(json, listType);
        return ImmutableList.copyOf(list);
    }

    private static class LinkedListType implements ParameterizedType {
        private final Type[] typeArguments;

        private LinkedListType(Type[] typeArguments) {
            this.typeArguments = typeArguments;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return typeArguments;
        }

        @Override
        public Type getRawType() {
            return LinkedList.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}
