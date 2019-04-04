package com.ardovic.zimadtestcase.data;

import com.ardovic.zimadtestcase.Contract;
import com.ardovic.zimadtestcase.api.Image;

import java.util.List;

public class Data implements Contract.IModel {


    @Override
    public List<Image> getCachedData(String tab) {
        return null;
    }

    @Override
    public void saveData(List<Image> objectList, String tab) {

    }
}
