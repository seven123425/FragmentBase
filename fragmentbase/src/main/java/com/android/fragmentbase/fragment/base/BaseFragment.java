package com.android.fragmentbase.fragment.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import com.android.fragmentbase.cache.ImageCache;
import com.android.fragmentbase.cache.ImageFetcher;

import org.apache.commons.lang3.StringUtils;

public abstract class BaseFragment extends Fragment {

    protected enum typeEnum {title, body, child, menu, other}

    protected ImageFetcher fetcher;
    protected Activity activity;
    private int width = 0 ,height = 0;
    private String file = "";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkSizeAndFile();
        ImageCache.ImageCacheParams cacheParams = new ImageCache.ImageCacheParams(activity, file);
        cacheParams.setMemCacheSizePercent(0.25f);
        fetcher = new ImageFetcher(activity,width ,height);
        fetcher.addImageCache(activity.getFragmentManager(), cacheParams);
    }

    protected abstract int itemWidth();

    protected abstract int itemHeight();

    protected abstract String cacheFileName();

    private void checkSizeAndFile(){
        if(itemWidth() == 0){
            width = 50;
        }else {
            width = itemWidth();
        }
        if(itemHeight() == 0){
           height = 50;
        }else {
            height = itemHeight();
        }
        if(StringUtils.isBlank(cacheFileName())){
            file = "/imageCache/";
        }else{
            file = cacheFileName();
        }
    }
}
