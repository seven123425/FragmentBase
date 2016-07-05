package com.android.fragmentbase;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.android.fragmentbase.control.BaseFragmentControl;
import com.android.fragmentbase.control.BaseProcessControl;
import com.android.fragmentbase.fragment.page.BodyFragment;

public abstract class BaseFragmentActivity extends Activity {

    private BodyFragment bodyFragment = bodyFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_fragment);
        getFragmentDefaultControl().setBodyWidget(bodyFragment);
        getFragmentDefaultControl().initFragmentInLayout(R.id.body, this, bodyFragment);
        if(bodyFragment.getTitleFragment() != null){
            getFragmentDefaultControl().setTitleWidget(bodyFragment.getTitleFragment());
            getFragmentDefaultControl().initFragmentInLayout(R.id.title, this, bodyFragment.getTitleFragment());
        }else {
            getFragmentDefaultControl().initTitleLayout(R.id.title);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(!beckEvent()){
                getFragmentDefaultControl().changeLastPage(getProcessDefaultControl());
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected abstract boolean beckEvent();

    protected abstract BodyFragment bodyFragment();

    protected abstract BaseProcessControl getProcessDefaultControl();

    protected abstract BaseFragmentControl getFragmentDefaultControl();

}
