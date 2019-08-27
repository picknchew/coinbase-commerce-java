package me.picknchew.coinbase.commerce;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.picknchew.coinbase.commerce.deserializer.InstantDeserializer;
import me.picknchew.coinbase.commerce.event.EventHandler;
import me.picknchew.coinbase.commerce.util.StubProxySelector;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.ProxySelector;
import java.time.Instant;

public class Coinbase {
    private static final String COINBASE_VERSION = "2018-03-22";
    private static final String API_URL = "https://api.commerce.coinbase.com/";

    private final EventHandler notificationHandler;

    private final Retrofit retrofit;
    private final ChargesService chargesService;

    Coinbase(CoinbaseBuilder builder) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().baseUrl(API_URL);
        ProxySelector proxySelector = ProxySelector.getDefault();

        clientBuilder.addInterceptor(getAuthInterceptor(builder.apiKey));
        clientBuilder.proxySelector(proxySelector == null ? new StubProxySelector() : proxySelector);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Instant.class, new InstantDeserializer())
                .create();

        retrofitBuilder.client(clientBuilder.build());
        retrofitBuilder.addConverterFactory(CoinbaseResponseConverterFactory.create());
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create(gson));

        this.retrofit = retrofitBuilder.build();
        this.chargesService = retrofit.create(ChargesService.class);

        if (builder.webhookSecret != null) {
            notificationHandler = new EventHandler(builder.webhookSecret);
        } else {
            notificationHandler = null;
        }
    }

    private Interceptor getAuthInterceptor(String apiKey) {
        return chain -> {
            Request originalRequest = chain.request();

            Request request = originalRequest.newBuilder()
                    .addHeader("X-CC-Api-Key", apiKey)
                    .addHeader("X-CC-Version", COINBASE_VERSION)
                    .build();

            return chain.proceed(request);
        };
    }

    public EventHandler getNotificationHandler() {
        return notificationHandler;
    }

    public ChargesService getChargesService() {
        return chargesService;
    }
}
