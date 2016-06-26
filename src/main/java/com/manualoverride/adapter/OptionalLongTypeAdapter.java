package com.manualoverride.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.OptionalLong;

public class OptionalLongTypeAdapter extends TypeAdapter<OptionalLong> {
    @Override
    public void write(JsonWriter out, OptionalLong optional) throws IOException {
        if (optional.isPresent()) {
            long value = optional.getAsLong();
            out.value(value);
        } else {
            out.nullValue();
        }
    }

    @Override
    public OptionalLong read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return OptionalLong.empty();
        }

        long value = in.nextLong();
        return OptionalLong.of(value);
    }
}
