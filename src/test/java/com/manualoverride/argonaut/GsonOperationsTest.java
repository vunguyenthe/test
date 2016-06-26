package com.manualoverride.argonaut;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.gson.*;
import com.manualoverride.adapter.ImmutableListJsonDeserializer;
import com.manualoverride.adapter.ImmutableSetJsonDeserializer;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class GsonOperationsTest extends JsonOperationsSerialiserTest<JsonElement, JsonObject> {

    public GsonOperationsTest() {

        super(new GsonOperations(createGson()));
    }

    private static Gson createGson() {

        return new GsonBuilder()
                .registerTypeAdapter(ImmutableMap.class, new JsonDeserializer<ImmutableMap<?, ?>>() {

                    @Override
                    public ImmutableMap<?, ?> deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {

                        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                        Type mapType = createHashMapType(actualTypeArguments);
                        Map<?, ?> map = context.deserialize(json, mapType);
                        return ImmutableMap.copyOf(map);
                    }
                })
                .registerTypeAdapter(ImmutableList.class, new ImmutableListJsonDeserializer())
                .registerTypeAdapter(ImmutableSet.class, new ImmutableSetJsonDeserializer())
                .create();

    }

    private static Type createHashMapType(final Type[] actualTypeArguments) {

        return new ParameterizedType() {

            @Override
            public Type[] getActualTypeArguments() {

                return actualTypeArguments;
            }

            @Override
            public Type getRawType() {

                return HashMap.class;
            }

            @Override
            public Type getOwnerType() {

                return null;
            }
        };
    }
}
