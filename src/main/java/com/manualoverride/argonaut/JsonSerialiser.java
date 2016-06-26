package com.manualoverride.argonaut;

import java.lang.reflect.Type;

public interface JsonSerialiser {
    void registerType(Class<?> type);

    void registerType(String typeName, Type type);

    Type getRegisteredType(String typeName);

    String getRegisteredTypeName(Type type);

    String serialise(Object content);

    Object deserialise(String json);
}
