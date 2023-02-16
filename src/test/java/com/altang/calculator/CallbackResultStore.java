package com.altang.calculator;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallbackResultStore implements Callback<ResponseBody> {
    private boolean isRequestCompleted;
    private boolean isRequestSuccessful;
    private String message;

    @Override
    public void onResponse(final Call<ResponseBody> call, final Response<ResponseBody> response) {
        this.isRequestSuccessful = response.isSuccessful();
        if (this.isRequestSuccessful) {
            try {
                this.message = response.body().string();
            } catch (Exception e) {
                this.message = "Exception extracting message body!";
            }
        } else {
            try {
                this.message = response.errorBody().toString();
            } catch (Exception e) {
                this.message = "Exception extracting error body!";
            }
        }
        isRequestCompleted = true;
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        this.isRequestSuccessful = false;
        this.message = t.toString();
        this.isRequestCompleted = true;
    }

    // TODO: turn this into an async/await thing or at least something less hideous
    public boolean isRequestCompleted() {
        return isRequestCompleted;
    }

    public boolean isRequestSuccessful() {
        return isRequestSuccessful;
    }

    public String getMessage() {
        return message;
    }
}