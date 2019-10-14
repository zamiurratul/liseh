package com.liseh.bll.service.impl;

import com.liseh.GenericKafkaObject;
import com.liseh.bll.service.MessageExchangeService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class MessageExchangeServiceImpl implements MessageExchangeService {
    @Value("${liseh-bll-request-topic-async}")
    private String bllRequestTopicAsync;

    @Value("${liseh-bll-request-topic-sync}")
    private String bllRequestTopicSync;

    @Value("${liseh-bll-reply-topic-sync}")
    private String bllReplyTopicSync;

    private final KafkaTemplate<String, GenericKafkaObject> kafkaTemplate;
    private final ReplyingKafkaTemplate<String, GenericKafkaObject, GenericKafkaObject> replyingKafkaTemplate;

    public MessageExchangeServiceImpl(KafkaTemplate<String, GenericKafkaObject> kafkaTemplate, ReplyingKafkaTemplate<String, GenericKafkaObject, GenericKafkaObject> replyingKafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.replyingKafkaTemplate = replyingKafkaTemplate;
    }

    @Override
    public GenericKafkaObject sendAndReceiveMessage(GenericKafkaObject request) throws InterruptedException, ExecutionException {
        ProducerRecord<String, GenericKafkaObject> producerRecord = new ProducerRecord<>(bllRequestTopicSync, request);
        producerRecord.headers().add(new RecordHeader(KafkaHeaders.REPLY_TOPIC, bllReplyTopicSync.getBytes()));

        RequestReplyFuture<String, GenericKafkaObject, GenericKafkaObject> requestReplyFuture = replyingKafkaTemplate.sendAndReceive(producerRecord);
        ConsumerRecord<String, GenericKafkaObject> consumerRecord =  requestReplyFuture.get();
        return consumerRecord.value();
    }

    @Override
    public void sendMessage(GenericKafkaObject request) throws InterruptedException, ExecutionException {
        kafkaTemplate.send(bllRequestTopicAsync, request);
    }
}
