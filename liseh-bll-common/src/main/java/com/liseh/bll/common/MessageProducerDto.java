package com.liseh.bll.common;

import lombok.Data;

@Data
public class MessageProducerDto {
    private String requestTopic;
    private String replyTopic;
    private String actionName;
    private String content;
}
