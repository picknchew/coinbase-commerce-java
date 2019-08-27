package me.picknchew.coinbase.commerce;

import me.picknchew.coinbase.commerce.body.CreateChargeBody;
import me.picknchew.coinbase.commerce.model.Charge;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ChargesService {

    @POST("charges")
    Call<Charge> createCharge(@Body CreateChargeBody body);
}
