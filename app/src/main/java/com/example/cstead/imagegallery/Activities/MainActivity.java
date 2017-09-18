package com.example.cstead.imagegallery.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.cstead.imagegallery.Constants;
import com.example.cstead.imagegallery.GiphyAccessor;
import com.example.cstead.imagegallery.GiphyToast;
import com.example.cstead.imagegallery.ImageGalleryAdapter;
import com.example.cstead.imagegallery.Models.Gif;
import com.example.cstead.imagegallery.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private EditText mSearchTextView;
    private static Context mContext;
    private GiphyAccessor mGiphyAccessor;
    private TextView mErrorView;
    private RequestQueue mRequestQueue;
    private GridLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecyclerView();
        setupTextViews();
        mContext = this;

        createRequestQueue();
        mGiphyAccessor = new GiphyAccessor(Constants.API_KEY, Constants.MAX_GIFS, mContext, mRequestQueue);

        getTrendingGifs();
    }

    private void setupRecyclerView() {
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_images);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addOnScrollListener(scrollListener);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void setupTextViews() {
        mErrorView = (TextView) findViewById(R.id.errorView);
        mSearchTextView = (EditText)findViewById(R.id.searchTextView);
        //add watcher
        mSearchTextView.addTextChangedListener(searchTextWatcher);
    }

    private void createRequestQueue() {
        mRequestQueue = Volley.newRequestQueue(mContext);
    }

    private final TextWatcher searchTextWatcher = new TextWatcher(){
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        public void afterTextChanged(Editable s) {
            String input = mSearchTextView.getText().toString();
            getGifsByUserInput(input);
        }
    };

    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener()
    {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy)
        {
            if (dy <=0) return;
            int visibleItemCount = mLayoutManager.getChildCount();
            int totalItemCount = mLayoutManager.getItemCount();
            int pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

            if ((visibleItemCount + pastVisiblesItems) >= totalItemCount)
            {
                GiphyToast.showToast(mContext);
            }
        }
    };

    private void getGifsByUserInput(String input) {
        if (input != null && input != "") {
            mGiphyAccessor.getGifsByInput(input, new GiphyAccessor.VolleyCallback() {
                @Override
                public void onSuccess(List<Gif> gifList) {
                    if (gifList == null || gifList.size() == 0) {
                        toggleVisibility(true);
                    } else {
                        toggleVisibility(false);
                        ImageGalleryAdapter adapter = new ImageGalleryAdapter(mContext, gifList,
                                (MainActivity) mContext);
                        mRecyclerView.setAdapter(adapter);
                    }
                }
                @Override
                public void onError(String message) {
                    mErrorView.setText(message);
                    toggleVisibility(true);
                }
            });
        }
    }

    private void getTrendingGifs() {
        mGiphyAccessor.getTrendingGifs(new GiphyAccessor.VolleyCallback() {
            @Override
            public void onSuccess(List<Gif> gifList) {
                toggleVisibility(false);
                ImageGalleryAdapter adapter = new ImageGalleryAdapter(mContext, gifList,
                        (MainActivity) mContext);
                mRecyclerView.setAdapter(adapter);
            }
            @Override
            public void onError(String message) {
                mErrorView.setText(message);
                toggleVisibility(true);
            }
        });
    }

    private void toggleVisibility(boolean showError) {
        if (showError) {
            mErrorView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mErrorView.setVisibility(View.GONE);
        }
    }

}
