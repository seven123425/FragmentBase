package com.android.fragmentbase.process;

import com.android.fragmentbase.control.BaseFragmentControl;
import com.android.fragmentbase.factory.BaseFragmentFactory;
import com.android.fragmentbase.fragment.page.BodyFragment;

public abstract class BaseProcess implements PageProcess {

    protected int pageCount = 0;

    protected abstract String[] getPageString();

    protected BaseFragmentFactory fragmentFactory;

    protected BaseFragmentControl fragmentControl;

    public BaseProcess(BaseFragmentFactory fragmentFactory, BaseFragmentControl fragmentControl) {
        if (!isFirstProcess()) {
            pageCount = -1;
        }
        this.fragmentFactory = fragmentFactory;
        this.fragmentControl = fragmentControl;
    }

    @Override
    public void nextPage() {
        BodyFragment fragment;
        if (pageCount >= 0 && pageCount < getPageString().length - 1) {
            pageCount++;
            fragment = fragmentFactory.getBodyWidget(getPageString()[pageCount]);
            fragmentControl.getBodyWidget().changeNextFragment(fragment);
            fragmentControl.setBodyWidget(fragment);
        }
    }

    @Override
    public void lastPage() {
        BodyFragment fragment;
        if (pageCount > 0) {
            pageCount--;
            fragment = fragmentFactory.getBodyWidget(getPageString()[pageCount]);
            fragmentControl.getBodyWidget().changeNextFragment(fragment);
            fragmentControl.setBodyWidget(fragment);
        }
    }

    protected boolean isFirstProcess() {
        return true;
    }

    @Override
    public void callByOtherLine() {
        pageCount++;
    }
}
