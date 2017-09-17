package com.example.cstead.imagegallery.Models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cstead on 9/4/17.
 */

public class GifCollection {

    @SerializedName("data")
    public List<Gif> gifs;

    public GifCollection() {
        gifs = new ArrayList<>();
    }

    public static GifCollection parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        GifCollection gifCollection = gson.fromJson(response, GifCollection.class);
        return gifCollection;
    }

}
