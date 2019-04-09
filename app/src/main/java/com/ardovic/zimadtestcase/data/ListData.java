package com.ardovic.zimadtestcase.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListData {

    @Expose
    private String message;
    @Expose
    @SerializedName("data")
    private List<Image> images = null;

    public String getMessage() {
        return message;
    }

    public List<Image> getImages() {
        return images;
    }
}

