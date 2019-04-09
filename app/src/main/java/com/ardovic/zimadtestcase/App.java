package com.ardovic.zimadtestcase;

import android.app.Application;

import com.ardovic.zimadtestcase.api.ApiClient;
import com.ardovic.zimadtestcase.api.ApiInterface;
import com.ardovic.zimadtestcase.data.Model;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class App extends Application {

    private static final String PREFS = "PREFS";
    private static App instance;
    private Model model;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        model = new Model(getApplicationContext().getSharedPreferences(PREFS, MODE_PRIVATE));

        Picasso picasso = new Picasso.Builder(this)
                .downloader(new OkHttp3Downloader(this, Integer.MAX_VALUE))
                .build();
        picasso.setLoggingEnabled(true);
        Picasso.setSingletonInstance(picasso);
    }

    public static App getInstance() {
        return instance;
    }

    public Model getModel() {
        return model;
    }
}
