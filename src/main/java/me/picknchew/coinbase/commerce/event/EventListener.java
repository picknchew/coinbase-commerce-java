package me.picknchew.coinbase.commerce.event;

import me.picknchew.coinbase.commerce.event.model.Event;

public interface EventListener<T extends Event> {

    void onReceive(T notification);
}
