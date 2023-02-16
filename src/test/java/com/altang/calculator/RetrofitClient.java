package com.altang.calculator;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class RetrofitClient {
    static final String BASE_URL = "http://0.0.0.0:8080/";

    public CallbackResultStore add(final String int1, final String int2) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();
        CalculatorAPI calculatorAPI = retrofit.create(CalculatorAPI.class);
        Call<ResponseBody> call = calculatorAPI.add(int1, int2);
        CallbackResultStore store = new CallbackResultStore();
        call.enqueue(store);
        return store;
    }
}