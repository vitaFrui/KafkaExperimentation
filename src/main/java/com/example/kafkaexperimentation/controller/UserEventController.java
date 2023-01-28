package com.example.kafkaexperimentation.controller;

import com.example.kafkaexperimentation.model.EvenCreationRequest;
import com.example.kafkaexperimentation.model.UserEvent;
import com.example.kafkaexperimentation.service.UserEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A very simple REST Controller for producing messages via external output.
 */
@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class UserEventController {
    private final UserEventProducer userEventProducer;

    /**
     * Create user events.
     *
     * @param evenCreationRequest Event creation request
     * @return status of even creation
     */
    @PostMapping("/create")
    public ResponseEntity<Void> createEvents(@RequestBody final EvenCreationRequest evenCreationRequest) {
        for (int i = 0; i < evenCreationRequest.getNumEvents(); i++) {
            userEventProducer.sendMessage(UserEvent.createRandomEvent());
        }

        return ResponseEntity.ok().build();
    }
}
