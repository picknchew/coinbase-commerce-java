package me.picknchew.coinbase.commerce.event;

import com.google.gson.annotations.SerializedName;
import me.picknchew.coinbase.commerce.event.model.ChargeStatusEvent;
import me.picknchew.coinbase.commerce.event.model.Event;

public enum EventType {
    @SerializedName("charge:created")
    CHARGE_CREATED(ChargeStatusEvent.class),
    @SerializedName("charge:confirmed")
    CHARGE_CONFIRMED(ChargeStatusEvent.class),
    @SerializedName("charge:failed")
    CHARGE_FAILED(ChargeStatusEvent.class);

    private final Class<? extends Event> wrapperType;

    EventType(Class<? extends Event> wrapperType) {
        this.wrapperType = wrapperType;
    }

    public Class<? extends Event> getWrapperType() {
        return wrapperType;
    }
}
