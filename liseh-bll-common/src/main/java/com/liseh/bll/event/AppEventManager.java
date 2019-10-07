package com.liseh.bll.event;

import com.liseh.bll.utility.LogUtils;
import lombok.Data;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class AppEventManager {
    private static Map<String, List<AppEvent>> eventRegistry = new ConcurrentHashMap<>();

    public static void register(String eventName, EventCallback eventCallback) {
        AppEvent appEvent = new AppEvent(eventCallback.getClass().getTypeName().split("\\$\\$Lambda\\$")[0], eventCallback);
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
                if (appEvent.getClazz().equals(clazz.getTypeName())) {
                    appEvents.remove(appEvent);
                }
            }
        }
    }

    public static void fire(String eventName) {
        if (eventRegistry.containsKey(eventName)) {
            eventRegistry.get(eventName).forEach(appEvent -> {
                try {
                    appEvent.getEventCallback().callback();
                } catch (Exception ex) {
                    String message = String.format("Event %s on class %s. Cause: %s", eventName, appEvent.getClazz(), ex.getMessage());
                    LogUtils.error(message);
                }
            });
        }
    }

    @Data
    private static class AppEvent {
        private String clazz;
        private EventCallback eventCallback;

        private AppEvent(String clazz, EventCallback eventCallback) {
            this.clazz = clazz;
            this.eventCallback = eventCallback;
        }
    }
}
