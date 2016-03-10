package com.android.fragmentbase.fragment.page;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.android.fragmentbase.control.BaseFragmentControl;
import com.android.fragmentbase.fragment.base.PageFragment;

public abstract class MenuFragment extends PageFragment {

    private View menuView;
    private boolean isShow;
    private Animation animation;
    protected Handler handler = new Handler();

    @Override
    protected String LayoutType() {
        return typeEnum.menu.toString();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        menuView = super.onCreateView(inflater, container, savedInstanceState);
        menuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        isShow = initVisibleStatus();
        if (isShow) {
            menuView.setVisibility(View.VISIBLE);
        } else {
            menuView.setVisibility(View.GONE);
        }
        BaseFragmentControl.getInstance().setMenuWidget(this);
        return menuView;
    }

    protected abstract View fragmentLayout(LayoutInflater inflater, ViewGroup container);

    protected abstract void setupComponents(View fragmentView);

    public boolean getStatus() {
        return isShow;
    }

    protected abstract boolean initVisibleStatus();

    public void setStatus(boolean status) {
        isShow = status;
        if (status) {
            animation = openAnim();
            menuOpen();
        } else {
            animation = closeAnim();
            menuClose();
        }
    }

    protected abstract Animation openAnim();

    protected abstract Animation closeAnim();

    protected void menuOpen() {
        menuView.setVisibility(View.VISIBLE);
        menuView.startAnimation(animation);
    }

    protected void menuClose() {
        menuView.startAnimation(animation);
        handler.postDelayed(new Runnable() {
            public void run() {
                if (!isShow) {
                    menuView.setVisibility(View.GONE);
                }
            }
        }, 500);
    }
}
