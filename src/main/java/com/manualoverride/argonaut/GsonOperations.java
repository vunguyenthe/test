package com.manualoverride.argonaut;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;

public class GsonOperations implements JsonOperations<JsonElement, JsonObject> {
    private final JsonParser jsonParser = new JsonParser();
    private final Gson gson;

    public GsonOperations() {
        this(new Gson());
    }

    public GsonOperations(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String toJson(Object value) {
        return gson.toJson(value);
    }

    @Override
    public JsonElement fromJson(String json) {
        return jsonParser.parse(json);
    }

    @Override
    public JsonElement toElement(Object value) {
        return gson.toJsonTree(value);
    }

    @Override
    public Object fromElement(JsonElement element, Type type) {
        return gson.fromJson(element, type);
    }

    @Override
    public String fromElementToJson(JsonElement element) {
        return gson.toJson(element);
    }

    @Override
    public String fromObjectToJson(JsonObject object) {
        return fromElementToJson(object);
    }

    @Override
    public boolean isJsonObject(JsonElement element) {
        return element.isJsonObject();
    }

    @Override
    public boolean isString(JsonElement element) {
        return element.isJsonPrimitive() && element.getAsJsonPrimitive().isString();
    }

    @Override
    public JsonObject getAsJsonObject(JsonElement element) {
        return element.getAsJsonObject();
    }

    @Override
    public String getAsString(JsonElement element) {
        return element.getAsString();
    }

    @Override
    public JsonElement getField(JsonObject object, String field) {
        return object.get(field);
    }

    @Override
    public void setField(JsonObject object, String field, JsonElement value) {
        object.add(field, value);
    }
}
