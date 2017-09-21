package com.example.cstead.giphysearch;

import android.content.Context;

import com.android.volley.RequestQueue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.URLEncoder;

import static org.junit.Assert.assertEquals;

/**
 * Created by cstead on 9/9/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class GiphyAccessorTest {
    @Mock
    private Context mMockContext;
    @Mock
    private GiphyAccessor.VolleyCallback volleyCallback;
    @Mock
    private RequestQueue mRequestQueue;
    private int mMaxGifs = 1;
    private String mApiKey = "api_key";

    @Test
    public void getSearchUrl_Test() {
        GiphyAccessor giphyAccessor = new GiphyAccessor(mApiKey, mMaxGifs, mMockContext, mRequestQueue);
        String search = "test";
        String expected = "http://api.giphy.com/v1/gifs/search?api_key=" + mApiKey + "&q=" + search;
        assertEquals(expected, giphyAccessor.getSearchUrl(search));
    }

    @Test
    public void getSearchUrl_ReturnsUrlEncoded() {
        GiphyAccessor giphyAccessor = new GiphyAccessor(mApiKey, mMaxGifs, mMockContext, mRequestQueue);
        String search = "some string with weirdCharacters 2)++%%\\&";
        String encoded = URLEncoder.encode(search);
        String expected = "http://api.giphy.com/v1/gifs/search?api_key=" + mApiKey + "&q=" + encoded;
        assertEquals(expected, giphyAccessor.getSearchUrl(search));
    }

    @Test
    public void getTrendingUrl_Test() {
        GiphyAccessor giphyAccessor = new GiphyAccessor(mApiKey, mMaxGifs, mMockContext, mRequestQueue);
        String expected = "http://api.giphy.com/v1/gifs/trending?api_key=" + mApiKey + "&limit=" + mMaxGifs;
        assertEquals(expected, giphyAccessor.getTrendingUrl());
    }

}
