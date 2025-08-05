package com.example.kafkaexperimentation.util;

import com.example.kafkaexperimentation.model.UserEventV2;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * Implemented deserializer that can be configured using application.properties
 */
public class UserEventDeserializer implements Deserializer<UserEventV2> {
    @Override
    public UserEventV2 deserialize(final String s, final byte[] bytes) {
        return SerDeGenerator.USER_EVENT_SERDE.deserializer().deserialize(s, bytes);
    }
}
