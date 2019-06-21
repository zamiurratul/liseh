package com.liseh.bll.impl.producer;

import com.liseh.bll.constants.KafkaConstants;
import com.liseh.bll.model.common.MessageProducerDto;
import com.liseh.bll.producer.MessageProducer;
import com.liseh.bll.producer.RegistrationProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class RegistrationProducerImpl implements RegistrationProducer {
    @Autowired
    MessageProducer messageProducer;

    @Override
    public String preRegistration(String content) throws InterruptedException, ExecutionException {
        MessageProducerDto messageProducerDto = new MessageProducerDto();
        messageProducerDto.setActionName(KafkaConstants.ACTION_REGISTRATION_PRE_REGISTER);
        messageProducerDto.setRequestTopic(KafkaConstants.REQUEST_TOPIC_REGISTRATION);
        messageProducerDto.setReplyTopic(KafkaConstants.REPLY_TOPIC_REGISTRATION);
        messageProducerDto.setContent(content);
        return messageProducer.sendAndReceiveMessage(messageProducerDto);
    }

    @Override
    public String registration(String content) throws InterruptedException, ExecutionException {
        MessageProducerDto messageProducerDto = new MessageProducerDto();
        messageProducerDto.setActionName(KafkaConstants.ACTION_REGISTRATION_REGISTER);
        messageProducerDto.setRequestTopic(KafkaConstants.REQUEST_TOPIC_REGISTRATION);
        messageProducerDto.setReplyTopic(KafkaConstants.REPLY_TOPIC_REGISTRATION);
        messageProducerDto.setContent(content);
        return messageProducer.sendAndReceiveMessage(messageProducerDto);
    }
}
