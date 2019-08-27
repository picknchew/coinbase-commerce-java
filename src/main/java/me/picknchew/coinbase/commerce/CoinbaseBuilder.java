package me.picknchew.coinbase.commerce;

public class CoinbaseBuilder {
    String apiKey;
    String webhookSecret;

    public CoinbaseBuilder withApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public CoinbaseBuilder withWebhookSecret(String secret) {
        this.webhookSecret = secret;
        return this;
    }

    public Coinbase build() {
        return new Coinbase(this);
    }
}
