package com.example.cstead.giphysearch.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cstead on 9/4/17.
 */

public class FixedHeightStill implements Parcelable {
    public String url;

    protected FixedHeightStill(Parcel in) {
        url = in.readString();
    }

    public static final Creator<FixedHeightStill> CREATOR = new Creator<FixedHeightStill>() {
        @Override
        public FixedHeightStill createFromParcel(Parcel source) {
            return new FixedHeightStill(source);
        }

        @Override
        public FixedHeightStill[] newArray(int size) {
            return new FixedHeightStill[size];
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
