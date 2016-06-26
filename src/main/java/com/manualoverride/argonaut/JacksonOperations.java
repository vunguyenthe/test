package com.manualoverride.argonaut;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;

public class JacksonOperations implements JsonOperations<JsonNode, ObjectNode> {
    private static final Logger logger = LoggerFactory.getLogger(JacksonOperations.class);

    private final ObjectMapper objectMapper;

    public JacksonOperations() {
        this(new ObjectMapper());
    }

    public JacksonOperations(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String toJson(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            logger.error("Error serialising [" + value + "] to JSON", e);
            return null;
        }
    }

    @Override
    public JsonNode fromJson(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (IOException e) {
            logger.error("Error parsing JSON [" + json + "]", e);
            return null;
        }
    }

    @Override
    public JsonNode toElement(Object value) {
        return objectMapper.valueToTree(value);
    }

    @Override
    public Object fromElement(JsonNode element, Type type) {
        JavaType javaType = objectMapper.getTypeFactory().constructType(type);
        return objectMapper.convertValue(element, javaType);
    }

    @Override
    public String fromElementToJson(JsonNode element) {
        return element.toString();
    }

    @Override
    public String fromObjectToJson(ObjectNode object) {
        return fromElementToJson(object);
    }

    @Override
    public boolean isJsonObject(JsonNode element) {
        return element.isObject();
    }

    @Override
    public boolean isString(JsonNode element) {
        return element.isTextual();
    }

    @Override
    public ObjectNode getAsJsonObject(JsonNode element) {
        return (ObjectNode) element;
    }

    @Override
    public String getAsString(JsonNode element) {
        return element.textValue();
    }

    @Override
    public JsonNode getField(ObjectNode object, String field) {
        return object.get(field);
    }

    @Override
    public void setField(ObjectNode object, String field, JsonNode value) {
        object.set(field, value);
    }
}
