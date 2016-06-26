package com.manualoverride.argonaut;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.manualoverride.argonaut.util.Immutable;
import com.manualoverride.argonaut.util.ImmutableListHolder;
import com.manualoverride.argonaut.util.ImmutableMapHolder;
import com.manualoverride.argonaut.util.ImmutableSetHolder;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public abstract class JsonOperationsSerialiserTest<E, O> {

    private final JsonOperations<E, O> jsonOperations;
    private final JsonSerialiser serialiser;

    public JsonOperationsSerialiserTest(JsonOperations<E, O> jsonOperations) {

        this.jsonOperations = jsonOperations;
        this.serialiser = new JsonOperationsSerialiser<E, O>(jsonOperations);
    }

    @Test
    public void serialiseAndDeserialise_validInteger() {

        serialiser.registerType(Integer.class);

        int content = 1;
        Object deserialisedContent = serialiseAndDeserialise(content);

        assertEquals("Deserialised primitive value", content, deserialisedContent);
    }

    @Test
    public void serialiseAndDeserialise_validString() {

        serialiser.registerType(String.class);

        String content = "content test 1";
        Object deserialisedContent = serialiseAndDeserialise(content);

        assertEquals(content, deserialisedContent);
    }

    @Test
    public void serialiseAndDeserialise_validFloat() {

        serialiser.registerType(Float.class);

        Float content = 0.1f;
        Object deserialisedContent = serialiseAndDeserialise(content);

        assertEquals(content, deserialisedContent);
    }

    @Test
    public void serialiseAndDeserialise_validHashMap() {

        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("key1", "value1");
        hashMap.put("key2", "value2");
        hashMap.put("key3", "value3");
        serialiser.registerType(HashMap.class);

        Object deserialisedContent = serialiseAndDeserialise(hashMap);

        assertEquals("Deserialised map class instance", hashMap, deserialisedContent);
    }

    @Test
    public void serialiseAndDeserialise_validImmutable() {

        serialiser.registerType("CustomTypeName", Immutable.class);

        Immutable content = new Immutable("322", 12, true);
        Object deserialisedContent = serialiseAndDeserialise(content);

        assertEquals("Deserialised immutable class instance", content, deserialisedContent);
    }

    @Test
    public void serialise_notRegisterType_returnNull() {

        Immutable content1 = new Immutable("woo", 9835, true);
        Immutable content2 = new Immutable("woo", 9836, true);

        ImmutableList.Builder<Immutable> immutableListList = ImmutableList
                .builder();
        immutableListList.add(content1);
        immutableListList.add(content2);
        String deserialisedContent = serialiser.serialise(immutableListList);

        assertNull(deserialisedContent);
    }

    @Test
    public void serialise_registerWrongType_returnNull() {

        serialiser.registerType(String.class);
        // set a content as integer type
        Integer content = 0;
        String json = serialiser.serialise(content);
        // expected serialiser returns null value
        assertNull(json);
    }

    @Test
    public void deserialise_nullValue_returnNull() {

        Object deserialisedContent = serialiser.deserialise(null);
        assertNull(deserialisedContent);
    }

    @Test
    public void serialiseAndDeserialise_validImmutableMapHolder() {

        ImmutableMap.Builder<String, Long> content = ImmutableMap.builder();
        content.put("content 1", 001L);
        content.put("content 2", 002L);
        content.put("content 3", 003L);

        ImmutableMapHolder immutableMapHolder = new ImmutableMapHolder(content.build());

        serialiser.registerType(ImmutableMapHolder.class);
        String json = serialiser.serialise(immutableMapHolder);

        ImmutableMapHolder deserialisedContent = (ImmutableMapHolder) serialiser.deserialise(json);
        assertEquals("Deserialised immutable map holder", immutableMapHolder, deserialisedContent);
    }

    @Test
    public void serialiseAndDeserialise_validImmutableListHolder() {

        Immutable content1 = new Immutable("woo", 9835, true);
        Immutable content2 = new Immutable("woo", 9836, true);
        ImmutableList.Builder<Immutable> immutableListList = ImmutableList
                .builder();

        immutableListList.add(content1);
        immutableListList.add(content2);

        serialiser.registerType(ImmutableListHolder.class);
        ImmutableListHolder immutableListHolder = new ImmutableListHolder(immutableListList.build());
        String json = serialiser.serialise(immutableListHolder);

        ImmutableListHolder deserialisedContent = (ImmutableListHolder) serialiser.deserialise(json);
        assertEquals("Deserialised immutable list holder", immutableListHolder, deserialisedContent);
    }

    @Test
    public void serialiseAndDeserialise_validImmutableSetHolder() {

        String content1 = "content 1";
        String content2 = "content 2";

        ImmutableSet<String> immutableSet =
                new ImmutableSet.Builder<String>()
                        .add(content1)
                        .add(content2)
                        .build();

        serialiser.registerType(ImmutableSetHolder.class);
        ImmutableSetHolder immutableSetHolder = new ImmutableSetHolder(immutableSet);
        String json = serialiser.serialise(immutableSetHolder);

        ImmutableSetHolder deserialisedContent = (ImmutableSetHolder) serialiser.deserialise(json);
        assertEquals("Deserialised immutable set holder", immutableSetHolder, deserialisedContent);
    }

    @Test
    public void deserialise_unknownField() {

        serialiser.registerType(Immutable.class);

        Immutable content = new Immutable("woo", 9835, true);
        String json = serialiser.serialise(content);

        E jsonElement = jsonOperations.fromJson(json);
        O jsonObject = jsonOperations.getAsJsonObject(jsonElement);

        E contentJsonElement = jsonOperations.getField(jsonObject, JsonOperationsSerialiser.CONTENT_FIELD);
        O contentJsonObject = jsonOperations.getAsJsonObject(contentJsonElement);

        String unknownValue = "unknownValue";
        E unknownElement = jsonOperations.toElement(unknownValue);
        jsonOperations.setField(contentJsonObject, "unknownField", unknownElement);

        String jsonWithUnknownField = jsonOperations.fromObjectToJson(jsonObject);
        Object deserialisedContent = serialiser.deserialise(jsonWithUnknownField);

        assertEquals("Deserialised with unknown field", content, deserialisedContent);

    }

    @Test
    public void deserialise_emptyJsonString_returnNull() {

        Object deserialisedContent = serialiser.deserialise("");
        assertNull(deserialisedContent);
    }

    private Object serialiseAndDeserialise(Object content) {

        String json = serialiser.serialise(content);
        return serialiser.deserialise(json);
    }
}
