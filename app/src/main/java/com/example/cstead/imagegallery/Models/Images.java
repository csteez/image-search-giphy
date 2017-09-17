package com.example.cstead.imagegallery.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cstead on 9/4/17.
 */

public class Images implements Parcelable {
    @SerializedName("fixed_height_still")
    public FixedHeightStill fixedHeightStill;

    @SerializedName("fixed_height")
    public FixedHeight fixedHeight;

    protected Images(Parcel in) {
        fixedHeightStill = in.readParcelable(FixedHeightStill.class.getClassLoader());
        fixedHeight = in.readParcelable(FixedHeight.class.getClassLoader());
    }

    public static final Creator<Images> CREATOR = new Creator<Images>() {
        @Override
        public Images createFromParcel(Parcel source) {
            return new Images(source);
        }

        @Override
        public Images[] newArray(int size) {
            return new Images[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(fixedHeightStill, flags);
        dest.writeParcelable(fixedHeight, flags);
    }
}
