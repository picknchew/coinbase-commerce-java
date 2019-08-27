package me.picknchew.coinbase.commerce.body;

import com.google.gson.annotations.SerializedName;
import me.picknchew.coinbase.commerce.Price;
import me.picknchew.coinbase.commerce.model.Charge;

import java.util.Map;

public class CreateChargeBody {
    @SerializedName("name")
    private final String name;

    @SerializedName("description")
    private final String description;

    @SerializedName("pricing_type")
    private final Charge.PricingType pricingType;

    @SerializedName("local_price")
    private Price localPrice;

    @SerializedName("metadata")
    private Map<String, String> metadata;

    @SerializedName("redirect_url")
    private String redirectUrl;

    public CreateChargeBody(String name, String description, Charge.PricingType pricingType) {
        this.name = name;
        this.description = description;
        this.pricingType = pricingType;
    }

    public void setLocalPrice(Price localPrice) {
        this.localPrice = localPrice;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
