package com.example.kafkaexperimentation.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * An object that represents a UserEvent that we will use in our Kafka producer and consumer.
 */
@Setter
@Getter
@Builder
// no arg constructor for jackson
@NoArgsConstructor
@AllArgsConstructor
public class UserEventV2 {
    // Added for the analytics team
    private static final String event_source = "user_event_service";

    private UUID userId;
    private String eventName;
    private LocalDateTime eventDatetime;

    // A custom attribute map. Users can add their own attributes, and some are set by the service app owners.
    private Map<String, String> eventAttrMap;

    /**
     * Validates the core fields of the event. Throws an exception if invalid.
     */
    public void validate() {
        if (userId == null || eventName == null || eventName.trim().isEmpty() || eventDatetime == null) {
            throw new IllegalStateException("Event is missing required fields.");
        }
    }

}
