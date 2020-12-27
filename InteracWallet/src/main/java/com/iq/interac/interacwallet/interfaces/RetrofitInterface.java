package com.iq.interac.interacwallet.interfaces;

import com.google.gson.JsonElement;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface RetrofitInterface {
    @GET
    Call<JsonElement> getDataInteracObject(@Url String url);

    @POST
    Call<JsonElement> postDataInteracObject(@Url String url, @Body RequestBody body);
}
