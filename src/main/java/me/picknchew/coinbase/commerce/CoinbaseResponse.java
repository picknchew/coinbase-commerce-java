package me.picknchew.coinbase.commerce;

import com.google.gson.annotations.SerializedName;

public class CoinbaseResponse<T> {
    @SerializedName("data")
    T data;
}
