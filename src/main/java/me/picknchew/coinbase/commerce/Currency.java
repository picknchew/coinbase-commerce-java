package me.picknchew.coinbase.commerce;

import com.google.gson.annotations.SerializedName;

public enum Currency {
    @SerializedName("local")
    LOCAL(""),
    @SerializedName("ethereum")
    ETHEREUM("ETH"),
    @SerializedName("bitcoin")
    BITCOIN("BTC"),
    @SerializedName("litecoin")
    LITECOIN("LTC"),
    @SerializedName("bitcoincash")
    BITCOIN_CASH("BCH");

    private final String symbol;

    Currency(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
