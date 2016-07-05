package com.android.fragmentbase.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import com.android.fragmentbase.cache.ImageFetcher;

public abstract class CustomizeAdapter extends BaseAdapter {

    protected Context context;
    protected Activity activity;
    protected LayoutInflater inflater;
    protected ImageFetcher fetcher;

    public CustomizeAdapter(Context context) {
        this(context, null);
    }

    public CustomizeAdapter(Context context, ImageFetcher fetcher) {
        this.context = context;
        this.activity = (Activity) context;
        this.fetcher = fetcher;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
