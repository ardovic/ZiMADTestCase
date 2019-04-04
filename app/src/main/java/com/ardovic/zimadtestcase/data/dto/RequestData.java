package com.ardovic.zimadtestcase.data.dto;

import android.os.Parcel;
import android.os.Parcelable;

public class RequestData implements Parcelable {

    public int page;

    public RequestData(int page) {
        this.page = page;
    }

    public static final Creator<RequestData> CREATOR = new Creator<RequestData>() {
        @Override
        public RequestData createFromParcel(Parcel in) {
            return new RequestData(in);
        }

        @Override
        public RequestData[] newArray(int size) {
            return new RequestData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
    }

    protected RequestData(Parcel in) {
        page = in.readInt();
    }
}

