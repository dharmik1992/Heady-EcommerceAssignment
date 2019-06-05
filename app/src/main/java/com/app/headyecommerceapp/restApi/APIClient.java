package com.app.headyecommerceapp.restApi;


import com.app.headyecommerceapp.interfaces.Urls;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient implements Urls {
    private static APIClient instance;

    public static synchronized APIClient getInstance() {
        if (instance == null) {
            instance = new APIClient();
        }
        return instance;
    }

    public ExecuteAPI getRestAdapter() {
        final OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(160, TimeUnit.SECONDS)
                .connectTimeout(160, TimeUnit.SECONDS)
                .build();
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return restAdapter.create(ExecuteAPI.class);
    }
}
