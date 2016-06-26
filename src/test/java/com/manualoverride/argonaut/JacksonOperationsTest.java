package com.manualoverride.argonaut;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.manualoverride.argonaut.util.Immutable;
import com.manualoverride.argonaut.util.ImmutableMapHolder;
import com.manualoverride.argonaut.util.ImmutableMapHolderMixIn;
import com.manualoverride.argonaut.util.ImmutableMixIn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JacksonOperationsTest extends JsonOperationsSerialiserTest<JsonNode, ObjectNode> {
    public JacksonOperationsTest() {
        super(new JacksonOperations(createObjectMapper()));
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.addMixInAnnotations(Immutable.class, ImmutableMixIn.class);
        objectMapper.addMixInAnnotations(ImmutableMapHolder.class, ImmutableMapHolderMixIn.class);

        objectMapper.registerModule(new GuavaModule());

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.addHandler(new CustomDeserializationProblemHandler());

        return objectMapper;
    }

    private static class CustomDeserializationProblemHandler extends DeserializationProblemHandler {
        private static final Logger logger = LoggerFactory.getLogger(CustomDeserializationProblemHandler.class);

        @Override
        public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser jp, JsonDeserializer<?> deserializer, Object beanOrClass, String propertyName) throws IOException, JsonProcessingException {
            logger.warn("Encountered unknown property [" + propertyName + "] while deserialising to bean or class [" + beanOrClass + "]");

            return super.handleUnknownProperty(ctxt, jp, deserializer, beanOrClass, propertyName);
        }
    }
}
