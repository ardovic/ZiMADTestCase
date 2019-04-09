package com.ardovic.zimadtestcase.api;

import com.ardovic.zimadtestcase.data.ListData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/xim/api.php")
    Call<ListData> getListData(@Query("query") String type);
}
