package com.android.fragmentbase.control;

import android.app.Activity;
import android.app.FragmentTransaction;

import com.android.fragmentbase.fragment.base.PageFragment;
import com.android.fragmentbase.fragment.page.BodyFragment;
import com.android.fragmentbase.fragment.page.ChildFragment;
import com.android.fragmentbase.fragment.page.MenuFragment;
import com.android.fragmentbase.fragment.page.TitleFragment;

public class BaseFragmentControl {

    private static BaseFragmentControl instance = new BaseFragmentControl();

    protected BaseFragmentControl() {

    }

    public static BaseFragmentControl getInstance() {
        return instance;
    }

    private int titleID;
    private BodyFragment bodyFragment;
    private TitleFragment titleFragment;
    private ChildFragment childFragment;
    private MenuFragment menuFragment;

    public void setBodyWidget(BodyFragment fragment) {
        bodyFragment = fragment;
    }

    public void setTitleWidget(TitleFragment fragment) {
        titleFragment = fragment;
    }

    public void setChildWidget(ChildFragment fragment) {
        childFragment = fragment;
    }

    public void setMenuWidget(MenuFragment fragment) {
        menuFragment = fragment;
    }

    public BodyFragment getBodyWidget() {
        return bodyFragment;
    }

    public TitleFragment getTitleWidget() {
        return titleFragment;
    }

    public ChildFragment getChildWidget() {
        return childFragment;
    }

    public MenuFragment getMenuWidget() {
        return menuFragment;
    }

    public void changeNextPage(BaseProcessControl control) {
        control.getProcess().nextPage();
    }

    public void changeLastPage(BaseProcessControl control) {
        control.getProcess().lastPage();
    }

    public void initFragmentInLayout(int id, Activity activity, PageFragment fragment) {
        FragmentTransaction transaction = activity.getFragmentManager().beginTransaction();
        transaction.replace(id, fragment);
        transaction.commitAllowingStateLoss();
    }

    public void initTitleLayout(int id) {
        titleID = id;
    }
}
