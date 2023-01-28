package com.example.kafkaexperimentation.service;

import com.example.kafkaexperimentation.model.UserEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Class that contains the Kafka producer.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserEventProducer {
    @Value("${kafka.user-event.topic.name}")
    private String userEventTopicName;

    private final KafkaTemplate<String,UserEvent> kafkaTemplate;

    public void sendMessage(final UserEvent userEvent) {
        log.info("Sending user event: {}", userEvent.getUserId());

        this.kafkaTemplate.send(userEventTopicName, userEvent);
    }
}
