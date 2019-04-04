package com.ardovic.zimadtestcase;

import android.support.annotation.NonNull;

import com.ardovic.zimadtestcase.api.ApiClient;
import com.ardovic.zimadtestcase.api.ApiInterface;
import com.ardovic.zimadtestcase.api.ListData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter implements Contract.IPresenter, Callback<ListData> {

    private Contract.IView view;

    public Presenter(Contract.IView view, String tab) {
        this.view = view;
        ApiClient.getApiClient()
                .create(ApiInterface.class)
                .getListData(tab)
                .enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<ListData> call, @NonNull Response<ListData> response) {
        if (response.body() != null)
            view.populateList(response.body().getImages());
    }

    @Override
    public void onFailure(@NonNull Call<ListData> call, @NonNull Throwable t) {
        // do nothing
    }
}
