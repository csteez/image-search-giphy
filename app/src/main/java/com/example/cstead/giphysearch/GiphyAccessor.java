package com.example.cstead.giphysearch;

import android.content.Context;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cstead.giphysearch.Models.Gif;
import com.example.cstead.giphysearch.Models.GifCollection;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.List;

/**
 * Created by cstead on 9/4/17.
 */

public class GiphyAccessor {
    private String mApiKey;
    private int mMaxGifs;
    private Context mContext;
    private RequestQueue mRequestQueue;


    public GiphyAccessor(String apiKey, int maxGifs, Context context, RequestQueue requestQueue) {
        mApiKey = apiKey;
        mMaxGifs = maxGifs;
        mContext = context;
        mRequestQueue = requestQueue;
    }

    public void getTrendingGifs(final VolleyCallback callback) {
        String url = getTrendingUrl();
        addToRequestQueue(getJsonObjectRequest(url, callback));
    }

    public void getGifsByInput(final String input, final VolleyCallback callback) {
        String url = getSearchUrl(input);
        addToRequestQueue(getJsonObjectRequest(url, callback));
    }

    public JsonObjectRequest getJsonObjectRequest(String url, final VolleyCallback callback) {
        return new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        GifCollection gifCollection = GifCollection.parseJSON(response.toString());
                        if(callback!=null){
                            callback.onSuccess(gifCollection.gifs);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (callback != null) {
                    String message = "An error occurred";

                    if (error instanceof NetworkError) {
                        message = "No Internet service - Please check your connection.";
                    } else if (error instanceof ServerError) {
                        message = "Server error. Please try again later.";
                    } else if (error instanceof NoConnectionError) {
                        message = "No Internet service - Please check your connection.";
                    } else if (error instanceof TimeoutError) {
                        message = "Connection timed out - Please check your internet connection.";
                    }
                    callback.onError(message);
                }
            }
        });
    }

    public String getSearchUrl(final String search) {
        String encoded = URLEncoder.encode(search);
        return "http://api.giphy.com/v1/gifs/search?api_key=" + mApiKey + "&q=" + encoded;
    }

    public String getTrendingUrl() {
        return "http://api.giphy.com/v1/gifs/trending?api_key=" + mApiKey + "&limit=" + mMaxGifs;
    }

    public interface VolleyCallback {
        void onSuccess(List<Gif> gifList);
        void onError(String message);
    }

    private void addToRequestQueue(JsonObjectRequest jsonObjectRequest) {
        if (mRequestQueue == null) {
           mRequestQueue = Volley.newRequestQueue(mContext);
        }
        mRequestQueue.add(jsonObjectRequest);
    }
}
