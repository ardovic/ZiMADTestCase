package com.ardovic.zimadtestcase.data;

import android.content.SharedPreferences;

import com.ardovic.zimadtestcase.ui.list.ContractMVP;
import com.google.gson.Gson;

public class Model implements ContractMVP.IModel {

    private SharedPreferences prefs;

    public Model(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    @Override
    public ListData getCachedData(String tab) {
        String cachedJsonString = prefs.getString(tab, null);
        return cachedJsonString != null ? new Gson().fromJson(cachedJsonString, ListData.class) : null;
    }

    @Override
    public void saveData(ListData data, String tab) {
        prefs.edit().putString(tab, new Gson().toJson(data)).apply();
    }
}
