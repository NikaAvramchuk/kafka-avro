package com.example.kafkaavro;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AvroProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AvroProducer.class);
    @Value("${topic.name}")
    private String topic;
    private final Producer<String, Book> kafkaProducer;

    @Autowired
    public AvroProducer(Producer<String, Book> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void sendMessage(Book book) {
        ProducerRecord<String, Book> producerRecord = new ProducerRecord<>(topic, book);
        kafkaProducer.send(producerRecord, (recordMetadata, exception) -> {
            if (exception == null) {
                LOGGER.info("AvroProducer sent a message about book {}", book);
            }
            else {
                exception.printStackTrace();
            }
        });
    }
}
