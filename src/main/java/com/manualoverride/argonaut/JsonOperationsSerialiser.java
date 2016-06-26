package com.manualoverride.argonaut;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.Map;

public class JsonOperationsSerialiser<E, O> implements JsonSerialiser {
    public static final String TYPE_FIELD = "name";
    public static final String CONTENT_FIELD = "content";

    private static final Logger logger = LoggerFactory.getLogger(JsonOperationsSerialiser.class);

    private final BiMap<String, Type> registeredTypes = Maps.synchronizedBiMap(HashBiMap.<String, Type>create());

    private final JsonOperations<E, O> jsonOperations;

    public JsonOperationsSerialiser(JsonOperations<E, O> jsonOperations) {
        this.jsonOperations = jsonOperations;
    }

    @Override
    public final void registerType(Class<?> type) {
        registerType(type.getSimpleName(), type);
    }

    @Override
    public final void registerType(String typeName, Type type) {
        registeredTypes.put(typeName, type);
        logger.info("Registered type [" + type + "] with name [" + typeName + "]");
    }

    @Override
    public final Type getRegisteredType(String typeName) {
        return registeredTypes.get(typeName);
    }

    @Override
    public final String getRegisteredTypeName(Type type) {
        return registeredTypes.inverse().get(type);
    }

    @Override
    public String serialise(Object content) {
        Class<?> type = content.getClass();

        String typeName = getRegisteredTypeName(type);
        if (typeName == null) {
            logger.info("No type name found for type [" + type + "]");
            return null;
        }

        Map<String, Object> value = createValueToSerialise(typeName, content);
        String json = jsonOperations.toJson(value);
        if (json == null) {
            return null;
        }

        logger.debug("Serialised content [" + content + "] to JSON [" + json + "]");
        return json;
    }

    @Override
    public Object deserialise(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }

        E element = jsonOperations.fromJson(json);
        if (element == null || !jsonOperations.isJsonObject(element)) {
            logger.info("The String [" + json + "] could not be parsed to a JSON object");
            return null;
        }

        O object = jsonOperations.getAsJsonObject(element);
        E typeElement = jsonOperations.getField(object, TYPE_FIELD);
        if (!jsonOperations.isString(typeElement)) {
            logger.info("The type element [" + typeElement + "] is not a String");
            return null;
        }

        String typeName = jsonOperations.getAsString(typeElement);
        Type type = getRegisteredType(typeName);
        if (type == null) {
            logger.info("No type found for type name [" + typeName + "]");
            return null;
        }

        E contentElement = jsonOperations.getField(object, CONTENT_FIELD);
        Object result = jsonOperations.fromElement(contentElement, type);
        logger.debug("Deserialised content from JSON [" + json + "] to [" + result + "]");
        return result;
    }

    private Map<String, Object> createValueToSerialise(String typeName, Object content) {
        return ImmutableMap.<String, Object>builder()
                .put(TYPE_FIELD, typeName)
                .put(CONTENT_FIELD, content)
                .build();
    }
}
