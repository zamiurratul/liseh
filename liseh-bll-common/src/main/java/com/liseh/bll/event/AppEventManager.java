package com.liseh.bll.event;

import com.liseh.bll.utility.LogUtils;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class AppEventManager {
    private static final Logger logger = LogManager.getLogger(AppEventManager.class);
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
                    String message = String.format("Event %s on class %s.", eventName, appEvent.getClazz());
                    LogUtils.error(logger, "FireAppEvent", message, ex.getMessage());
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
