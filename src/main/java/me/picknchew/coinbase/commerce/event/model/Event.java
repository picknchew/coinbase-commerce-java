package me.picknchew.coinbase.commerce.event.model;

import java.time.Instant;

public abstract class Event {
    protected final String id;
    protected final String apiVersion;
    protected final Instant createdAt;

    public Event(String id, String apiVersion, Instant createdAt) {
        this.id = id;
        this.apiVersion = apiVersion;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
