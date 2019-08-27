package me.picknchew.coinbase.commerce.model;

import com.google.gson.annotations.SerializedName;
import me.picknchew.coinbase.commerce.Currency;
import me.picknchew.coinbase.commerce.Price;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class Charge {
    @SerializedName("code")
    private String code;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("logo_url")
    private String logoUrl;

    @SerializedName("hosted_url")
    private String hostedUrl;

    @SerializedName("created_at")
    private Instant createdAt;

    @SerializedName("expires_at")
    private Instant expiresAt;

    @SerializedName("confirmed_at")
    private Instant confirmedAt;

    @SerializedName("pricing_type")
    private PricingType pricingType;

    @SerializedName("pricing")
    private Map<Currency, Price> pricing;

    @SerializedName("addresses")
    private Map<Currency, String> addresses;

    @SerializedName("metadata")
    private Map<String, String> metadata;

    @SerializedName("payments")
    private List<Payment> payments;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getHostedUrl() {
        return hostedUrl;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public Instant getConfirmedAt() {
        return confirmedAt;
    }

    public Map<Currency, Price> getPricing() {
        return pricing;
    }

    public PricingType getPricingType() {
        return pricingType;
    }

    public Map<Currency, String> getAddresses() {
        return addresses;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public enum PricingType {
        @SerializedName("no_price")
        NO_PRICE,
        @SerializedName("fixed_price")
        FIXED_PRICE
    }
}
