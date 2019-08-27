package me.picknchew.coinbase.commerce.event;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.picknchew.coinbase.commerce.deserializer.InstantDeserializer;
import me.picknchew.coinbase.commerce.event.model.ChargeStatusEvent;
import me.picknchew.coinbase.commerce.event.model.Event;
import spark.Spark;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static spark.Spark.*;

public class EventHandler {
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(ChargeStatusEvent.class, new ChargeStatusEvent.Deserializer())
            .registerTypeAdapter(Instant.class, new InstantDeserializer())
            .create();
    private final Map<Class<? extends Event>, Set<EventListener<?>>> listeners = new HashMap<>();
    private final HashFunction hashFunction;
    private boolean started = false;

    public EventHandler(String secret) {
        this.hashFunction = Hashing.hmacSha256(secret.getBytes(StandardCharsets.UTF_8));

        registerDefaultEvents();
        initWebServer();
    }

    private void registerDefaultEvents() {
        registerNotification(ChargeStatusEvent.class);
    }

    private void initWebServer() {
        port(8880);

        JsonParser parser = new JsonParser();

        before((req, res) -> {
            String signature = req.headers("X-Cc-Webhook-Signature");

            if (signature == null || !req.headers("X-Cc-Webhook-Signature").equals(getSignature(req.body()))) {
                throw halt(401);
            }
        });

        post("/", (req, res) -> {
            try {
                JsonObject parsedObject = parser.parse(req.body()).getAsJsonObject().getAsJsonObject("event");
                EventType type = gson.fromJson(parsedObject.get("type"), EventType.class);

                res.status(200);

                notifyListeners(gson.fromJson(parsedObject, type.getWrapperType()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            //if (type.getWrapperType() == null) {
             //   throw new UnsupportedOperationException("'" + type.getIdentifier() + "' is currently not supported.");
            //}

            //notifyListeners(gson.fromJson(parsedObject, type.getWrapperType()));

            return "";
        });

        started = true;
    }

    private String getSignature(String body) {
        return hashFunction.hashString(body, StandardCharsets.UTF_8).toString();
    }

    private <T extends Event> void registerNotification(Class<T> notificationClass) {
        if (listeners.containsKey(notificationClass)) {
            throw new IllegalArgumentException("'" + notificationClass.toString() + "' has already been registered.");
        }

        listeners.put(notificationClass, new HashSet<>());
    }

    public <T extends Event> void registerListener(Class<T> notificationClass, EventListener<T> listener) {
        if (!started) {
            initWebServer();
        }

        if (!listeners.containsKey(notificationClass)) {
            throw new IllegalStateException("'" + notificationClass.toString() + "' is not a registered notification.");
        }

        listeners.get(notificationClass).add(listener);
    }

    public <T extends Event> void notifyListeners(T notification) {
        Class<? extends Event> notificationClass = notification.getClass();

        if (!listeners.containsKey(notificationClass)) {
            throw new IllegalStateException("'" + notificationClass.toString() + "' is not a registered notification.");
        }

        listeners.get(notificationClass).forEach(listener -> ((EventListener<T>) listener).onReceive(notification));
    }

    public void stop() {
        Spark.stop();
    }
}
