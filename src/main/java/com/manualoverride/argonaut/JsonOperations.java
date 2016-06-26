package com.manualoverride.argonaut;

import java.lang.reflect.Type;

public interface JsonOperations<E, O> {
    String toJson(Object value);

    E fromJson(String json);

    E toElement(Object value);

    Object fromElement(E element, Type type);

    String fromElementToJson(E element);

    String fromObjectToJson(O object);

    boolean isJsonObject(E element);

    boolean isString(E element);

    O getAsJsonObject(E element);

    String getAsString(E element);

    E getField(O object, String field);

    void setField(O object, String field, E value);
}
