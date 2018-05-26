package com.enghack2018.REST.Response;


import android.support.annotation.NonNull;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Interface that upper service layers can use to implement they're own call back logic for a rest call
 */
public interface AsyncCallBackResponse {
    void onSuccess(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response);
    void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t);
}
