package com.example.kafkaavro;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class KafkaConfig {
    @Value("${topic.name}")
    private String topicName;

    @Value("${topic.partitions-num}")
    private int partitions;

    @Value("${topic.replication-factor}")
    private short replicationFactor;

    @Value("${spring.kafka.properties.schema.registry.url}")
    private String schemaRegistryUrl;

    @Value("${spring.kafka.producer.key-serializer}")
    private String keySerializer;

    @Value("${spring.kafka.producer.value-serializer}")
    private String valueSerializer;

    @Bean
    public AdminClient adminClient() {
        Properties adminProperties = new Properties();
        adminProperties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        return AdminClient.create(adminProperties);
    }

    @Bean
    public NewTopic myTopic() {
        return new NewTopic(topicName, partitions, replicationFactor);
    }

    @Bean
    public Producer<String, Book> kafkaProducer() {
        Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        producerProps.put("schema.registry.url", schemaRegistryUrl);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);

        return new KafkaProducer<>(producerProps);
    }
}

