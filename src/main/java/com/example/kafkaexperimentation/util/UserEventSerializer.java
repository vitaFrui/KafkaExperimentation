package com.example.kafkaexperimentation.util;

import com.example.kafkaexperimentation.model.UserEvent;
import org.apache.kafka.common.serialization.Serializer;

/**
 * Implemented serializer that can be configured using application.properties
 */
public class UserEventSerializer implements Serializer<UserEvent> {
    @Override
    public byte[] serialize(final String s, final UserEvent userEvent) {
        return SerDeGenerator.USER_EVENT_SERDE.serializer().serialize(s, userEvent);
    }
}
