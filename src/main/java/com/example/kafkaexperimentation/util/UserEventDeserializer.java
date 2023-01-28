package com.example.kafkaexperimentation.util;

import com.example.kafkaexperimentation.model.UserEvent;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * Implemented deserializer that can be configured using application.properties
 */
public class UserEventDeserializer implements Deserializer<UserEvent> {
    @Override
    public UserEvent deserialize(final String s, final byte[] bytes) {
        return SerDeGenerator.USER_EVENT_SERDE.deserializer().deserialize(s, bytes);
    }
}
