package com.liseh.bll.producer;

import com.liseh.bll.common.MessageProducerDto;

import java.util.concurrent.ExecutionException;

public interface MessageProducer {

    String sendAndReceiveMessage(MessageProducerDto messageProducerDto) throws InterruptedException, ExecutionException;

    default String sendMessage(MessageProducerDto messageProducerDto){
        //TODO implement in future for async operations
        return null;
    };
}
