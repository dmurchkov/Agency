package com.dmurchkov.service.agency.kafka;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;

@AllArgsConstructor
public class AgencyKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName;

    public void send(String message) {
        kafkaTemplate.send(topicName, message);
    }
}