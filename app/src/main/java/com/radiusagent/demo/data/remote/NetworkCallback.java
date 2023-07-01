package com.radiusagent.demo.data.remote;

import com.google.gson.Gson;
import com.radiusagent.demo.common.base_response.BaseResponse;
import com.radiusagent.demo.common.base_response.Result;

import java.net.ConnectException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class NetworkCallback<T> implements Callback<T> {

    private static final String TAG = "NetworkCallback";

    public abstract void onResponse(Result<T> result);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful() && response.body() != null) {
            onResponse(getSuccessResult(response));
        } else {
            onResponse(getFailureResult(response));
        }
    }

    private Result<T> getSuccessResult(Response<T> response) {
        return new Result<>(response.body(), "",Result.Status.SUCCESS);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (t instanceof ConnectException) {
            onResponse(getFailedToConnectFailureResult());
        } else {
            onResponse(getFailureResult(t));
        }
    }

    private Result<T> getFailedToConnectFailureResult() {
        return new Result<>(null, "Connection Failed", Result.Status.FAILURE);
    }


    private Result<T> getFailureResult(Response<T> response) {
        try {
            BaseResponse apiFailureResponse = new Gson().fromJson(response.errorBody().string(), BaseResponse.class);
            return new Result<>(null, apiFailureResponse.getMessage(), Result.Status.FAILURE);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result<>(null, "", Result.Status.FAILURE);
    }

    private Result<T> getFailureResult(Throwable t) {
        return new Result<>(null, t.getMessage(), Result.Status.FAILURE);
    }
}