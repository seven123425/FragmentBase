package com.android.fragmentbase.process;

import com.android.fragmentbase.control.BaseFragmentControl;
import com.android.fragmentbase.factory.BaseFragmentFactory;
import com.android.fragmentbase.fragment.page.BodyFragment;

public abstract class ShapeProcess extends BaseProcess {

    public ShapeProcess(PageProcess control, BaseFragmentFactory fragmentFactory, BaseFragmentControl fragmentControl) {
        super(control, fragmentFactory, fragmentControl);
    }

    public PageProcess getLastControl() {
        return control;
    }

    @Override
    public void nextPage() {
        BodyFragment fragment;
        if (pageCount >= -1 && pageCount < getPageString().length - 1) {
            pageCount++;
            fragment = fragmentFactory.getBodyWidget(getPageString()[pageCount]);
            fragmentControl.getBodyWidget().changeNextFragment(fragment);
            fragmentControl.setBodyWidget(fragment);
        }
    }

    @Override
    public void lastPage() {
        super.lastPage();
        if (pageCount <= 0) {
            backLastProcess();
        }
    }

    @Override
    protected boolean isFirstProcess() {
        return false;
    }

    protected abstract void backLastProcess();
}
