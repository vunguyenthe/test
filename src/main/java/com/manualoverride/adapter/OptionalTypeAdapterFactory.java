package com.manualoverride.adapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

public class OptionalTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class<? super T> rawType = typeToken.getRawType();
        if (!Optional.class.isAssignableFrom(rawType)) {
            return null;
        }

        Type type = typeToken.getType();
        Type valueType = getValueType(type);
        TypeAdapter<?> valueTypeAdapter = gson.getAdapter(TypeToken.get(valueType));

        return new OptionalTypeAdapter(valueTypeAdapter);
    }

    private Type getValueType(Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            return typeArguments[0];
        } else {
            return Object.class;
        }
    }

    private static class OptionalTypeAdapter<V> extends TypeAdapter<Optional<V>> {
        private final TypeAdapter<V> valueTypeAdapter;

        private OptionalTypeAdapter(TypeAdapter<V> valueTypeAdapter) {
            this.valueTypeAdapter = valueTypeAdapter;
        }

        @Override
        public void write(JsonWriter out, Optional<V> optional) throws IOException {
            if (optional.isPresent()) {
                V value = optional.get();
                valueTypeAdapter.write(out, value);
            } else {
                out.nullValue();
            }
        }

        @Override
        public Optional<V> read(JsonReader in) throws IOException {
            V value = valueTypeAdapter.read(in);
            return Optional.ofNullable(value);
        }
    }
}
