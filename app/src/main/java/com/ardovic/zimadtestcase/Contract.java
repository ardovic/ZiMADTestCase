package com.ardovic.zimadtestcase;

import com.ardovic.zimadtestcase.api.Image;

import java.util.List;

public interface Contract {

    interface IView {

        void populateList(List<Image> images);
    }

    interface IPresenter {

    }

    interface IModel {

        List<Image> getCachedData(String tab);

        void saveData(List<Image> objectList, String tab);
    }
}
