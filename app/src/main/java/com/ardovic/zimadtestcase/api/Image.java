package com.ardovic.zimadtestcase.api;

import com.google.gson.annotations.Expose;

public class Image {

    @Expose
    private String url;
    @Expose
    private String title;

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}
