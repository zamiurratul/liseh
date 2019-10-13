package com.liseh;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GenericKafkaObject {
    private String topicName;
    private String content;
}
