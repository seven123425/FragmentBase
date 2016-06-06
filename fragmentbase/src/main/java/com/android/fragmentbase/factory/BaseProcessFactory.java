package com.android.fragmentbase.factory;

import com.android.fragmentbase.control.BaseFragmentControl;
import com.android.fragmentbase.process.PageProcess;
import com.android.fragmentbase.process.ShapeProcess;

public abstract class BaseProcessFactory {

    public PageProcess getProcess(String inputType) {
        return initProcess(inputType, getFragmentDefaultFactory(), getFragmentDefaultControl());
    }

    protected abstract PageProcess initProcess(String inputType, BaseFragmentFactory fragmentFactory, BaseFragmentControl fragmentControl);

    public PageProcess getShapeProcess(String inputType, PageProcess control) {
        return initShapeProcess(inputType, control, getFragmentDefaultFactory(), getFragmentDefaultControl());
    }

    protected abstract ShapeProcess initShapeProcess(String inputType, PageProcess control, BaseFragmentFactory fragmentFactory, BaseFragmentControl fragmentControl);

    protected abstract BaseFragmentControl getFragmentDefaultControl();

    protected abstract BaseFragmentFactory getFragmentDefaultFactory();
}
