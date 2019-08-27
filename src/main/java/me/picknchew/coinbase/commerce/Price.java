package me.picknchew.coinbase.commerce;

import com.google.gson.annotations.SerializedName;

public class Price {
    @SerializedName("amount")
    private final String amount;

    @SerializedName("currency")
    private final String currency;

    public Price(String amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
