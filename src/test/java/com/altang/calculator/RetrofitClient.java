package com.altang.calculator;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

public class RetrofitClient {
    static final String BASE_URL = "http://localhost:9998/";

    public CallbackResultStore add(final String int1, final String int2) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();
        CalculatorAPI calculatorAPI = retrofit.create(CalculatorAPI.class);
        Call<ResponseBody> call = calculatorAPI.add(int1, int2);
        CallbackResultStore store = new CallbackResultStore();
        call.enqueue(store);
        return store;
    }

    public CallbackResultStore subtract(final String int1, final String int2) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();
        CalculatorAPI calculatorAPI = retrofit.create(CalculatorAPI.class);
        Call<ResponseBody> call = calculatorAPI.subtract(int1, int2);
        CallbackResultStore store = new CallbackResultStore();
        call.enqueue(store);
        return store;
    }

    public CallbackResultStore multiply(final String int1, final String int2) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();
        CalculatorAPI calculatorAPI = retrofit.create(CalculatorAPI.class);
        Call<ResponseBody> call = calculatorAPI.multiply(int1, int2);
        CallbackResultStore store = new CallbackResultStore();
        call.enqueue(store);
        return store;
    }

    public CallbackResultStore divide(final String int1, final String int2) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();
        CalculatorAPI calculatorAPI = retrofit.create(CalculatorAPI.class);
        Call<ResponseBody> call = calculatorAPI.divide(int1, int2);
        CallbackResultStore store = new CallbackResultStore();
        call.enqueue(store);
        return store;
    }

    public CallbackResultStore audit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();
        CalculatorAPI calculatorAPI = retrofit.create(CalculatorAPI.class);
        Call<ResponseBody> call = calculatorAPI.audit();
        CallbackResultStore store = new CallbackResultStore();
        call.enqueue(store);
        return store;
    }
}