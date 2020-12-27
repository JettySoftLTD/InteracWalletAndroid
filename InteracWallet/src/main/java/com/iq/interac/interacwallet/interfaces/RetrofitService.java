package com.iq.interac.interacwallet.interfaces;

import com.google.gson.JsonElement;
import com.iq.interac.interacwallet.coreapp.InteracWallet;
import com.iq.interac.interacwallet.repository.PathURL;
import com.iq.interac.interacwallet.view.activities.MyWalletActivity;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import okhttp3.CertificatePinner;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static volatile RetrofitService instance;

    static {
        try {
            instance = new RetrofitService();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private RetrofitInterface service;

    private RetrofitService() {
        int time_out = 120;
        //M6rLWlR+WqjDpG8Qr+VXewjaH9XtqXYZ75gLUdwwcJc=
        final CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add("jettysoftcloudapi.eastus.cloudapp.azure.com", "sha256/2J7yy3fRyQYqsu4Sx9c4AmBGkH2QekE9NPYwiuqtZF8=")
                .build();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(time_out, TimeUnit.SECONDS)
                .writeTimeout(time_out, TimeUnit.SECONDS)
                .connectTimeout(time_out, TimeUnit.SECONDS)
                .certificatePinner(certificatePinner)
                .retryOnConnectionFailure(true)
                //.addInterceptor(logging)
                // .cache(null)	//disable cache
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PathURL.mainUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        service = retrofit.create(RetrofitInterface.class);
    }

    public static RetrofitService getInstance() {
        if (instance == null) {
            instance = new RetrofitService();
        }
        return instance;
    }

    @SuppressWarnings("UnusedReturnValue")
    public static InteracAPI<?> enqueue(final Call<?> call, final InteracAPI resultCallback) {
        call.enqueue(resultCallback);
        return resultCallback;
    }

    public void getDataInteracObject(final String url, final InteracAPI mResultCallback) {
        InteracWallet.loader.showLoader();
        Call<JsonElement> call = service.getDataInteracObject(url);
        RetrofitService.enqueue(call, mResultCallback);
    }

    public void postDataInteracObject(@NonNull final String url, final InteracAPI mResultCallback) {
        InteracWallet.loader.showLoader();
        String params = url.split("\\?")[1];
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), params);
        Call<JsonElement> call = service.postDataInteracObject(url.split("\\?")[0], body);
        RetrofitService.enqueue(call, mResultCallback);
    }
}
