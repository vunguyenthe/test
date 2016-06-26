package com.manualoverride.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.OptionalInt;

public class OptionalIntTypeAdapter extends TypeAdapter<OptionalInt> {
    @Override
    public void write(JsonWriter out, OptionalInt optional) throws IOException {
        if (optional.isPresent()) {
            int value = optional.getAsInt();
            out.value(value);
        } else {
            out.nullValue();
        }
    }

    @Override
    public OptionalInt read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL) {
            in.nextNull();
            return OptionalInt.empty();
        }

        int value = in.nextInt();
        return OptionalInt.of(value);
    }
}
