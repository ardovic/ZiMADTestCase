package com.ardovic.zimadtestcase;

public class DTO {

    private String url;
    private String title;

    public DTO(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}
