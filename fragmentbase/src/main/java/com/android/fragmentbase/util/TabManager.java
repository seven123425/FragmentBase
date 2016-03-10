package com.android.fragmentbase.util;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class TabManager implements TabHost.OnTabChangeListener {

    enum tabKey{name};

    private final FragmentActivity mActivity;
    private final TabHost mTabHost;
    private final int mContainerId;
    private View lasttabview;
    private final HashMap<String, TabInfo> mTabs = new HashMap<String, TabInfo>();
    private HashMap<String, HashMap<String, String>> mTabkey = new HashMap<String, HashMap<String, String>>();

    TabInfo mLastTab;

    static final class TabInfo {
        private final String tag;
        private final Class<?> clss;
        private final Bundle args;
        private Fragment fragment;

        TabInfo(String _tag, Class<?> _class, Bundle _args) {
            tag = _tag;
            clss = _class;
            args = _args;
        }
    }

    static class DummyTabFactory implements TabHost.TabContentFactory {
        private final Context mContext;

        public DummyTabFactory(Context context) {
            mContext = context;
        }

        @Override
        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }
    }

    public TabManager(FragmentActivity activity, TabHost tabHost, int containerId, ArrayList<HashMap<String, String>> data) {
        mActivity = activity;
        mTabHost = tabHost;
        mContainerId = containerId;
        mTabHost.setOnTabChangedListener(this);
        for (HashMap<String, String> tabkey : data) {
            if (null != tabkey.get(tabKey.name.toString())) {
                mTabkey.put(tabkey.get(tabKey.name.toString()), tabkey);
            }
        }
    }

    public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
        tabSpec.setContent(new DummyTabFactory(mActivity));
        String tag = tabSpec.getTag();

        TabInfo info = new TabInfo(tag, clss, args);

        info.fragment = mActivity.getSupportFragmentManager().findFragmentByTag(tag);
        if (info.fragment != null && !info.fragment.isDetached()) {
            FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
            ft.detach(info.fragment);
            ft.commit();
        }

        mTabs.put(tag, info);
        mTabHost.addTab(tabSpec);
    }

    @Override
    public void onTabChanged(String tabId) {
        TabInfo newTab = mTabs.get(tabId);
        if (null != mTabkey.get(tabId)) {
            changeTab(mTabkey.get(tabId));
            if (lasttabview != null) {
                Animation fadeOut = new AlphaAnimation((float) 0.8, (float) 1);
                fadeOut.setDuration(200);
                lasttabview.setAnimation(fadeOut);
            }
            Animation fadeIn = new AlphaAnimation((float) 0.5, 1);
            fadeIn.setDuration(300);
            mTabHost.getCurrentTabView().setAnimation(fadeIn);
        }
        if (mLastTab != newTab) {
            FragmentTransaction ft = mActivity.getSupportFragmentManager().beginTransaction();
            if (mLastTab != null) {
                if (mLastTab.fragment != null) {
                    ft.detach(mLastTab.fragment);
                }
            }

            if (newTab != null) {
                newTab.fragment = Fragment.instantiate(mActivity,
                        newTab.clss.getName(), newTab.args);
//                ft.add(mContainerId, newTab.fragment, newTab.tag);
                if (newTab.fragment == null) {
                    ft.detach(mLastTab.fragment);
                } else {
//                    mActivity.getSupportFragmentManager().popBackStack();
                    ft.replace(mContainerId, newTab.fragment);
                    ft.attach(newTab.fragment);
                }
            }

            mLastTab = newTab;
            ft.commit();
            lasttabview = mTabHost.getCurrentTabView();
//            mActivity.getSupportFragmentManager().executePendingTransactions();
        }
    }

    public abstract void changeTab(HashMap<String, String> tabMap);
}