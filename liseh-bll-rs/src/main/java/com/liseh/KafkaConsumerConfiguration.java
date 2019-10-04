//package com.liseh;
//
//import com.liseh.bll.constants.KafkaConstants;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.*;
//import org.springframework.kafka.listener.ContainerProperties;
//import org.springframework.kafka.listener.KafkaMessageListenerContainer;
//import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableKafka
//@PropertySource("classpath:kafka_config.properties")
//public class KafkaConsumerConfiguration {
//    @Value("${kafka.bootstrap-servers}")
//    private String bootStrapServers;
//
//    @Value("${kafka.consumer-group}")
//    private String consumerGroup;
//
//    @Autowired
//    private ProducerFactory<String, GenericKafkaObject> producerFactory;
//
//    private Map<String, Object> consumerConfigs(){
//        Map<String, Object> configs = new HashMap<>();
//        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
//        configs.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);
//        return configs;
//    }
//
//    @Bean
//    public ConsumerFactory<String, GenericKafkaObject> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(GenericKafkaObject.class));
//    }
//
//    @Bean
//    public KafkaMessageListenerContainer<String, GenericKafkaObject> replyContainer(ConsumerFactory<String, GenericKafkaObject> consumerFactory){
//        String[] topics = new String[1];
//        topics[0] = KafkaConstants.REPLY_TOPIC_REGISTRATION;
//
//        ContainerProperties containerProperties = new ContainerProperties(topics);
//        return new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
//    }
//
//    @Bean
//    public ReplyingKafkaTemplate<String, GenericKafkaObject, GenericKafkaObject> kafkaReplyTemplate(ProducerFactory<String, GenericKafkaObject> producerFactory, KafkaMessageListenerContainer<String, GenericKafkaObject> container){
//        return new ReplyingKafkaTemplate<>(producerFactory, container);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, GenericKafkaObject> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, GenericKafkaObject> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
//        containerFactory.setConsumerFactory(consumerFactory());
//        containerFactory.setReplyTemplate(kafkaReplyTemplate(producerFactory, replyContainer(consumerFactory())));
//        return containerFactory;
//    }
//}
