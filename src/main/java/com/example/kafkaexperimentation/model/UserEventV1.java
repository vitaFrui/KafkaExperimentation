package com.example.kafkaexperimentation.model;

import java.util.UUID;

public class UserEventV1 {
    private UUID userId;
    private String eventName;
    private long eventTimestamp;

    // Added for the analytics team. Backporting to V1.
    public String event_source;


    /**
     * Legacy constructor for old services that still use epoch time.
     * @param userId The user's UUID
     * @param eventName The name of the event
     * @param eventTimestamp The epoch timestamp of the event
     */
    public UserEventV1(UUID userId, String eventName, long eventTimestamp) {
        this.userId = userId;
        this.eventName = eventName;
        this.eventTimestamp = eventTimestamp;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getEventName() {
        return eventName;
    }

    public long getEventTimestamp() {
        return eventTimestamp;
    }

    public String getEvent_source() {
        return event_source;
    }
}
