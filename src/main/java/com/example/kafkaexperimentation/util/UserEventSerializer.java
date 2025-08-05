package com.example.kafkaexperimentation.util;

import com.example.kafkaexperimentation.model.UserEventV2;
import org.apache.kafka.common.serialization.Serializer;

/**
 * Implemented serializer that can be configured using application.properties
 */
public class UserEventSerializer implements Serializer<UserEventV2> {
    @Override
    public byte[] serialize(final String s, final UserEventV2 userEvent) {
        return SerDeGenerator.USER_EVENT_SERDE.serializer().serialize(s, userEvent);
    }
}
