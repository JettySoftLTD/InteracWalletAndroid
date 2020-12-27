package com.iq.interac.interacwallet.interfaces;

import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.iq.interac.interacwallet.coreapp.InteracWallet;
import com.iq.interac.interacwallet.view.activities.MyWalletActivity;
import com.iq.interac.interacwallet.view.models.InteracMainBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface InteracAPI<T> extends Callback<T> {
    void notifySuccess(int status, String message, InteracMainBean imb);

    void notifyError(String errormessage);

    @Override
    default void onResponse(Call<T> call, Response<T> response) {
        InteracWallet.loader.dismiss();
        T responseBody = response.body();
        if (responseBody instanceof JsonElement)  {
            InteracMainBean imb = new Gson().fromJson(responseBody.toString(), InteracMainBean.class);
            if (imb != null ) {
                notifySuccess(imb.getStatus(), imb.getMessage(), imb);
            } else {
                notifyError("Network Response Error\nPlease Try Again");
            }
        } else {
            notifyError("Network Response Error\nPlease Try Again");
        }
    }

    @Override
    default void onFailure(Call<T> call, Throwable t) {
        InteracWallet.loader.dismiss();
        VolleyError vr = new VolleyError(t);
        if (vr instanceof TimeoutError) {
            notifyError("Request Timeout\nPlease Try Again");
        } else if (vr instanceof ServerError) {
            notifyError("Network Response Error\nPlease Try Again");
        } else if (vr instanceof NoConnectionError) {
            notifyError("Unable to Connect Server\nPlease Try Again");
        }
    }
}
