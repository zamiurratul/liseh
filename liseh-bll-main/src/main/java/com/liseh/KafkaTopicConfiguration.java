//package com.liseh;
//
//import com.liseh.bll.constant.KafkaConstants;
//import org.apache.kafka.clients.admin.AdminClientConfig;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.kafka.core.KafkaAdmin;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@PropertySource("classpath:kafka_config.properties")
//public class KafkaTopicConfiguration {
//    @Value("${kafka.bootstrap-servers}")
//    private String bootstrapAddress;
//
//    @Bean
//    public KafkaAdmin kafkaAdmin() {
//        Map<String, Object> configs = new HashMap<>();
//        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        return new KafkaAdmin(configs);
//    }
//
//    @Bean
//    public NewTopic registrationTopic() {
//        return new NewTopic(KafkaConstants.REQUEST_TOPIC_REGISTRATION, 1, (short) 1);
//    }
//}
