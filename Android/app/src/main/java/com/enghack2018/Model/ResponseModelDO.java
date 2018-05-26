package com.enghack2018.Model;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Base response model data object
 */
public class ResponseModelDO extends  DataObject {

    private JsonArray result;

    public ResponseModelDO(Response<ResponseBody> body){
        ResponseBody responseBody = body.body();
        String output = "";
        try {
            output = responseBody.string();
        } catch (IOException e) {
            //do nothing
        }
        JsonParser jsonParser = new JsonParser();
        this.result = jsonParser.parse(output).getAsJsonArray();
    }

    public JsonArray getResult() {
        return result;
    }
}
