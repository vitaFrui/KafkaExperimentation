package com.example.kafkaexperimentation.controller;

import com.example.kafkaexperimentation.model.UserEventV1;
import com.example.kafkaexperimentation.model.UserEventV2;
import com.example.kafkaexperimentation.service.UserEventProducer;
import com.example.kafkaexperimentation.service.UserEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * A very simple REST Controller for producing messages via external output.
 */
@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class UserEventController {
    private final UserEventProducer userEventProducer;
    private final UserEventService userEventService;

    /**
     * Create user events.
     *
     * @param userEvent Event creation request
     * @return status of even creation
     */
    @PostMapping("/v2/create")
    public ResponseEntity<Void> createEvents(@RequestBody final UserEventV2 userEvent) {
        userEventService.preProcessUserEvent(userEvent);

        userEventProducer.sendMessage(userEvent);

        return ResponseEntity.ok().build();
    }

    /**
     * Create user events.
     *
     * @param userEvent Event creation request
     * @return status of even creation
     */
    @PostMapping("/v1/create")
    public ResponseEntity<Void> createEventsLegacy(@RequestBody final UserEventV1 userEvent) {
        final var userEventV2 = UserEventV2.builder()
                .userId(userEvent.getUserId())
                .eventName(userEvent.getEventName())
                .eventDatetime(LocalDateTime.from(Instant.ofEpochMilli(userEvent.getEventTimestamp())))
                .event_source(userEvent.event_source)
                .build();

        userEventService.preProcessUserEvent(userEventV2);

        userEventProducer.sendMessage(userEventV2);

        return ResponseEntity.ok().build();
    }
}
