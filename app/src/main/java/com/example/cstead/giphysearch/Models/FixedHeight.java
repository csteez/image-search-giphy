package com.example.cstead.giphysearch.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cstead on 9/4/17.
 */

public class FixedHeight implements Parcelable {
    public String url;

    protected FixedHeight(Parcel in) {
        url = in.readString();
    }

    public static final Creator<FixedHeight> CREATOR = new Creator<FixedHeight>() {
        @Override
        public FixedHeight createFromParcel(Parcel source) {
            return new FixedHeight(source);
        }

        @Override
        public FixedHeight[] newArray(int size) {
            return new FixedHeight[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
    }
}
