package com.altang.calculator;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CalculatorAPI {
    @GET("add")
    Call<ResponseBody> add(@Query("int1") String int1, @Query("int2") String int2);

    @GET("subtract")
    Call<ResponseBody> subtract(@Query("int1") String int1, @Query("int2") String int2);

    @GET("multiply")
    Call<ResponseBody> multiply(@Query("int1") String int1, @Query("int2") String int2);

    @GET("divide")
    Call<ResponseBody> divide(@Query("int1") String int1, @Query("int2") String int2);

    @GET("audit")
    Call<ResponseBody> audit();
}