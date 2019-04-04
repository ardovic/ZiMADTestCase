package com.ardovic.zimadtestcase;

interface NetworkListener<T> {

    void onSuccess(T data);
    void onError(String... error);
}
