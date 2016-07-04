package com.android.fragmentbase.fragment.base;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.android.fragmentbase.R;

import static com.android.fragmentbase.util.LayoutFormat.getBitItemHeight;
import static com.android.fragmentbase.util.LayoutFormat.getBitItemWidth;

public abstract class PageFragment extends BaseFragment {

    private View fragmentView;
    private int fragmentAnimIn = R.anim.alpha_enter;
    private int fragmentAnimOut = R.anim.alpha_exit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            @SuppressWarnings("static-access")
            InputMethodManager inputManger = (InputMethodManager) activity
                    .getSystemService(activity.INPUT_METHOD_SERVICE);
            inputManger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        fragmentView = fragmentLayout(inflater, container);
        return fragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupComponents(fragmentView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected abstract View fragmentLayout(LayoutInflater inflater, ViewGroup container);

    protected abstract void setupComponents(View fragmentView);

    public void changeNextFragment(BaseFragment fragment) {
        changePage(fragment);
    }

    private void changePage(BaseFragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.setCustomAnimations(fragmentInAnim(), fragmentOutAnim());
        transaction.replace(this.getId(), fragment);
        transaction.commit();
        this.onDestroy();
    }

    private int fragmentInAnim(){
        return fragmentAnimIn;
    }

    private int fragmentOutAnim(){
        return fragmentAnimOut;
    }

    protected void setFragmentInAnim(int id){
        fragmentAnimIn = id;
    }

    protected void setFragmentOutAnim(int id){
        fragmentAnimOut = id;
    }

    protected abstract String LayoutType();

    public String fragmentLayoutType() {
        return LayoutType();
    }

    @Override
    protected int itemWidth() {
        return (int) getBitItemWidth();
    }

    @Override
    protected int itemHeight() {
        return (int) getBitItemHeight();
    }

    @Override
    protected String cacheFileName() {
        return null;
    }

    public String getPageTag() {
        return this.toString();
    }
}
