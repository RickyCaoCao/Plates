package com.enghack2018.REST.Request.Plate;



import android.support.annotation.NonNull;

import com.enghack2018.REST.Request.BaseRequest;
import com.enghack2018.REST.RequestController;
import com.enghack2018.REST.Response.AsyncCallBackResponse;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class PlatesRequestAsync extends BaseRequest {

    interface Plates {
        @GET("plates")
        Call<ResponseBody> getPlates(@Query("latitude") String latitude, @Query("longitude")String longitude);
    }
    public void getPlates(int amount, String latitude, String longitude, AsyncCallBackResponse asyncCallBackResponse){
        Plates plates = RequestController.getInstance().getRetrofit().create(Plates.class);
        plates.getPlates(latitude, longitude).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                asyncCallBackResponse.onSuccess(call, response);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                asyncCallBackResponse.onFailure(call, t);
            }
        });
    }
}
