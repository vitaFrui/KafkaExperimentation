package com.example.kafkaexperimentation.model;

/**
 * Enum representing different types of events.
 */
public enum EventTypeEnum {
    // A user has been authorized to perform a purchase
    AUTHORIZED,
    // User has been Blocked from further actions due to security or other reason
    BLOCKED,
    // User has taken a custom action unique to the client application. Check eventName for more details.
    CUSTOM,
    // User has triggered an error through invalid action or system error
    ERROR,
    // Informational event
    INFO,
    // User has purchased something, event name should match the event id of the corresponding authorization event
    PURCHASE
}
