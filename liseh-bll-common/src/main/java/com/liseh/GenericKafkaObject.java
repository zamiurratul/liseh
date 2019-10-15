package com.liseh;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GenericKafkaObject {
    private String eventType;
    private String eventName;
    private String content;

    public GenericKafkaObject() {

    }

    public GenericKafkaObject(String eventType, String eventName, String content) {
        this.eventType = eventType;
        this.eventName = eventName;
        this.content = content;
    }
}
