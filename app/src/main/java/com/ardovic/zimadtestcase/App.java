package com.ardovic.zimadtestcase;

import android.app.Application;

import com.ardovic.zimadtestcase.api.ApiClient;
import com.ardovic.zimadtestcase.api.ApiInterface;
import com.ardovic.zimadtestcase.data.Data;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class App extends Application {

    private static App instance;
    private ApiInterface apiInterface;
    private Data data;

    @Override
    public void onCreate() {
        super.onCreate();

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        data = new Data();

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);

    }

    public static App getInstance() {
        return instance;
    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }

    public Data getData() {
        return data;
    }
}
