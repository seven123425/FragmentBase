package com.android.fragmentbase.adapter.item;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.android.fragmentbase.cache.ImageFetcher;

public abstract class ItemBase {

    private View itemView;
    protected Activity activity;
    protected LayoutInflater inflater;
    protected ImageFetcher fetcher;

    public ItemBase(Activity activity, ImageFetcher fetcher) {
        this.fetcher = fetcher;
        this.activity = activity;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    protected abstract View itemViewInit();

    public View getItemView() {
        if (itemView == null) {
            itemView = itemViewInit();
        }
        return itemView;
    }

    //avoid item click event not trigger
    public View getItemView(View convertView) {
        if (convertView != null) {
            itemView = convertView;
        }else{
            itemView = itemViewInit();
        }
        return itemView;
    }

}
