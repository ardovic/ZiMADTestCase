package com.ardovic.zimadtestcase.ui.list;

import com.ardovic.zimadtestcase.data.Image;
import com.ardovic.zimadtestcase.data.ListData;

import java.util.List;

public interface ContractMVP {

    interface IView {
        void populateList(List<Image> images);
        void hideProgressBar();
    }

    interface IPresenter {
        void onImageClicked(Image image);
    }

    interface IModel {
        ListData getCachedData(String tab);
        void saveData(ListData data, String tab);
    }
}
