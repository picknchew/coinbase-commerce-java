package me.picknchew.coinbase.commerce.event.model;

import com.google.gson.*;
import me.picknchew.coinbase.commerce.model.Charge;
import me.picknchew.coinbase.commerce.event.EventType;

import java.lang.reflect.Type;
import java.time.Instant;

public class ChargeStatusEvent extends Event {
    private final Charge charge;
    private final Status status;

    public ChargeStatusEvent(String id, String apiVersion, Charge charge, Status status, Instant createdAt) {
        super(id, apiVersion, createdAt);
        this.charge = charge;
        this.status = status;
    }

    public Charge getCharge() {
        return charge;
    }

    public Status getStatus() {
        return status;
    }

    public static class Deserializer implements JsonDeserializer<ChargeStatusEvent> {

        public ChargeStatusEvent deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject rootObject = jsonElement.getAsJsonObject();
            JsonObject data = rootObject.getAsJsonObject("data");

            EventType eventType = jsonDeserializationContext.deserialize(rootObject.get("type"), EventType.class);
            String id = rootObject.get("id").getAsString();
            String apiVersion = rootObject.get("api_version").getAsString();
            Instant createdAt = jsonDeserializationContext.deserialize(rootObject.get("created_at"), Instant.class);
            Charge charge = jsonDeserializationContext.deserialize(data, Charge.class);
            Status status = null;

            switch (eventType) {
                case CHARGE_CREATED:
                    status = Status.CREATED;
                    break;
                case CHARGE_FAILED:
                    status = Status.FAILED;
                    break;
                case CHARGE_CONFIRMED:
                    status = Status.CONFIRMED;
                    break;
            }

            return new ChargeStatusEvent(id, apiVersion, charge, status, createdAt);
        }
    }

    public enum Status {
        CREATED, FAILED, CONFIRMED
    }
}
