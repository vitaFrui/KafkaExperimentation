package com.example.kafkaexperimentation.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * An object that represents some bogus UserEvent that we will use in our Kafka producer and consumer.
 */
@Setter
@Getter
@Builder
// no arg constructor for jackson
@NoArgsConstructor
@AllArgsConstructor
public class UserEvent {
    private UUID userId;
    private String eventName;
    private LocalDateTime eventDatetime;

    /**
     * Generates a random {@link UserEvent} object.
     *
     * @return random instance of this object
     */
    public static UserEvent createRandomEvent() {
        return UserEvent.builder()
                .userId(UUID.randomUUID())
                .eventName("Event")
                .eventDatetime(LocalDateTime.now())
                .build();
    }
}
