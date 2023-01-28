package com.example.kafkaexperimentation.util;

import com.example.kafkaexperimentation.model.UserEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

/**
 * Utility for generating generic SerDe objects.
 */
@Slf4j
@Getter
public class SerDeGenerator {
    public static final Serde<UserEvent> USER_EVENT_SERDE = SerDeGenerator.createSerDe(UserEvent.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
            // support Java 8 time (de)serialization
            .registerModule(new JavaTimeModule());

    /**
     * Creates a SerDe capable of (de)serializing the target class provided.
     *
     * @param target Target class
     * @return SerDe impl for the target class
     * @param <T> Target class type
     */
    public static <T> Serde<T> createSerDe(final Class<T> target) {
        return new Serde<>() {
            @Override
            public Serializer<T> serializer() {
                return (s, t) -> {
                    try {
                        return OBJECT_MAPPER.writeValueAsBytes(t);
                    } catch (final JsonProcessingException e) {
                        log.error("Could not serialize object of type {}, {}", target.getName(), t, e);
                        return null;
                    }
                };
            }

            @Override
            public Deserializer<T> deserializer() {
                return (s, bytes) -> {
                    try {
                        return OBJECT_MAPPER.readValue(bytes, target);
                    } catch (final IOException e) {
                        log.error("Could not deserialize object of type {} from bytes {}",target.getName(), bytes, e);
                        return null;
                    }
                };
            }
        };
    }
}
