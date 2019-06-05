package com.app.headyecommerceapp.restApi;


import com.google.gson.JsonElement;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


public interface ExecuteAPI {

    @GET
    Call<JsonElement> getGetResponce(@Url String pathurl, @Header("Authorization") String token, @QueryMap HashMap<String, Object> body);


}