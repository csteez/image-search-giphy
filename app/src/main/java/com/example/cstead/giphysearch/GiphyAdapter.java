package com.example.cstead.giphysearch;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.cstead.giphysearch.Activities.GifDetailActivity;
import com.example.cstead.giphysearch.Activities.MainActivity;
import com.example.cstead.giphysearch.Models.Gif;

import java.util.List;

/**
 * Created by cstead on 9/9/17.
 */

public class GiphyAdapter extends RecyclerView.Adapter<GiphyAdapter.MyViewHolder> {
    private List<Gif> mGifList;
    private Context mContext;
    private MainActivity mMainActivity;

    public GiphyAdapter(Context context, List<Gif> gifList, MainActivity activity) {
        mContext = context;
        mGifList = gifList;
        mMainActivity = activity;
    }

    @Override
    public GiphyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.item_photo, parent, false);
        GiphyAdapter.MyViewHolder viewHolder =
                new GiphyAdapter.MyViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GiphyAdapter.MyViewHolder holder, int position) {
        Gif gif = mGifList.get(position);
        ImageView imageView = holder.mPhotoImageView;

        GlideUrl glideUrl  = new GlideUrl(gif.getStill(), new LazyHeaders.Builder()
                .addHeader(Constants.ACCEPT_HEADER, Constants.ACCEPT_HEADER_VALUE).build());

        Glide.with(mContext)
                .load(glideUrl)
                .error(R.drawable.ic_cloud_off_red)
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return mGifList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView mPhotoImageView;

        public MyViewHolder (View itemView) {
            super(itemView);
            mPhotoImageView = (ImageView) itemView.findViewById(R.id.iv_photo);
            itemView.setOnClickListener(this);
        }

        public void onClick(View view) {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                GiphyToast.killToast();
                Gif gif = mGifList.get(position);
                Intent intent = new Intent(mContext, GifDetailActivity.class);
                intent.putExtra(GifDetailActivity.EXTRA_GIF, gif);
                mMainActivity.startActivity(intent);
            }
        }
    }
}