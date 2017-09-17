package com.example.cstead.imagegallery.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cstead on 9/4/17.
 */

public class Gif implements Parcelable {
    public String id;
    public Images images;

    public Gif() {

    }

    protected Gif(Parcel in) {
        id = in.readString();
        images = in.readParcelable(Images.class.getClassLoader());
    }

    public static final Creator<Gif> CREATOR = new Creator<Gif>() {
        @Override
        public Gif createFromParcel(Parcel source) {
            return new Gif(source);
        }

        @Override
        public Gif[] newArray(int size) {
            return new Gif[size];
        }
    };

    public String getStill() {
        if (this.images != null && this.images.fixedHeightStill != null) {
            return this.images.fixedHeightStill.url;
        }
        return "";
    }

    public String getFixedUrl() {
        if (this.images != null && this.images.fixedHeight != null) {
            return this.images.fixedHeight.url;
        }
        return "";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeParcelable(images, flags);
    }

}

