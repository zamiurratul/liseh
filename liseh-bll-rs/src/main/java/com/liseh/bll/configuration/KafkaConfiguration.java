package com.liseh.bll.configuration;

import com.liseh.GenericKafkaObject;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:kafka_config.properties")
public class KafkaConfiguration {
    @Value("${kafka.bootstrap-servers}")
    private String bootStrapServers;

    @Value("${kafka.consumer-group}")
    private String consumerGroup;

    @Bean
    public ProducerFactory<String, GenericKafkaObject> producerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<String, GenericKafkaObject> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ConsumerFactory<String, GenericKafkaObject> consumerFactory() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);
        return new DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean
    public KafkaMessageListenerContainer<String, GenericKafkaObject> replyContainer(ConsumerFactory<String, GenericKafkaObject> consumerFactory){
        String[] topics = new String[1];
        topics[0] = "registration_reply_topic";

        ContainerProperties containerProperties = new ContainerProperties(topics);
        return new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
    }

    @Bean
    public ReplyingKafkaTemplate<String, GenericKafkaObject, GenericKafkaObject> replyingKafkaTemplate(ProducerFactory<String, GenericKafkaObject> producerFactory, KafkaMessageListenerContainer<String, GenericKafkaObject> container){
        return new ReplyingKafkaTemplate<>(producerFactory, container);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, GenericKafkaObject>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, GenericKafkaObject> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        containerFactory.setReplyTemplate(kafkaTemplate());
        return containerFactory;
    }
}
