package com.ardovic.zimadtestcase.api;

import com.google.gson.annotations.Expose;

import java.util.List;

public class ListData {

    @Expose
    private String message;
    @Expose
    private List<Image> images = null;

    public String getMessage() {
        return message;
    }

    public List<Image> getImages() {
        return images;
    }
}

