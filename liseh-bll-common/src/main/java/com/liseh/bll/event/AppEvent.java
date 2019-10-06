package com.liseh.bll.event;

import lombok.Data;

@Data
public class AppEvent {
    private Class clazz;
    private EventCallback eventCallback;

    public AppEvent() {

    }

    public AppEvent(Class clazz, EventCallback eventCallback) {
        this.clazz = clazz;
        this.eventCallback = eventCallback;
    }
}
