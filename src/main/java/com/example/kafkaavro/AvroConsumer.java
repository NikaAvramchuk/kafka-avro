package com.example.kafkaavro;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AvroConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AvroConsumer.class);

    @KafkaListener(topics = "books", groupId = "group_id")
    public void consume(ConsumerRecord<String, Book> record) {
       LOGGER.info("Avro Consumer consumed: {}", record.value());
    }
}
