package com.liseh.bll.service;

import com.liseh.GenericKafkaObject;

import java.util.concurrent.ExecutionException;

public interface MessageExchangeService {
    GenericKafkaObject sendAndReceiveMessage(GenericKafkaObject requestKafkaObject) throws InterruptedException, ExecutionException;
    void sendMessage(GenericKafkaObject requestKafkaObject);
    void sendMessage(String eventType, String eventName, String content);
}
