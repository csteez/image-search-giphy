package com.example.cstead.giphysearch.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.cstead.giphysearch.Constants;
import com.example.cstead.giphysearch.Models.Gif;
import com.example.cstead.giphysearch.R;

/**
 * Created by cstead on 9/4/17.
 */

public class GifDetailActivity extends AppCompatActivity {
    public static final String EXTRA_GIF = "GifDetailActivity.GIF";
    private ImageView mImageView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);

        mImageView = (ImageView) findViewById(R.id.image);
        Gif gif = getIntent().getParcelableExtra(EXTRA_GIF);

        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setText(gif.getFixedUrl());

        GlideUrl glideUrl = new GlideUrl(gif.getFixedUrl(), new LazyHeaders.Builder()
                .addHeader(Constants.ACCEPT_HEADER, Constants.ACCEPT_HEADER_VALUE).build());

        Glide.with(this)
            .load(glideUrl)
            .error(R.drawable.ic_cloud_off_red)
            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
            .into(mImageView);
    }

}
