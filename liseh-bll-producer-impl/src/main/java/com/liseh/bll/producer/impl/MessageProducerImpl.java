package com.liseh.bll.producer.impl;

import com.liseh.bll.common.MessageProducerDto;
import com.liseh.bll.producer.MessageProducer;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class MessageProducerImpl implements MessageProducer {

//    @Autowired
//    ReplyingKafkaTemplate<String, GenericKafkaObject, GenericKafkaObject> replyingKafkaTemplate;

    @Override
    public String sendAndReceiveMessage(MessageProducerDto messageProducerDto) throws InterruptedException, ExecutionException {
//        String replyMessage;
//
//        GenericKafkaObject kafkaObject = new GenericKafkaObject();
//        kafkaObject.setTopicName(messageProducerDto.getRequestTopic());
//        kafkaObject.setContent(messageProducerDto.getContent());
//
//        ProducerRecord<String, GenericKafkaObject> producerRecord = new ProducerRecord<>(kafkaObject.getTopicName(), kafkaObject);
//        producerRecord.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, messageProducerDto.getReplyTopic().getBytes()));
//
//        ConsumerRecord<String, GenericKafkaObject> consumerRecord = replyingKafkaTemplate.sendAndReceive(producerRecord).get();
//        replyMessage = consumerRecord.value().getContent();
//
//        return replyMessage;
        return null;
    }
}
