package com.enghack2018.Model;


import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Base response model data object
 */
public class ResponseModelDO extends  DataObject {

    public ResponseModelDO(Response<ResponseBody> body){

    }
}
