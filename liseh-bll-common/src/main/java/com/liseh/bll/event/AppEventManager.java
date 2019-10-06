package com.liseh.bll.event;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AppEventManager {
    public static Map<String, List<AppEvent>> eventRegistry = new HashMap<>();

    public static void register(Class clazz, String eventName, EventCallback eventCallback) {
        AppEvent appEvent = new AppEvent(clazz, eventCallback);
        if (!eventRegistry.containsKey(eventName)) {
            List<AppEvent> appEvents = new ArrayList<>();
            appEvents.add(appEvent);
            eventRegistry.put(eventName, appEvents);
        } else {
            List<AppEvent> appEvents = eventRegistry.get(eventName);
            appEvents.add(appEvent);
            eventRegistry.put(eventName, appEvents);
        }
    }

    public static void deregister(Class clazz, String eventName) {
        if (eventRegistry.containsKey(eventName)) {
            List<AppEvent> appEvents = eventRegistry.get(eventName);
            for (AppEvent appEvent : appEvents) {
                if (appEvent.getClazz() == clazz) {
                    appEvents.remove(appEvent);
                }
            }
        }
    }

    public static void on(String eventName, Object... payload) {

    }

    public static void fire(String eventName) {
        if (eventRegistry.containsKey(eventName)) {
            eventRegistry.get(eventName).forEach(appEvent -> appEvent.getEventCallback().callback());
        }
    }

}
