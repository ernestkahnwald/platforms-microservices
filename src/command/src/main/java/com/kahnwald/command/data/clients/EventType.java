package com.kahnwald.command.data.clients;

public enum EventType {

    PLATFORM_PUBLISHED ("platform_published");

    private final String eventName;

    EventType(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return this.eventName;
    }
}
