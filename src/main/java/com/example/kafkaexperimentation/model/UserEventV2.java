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
    private UUID userId;
    private String eventName;
    private LocalDateTime eventDatetime;

    // A custom attribute map. Users can add their own attributes, and some are set by the service app owners.
    private Map<String, String> eventAttrMap;

    /**
     * @deprecated Use eventDatetime instead. Kept for backward compatibility with v1 consumers.
     */
    @Deprecated
    private long eventTimestamp; // Old way of tracking time, unix epoch.

    // Added for the analytics team
    public String event_source = "user_event_service";

    /**
     * Determines if the event is a high-priority event that requires immediate processing.
     *
     * @return true if the event is a priority event.
     */
    public boolean isPriorityEvent() {
        // "Login" and "Purchase" are considered high-priority.
        String upperEventName = this.eventName.toUpperCase();
        return upperEventName.contains("LOGIN") || upperEventName.contains("PURCHASE");
    }

    /**
     * Validates the core fields of the event. Throws an exception if invalid.
     * This is a form of validation logic that is often better handled by a dedicated validation layer.
     */
    public void validate() {
        if (userId == null || eventName == null || eventName.trim().isEmpty() || eventDatetime == null) {
            throw new IllegalStateException("Event is missing required fields.");
        }
    }

}
