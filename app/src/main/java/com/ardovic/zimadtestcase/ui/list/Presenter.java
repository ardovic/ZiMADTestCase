package com.ardovic.zimadtestcase.ui.list;

import androidx.annotation.NonNull;

import com.ardovic.zimadtestcase.App;
import com.ardovic.zimadtestcase.api.ApiClient;
import com.ardovic.zimadtestcase.api.ApiInterface;
import com.ardovic.zimadtestcase.data.Image;
import com.ardovic.zimadtestcase.data.ListData;
import com.ardovic.zimadtestcase.ui.FragmentCommunicator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter implements ContractMVP.IPresenter, Callback<ListData> {

    private ContractMVP.IView view;
    private FragmentCommunicator communicator;
    private String tab;

    Presenter(ContractMVP.IView view, String tab, FragmentCommunicator communicator) {
        this.view = view;
        this.communicator = communicator;
        this.tab = tab;
        ListData cachedData = App.getInstance().getModel().getCachedData(tab);
        if (cachedData != null) view.populateList(cachedData.getImages());
        ApiClient.getApiClient()
                .create(ApiInterface.class)
                .getListData(tab)
                .enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<ListData> call, @NonNull Response<ListData> response) {
        if (response.body() != null) {
            App.getInstance().getModel().saveData(response.body(), tab);
            view.populateList(response.body().getImages());
        } else {
            // do nothing
        }
    }

    @Override
    public void onFailure(@NonNull Call<ListData> call, @NonNull Throwable t) {
        view.hideProgressBar();
        communicator.showNetworkError();
    }

    @Override
    public void onImageClicked(Image image) {
        communicator.showFullImage(image);
    }
}
