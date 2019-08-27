package me.picknchew.coinbase.commerce.model;

import com.google.gson.annotations.SerializedName;
import me.picknchew.coinbase.commerce.Currency;
import me.picknchew.coinbase.commerce.Price;

import java.util.Map;

public class Payment {
    @SerializedName("network")
    private Currency currency;

    @SerializedName("transaction_id")
    private String transactionId;

    @SerializedName("status")
    private Status status;

    @SerializedName("value")
    private Map<String, Price> value;

    public Currency getCurrency() {
        return currency;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Status getStatus() {
        return status;
    }

    public Map<String, Price> getValue() {
        return value;
    }

    public static class Block {
        @SerializedName("height")
        private int height;

        @SerializedName("hash")
        private String hash;

        @SerializedName("confirmations_accumulated")
        private int confirmationsAccumulated;

        @SerializedName("confirmations_required")
        private int confirmationsRequired;

        public int getHeight() {
            return height;
        }

        public String getHash() {
            return hash;
        }

        public int getConfirmationsAccumulated() {
            return confirmationsAccumulated;
        }

        public int getConfirmationsRequired() {
            return confirmationsRequired;
        }
    }

    public enum Status {
        CONFIRMED
    }
}
