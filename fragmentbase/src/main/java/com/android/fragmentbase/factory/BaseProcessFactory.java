package com.android.fragmentbase.factory;

import com.android.fragmentbase.process.PageProcess;
import com.android.fragmentbase.process.ShapeProcess;

public abstract class BaseProcessFactory {

    public PageProcess getProcess(String inputType) {
        return initProcess(inputType);
    }

    protected abstract PageProcess initProcess(String inputType);

    public PageProcess getShapeProcess(String inputType, PageProcess control) {
        return initShapeProcess(inputType, control);
    }

    protected abstract ShapeProcess initShapeProcess(String inputType, PageProcess control);

}
