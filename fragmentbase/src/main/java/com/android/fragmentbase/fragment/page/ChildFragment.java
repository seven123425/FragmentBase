package com.android.fragmentbase.fragment.page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fragmentbase.fragment.base.PageFragment;

public abstract class ChildFragment extends PageFragment {
    @Override
    protected String LayoutType() {
        return typeEnum.child.toString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract View fragmentLayout(LayoutInflater inflater, ViewGroup container);

    protected abstract void setupComponents(View fragmentView);

}
