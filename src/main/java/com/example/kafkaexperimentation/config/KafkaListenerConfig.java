package com.example.kafkaexperimentation.config;

import com.example.kafkaexperimentation.model.UserEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * Configures the Kafka Consumer.
 */
@Slf4j
@Configuration
public class KafkaListenerConfig {
    @Value("${kafka.user-event.topic.name}")
    private String userEventTopicName;

    @KafkaListener(topics = "${kafka.user-event.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(final UserEvent userEvent) {
        log.info("Consumed event {}", userEvent.getUserId());
    }
}
