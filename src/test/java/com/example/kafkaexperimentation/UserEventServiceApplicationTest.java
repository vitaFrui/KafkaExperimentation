package com.example.kafkaexperimentation;

import com.example.kafkaexperimentation.controller.UserEventController;
import com.example.kafkaexperimentation.model.UserEventV2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@EmbeddedKafka(
		partitions = 1, topics = {"${kafka.user-event.topic.name}"})
class UserEventServiceApplicationTest {

	@Autowired
	private UserEventController userEventController;

	// A thread-safe queue to hold received records
	private final BlockingQueue<ConsumerRecord<String, UserEventV2>> records = new LinkedBlockingQueue<>();

	// KafkaListener to consume from the topic and add to the queue
	@KafkaListener(topics = "${kafka.user-event.topic.name}", groupId = "test-group")
	public void listen(ConsumerRecord<String, UserEventV2> record) {
		records.add(record);
	}

	@Test
	void create_user_event() throws InterruptedException {
		final var event = UserEventV2.builder()
				.userId(UUID.randomUUID())
				.eventDatetime(LocalDateTime.now())
				.eventName("test")
				.build();

		userEventController.createEvents(event);

		ConsumerRecord<String, UserEventV2> receivedRecord = records.poll(10, TimeUnit.SECONDS);

		assertNotNull(receivedRecord, "No message received from Kafka topic");
		UserEventV2 receivedEvent = receivedRecord.value();
		assertEquals(event.getUserId(), receivedEvent.getUserId());
		assertEquals(event.getEventName(), receivedEvent.getEventName());
	}
}
