package com.android.fragmentbase.fragment.page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.fragmentbase.control.BaseFragmentControl;
import com.android.fragmentbase.factory.BaseFragmentFactory;
import com.android.fragmentbase.fragment.base.BaseFragment;
import com.android.fragmentbase.fragment.base.PageFragment;

public abstract class BodyFragment extends PageFragment {

    @Override
    protected String LayoutType() {
        return typeEnum.body.toString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BaseFragmentControl.getInstance().setBodyWidget(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract View fragmentLayout(LayoutInflater inflater, ViewGroup container);

    protected abstract void setupComponents(View fragmentView);

    public BaseFragment getNextFragment(BaseFragmentFactory factory, String pageType){
        return factory.getBodyWidget(pageType);
    }

    public abstract TitleFragment getTitleFragment();
}
