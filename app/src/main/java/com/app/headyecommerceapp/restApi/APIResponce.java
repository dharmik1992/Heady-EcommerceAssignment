package com.app.headyecommerceapp.restApi;//package ccom.app.montessori.RestApi;

import android.content.Context;

import com.app.headyecommerceapp.R;
import com.app.headyecommerceapp.Utils.Loggers;
import com.app.headyecommerceapp.Utils.Util;
import com.app.headyecommerceapp.interfaces.ApplicationConfig;
import com.app.headyecommerceapp.interfaces.getCallBackResponce;
import com.google.gson.JsonElement;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Path;


public class APIResponce implements ApplicationConfig {
    getCallBackResponce getCallBackResponce;
    String Tag;
    String token;
    String pathurl;
    HashMap<String, Object> body;
    Context context;

    public APIResponce(Context context, @Path("pathurl") String pathurl, String Tag, @Body HashMap<String, Object> body, getCallBackResponce getCallBackResponce, int methods) {
        this.getCallBackResponce = getCallBackResponce;
        this.Tag = Tag;
        this.pathurl = pathurl;
        this.body = body;
        this.context = context;
        Loggers.D("pathurl ", "   " + pathurl);
        Loggers.D("pathurl body ", "   " + body);
        if (methods == GET) {
            callgetAPI();
        }

    }

    public void callgetAPI() {
        if (Util.isNetworkConnected(context)) {
            Call<JsonElement> apiCall = APIClient.getInstance().getRestAdapter().getGetResponce(pathurl, token, body);
            apiCall.enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    callBackResponce(response);
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    getCallBackResponce.getCallBackResponce(-1, t.getMessage(), Tag, null);
                }


            });
        } else
            getCallBackResponce.getCallBackResponce(NOINTERNETCONNECTION, context.getString(R.string.nointernetmessage), Tag, null);

    }

    public void callBackResponce(Response<JsonElement> response) {
        Loggers.D("errorcode ", "   " + response.code());

        Util.printRawResponse(response.errorBody());
        int code = getcode(response.code());
        if (response.body() != null) {
            try {
                JSONObject object = new JSONObject(response.body().toString());
                String message = "";
                if (object.has("message"))
                    message = object.getString("message");
                getCallBackResponce.getCallBackResponce(code, message, Tag, object);
            } catch (JSONException e) {
                getCallBackResponce.getCallBackResponce(response.code(), e.getMessage(), Tag, null);
            }
        } else if (response.errorBody() != null && response.errorBody().contentLength() > 0) {
            try {
                JSONObject object = new JSONObject(response.errorBody().string());
                String message = "";
                if (object.has("message"))
                    message = object.getString("message");
                getCallBackResponce.getCallBackResponce(code, message, Tag, object);
            } catch (Exception e) {
                getCallBackResponce.getCallBackResponce(response.code(), e.getMessage(), Tag, null);
            }
        } else
            getCallBackResponce.getCallBackResponce(response.code(), context.getString(R.string.commonerror), Tag, null);
    }




    public int getcode(int responceCode) {

     /*   if (responceCode == 200)
            return SUCCESS;
        else if (responceCode == 401)
            return SESSIONEXPIRE;
        else*/
        return responceCode;


    }

}
