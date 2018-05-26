package com.enghack2018.Controllers;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.enghack2018.Activities.MainDataActivity;
import com.enghack2018.Model.PlateDO;
import com.enghack2018.Model.ResponseModelDO;
import com.enghack2018.R;
import com.enghack2018.REST.Request.Plate.PlatesRequestAsync;
import com.enghack2018.REST.Response.AsyncCallBackResponse;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Splash Screen Controller
 */
public class SplashScreenController {

    private PlatesRequestAsync platesRequestAsync;
    private Context context;

    public SplashScreenController(Context context){
        this.context = context;
        this.platesRequestAsync = new PlatesRequestAsync();
    }

    public void fetchData(int amount){
        platesRequestAsync.getPlates(amount, "40.749319", "-73.986089",  new AsyncCallBackResponse() {
            @Override
            public void onSuccess(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                ResponseModelDO responseModelDO = new ResponseModelDO(response);

                //Populate list with response, start activity with passed in data through intent
                List<PlateDO> plateDOS = new ArrayList<>();
                for (JsonElement element : responseModelDO.getResult()){
                    JsonObject jsonObject = element.getAsJsonObject();
                    plateDOS.add(new PlateDO(
                            jsonObject.get("food_img").getAsString(),
                            jsonObject.get("type").getAsString(),
                            jsonObject.get("price").getAsString(),
                            jsonObject.get("name").getAsString(),
                            jsonObject.get("rating").getAsString()));
                }

                Intent intent = new Intent(context, MainDataActivity.class);
                intent.putExtra("plates", (Serializable) plateDOS);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Toast.makeText(context, R.string.cannot_conect_to_servers, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
