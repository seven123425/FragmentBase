package com.android.fragmentbase.control;

import com.android.fragmentbase.factory.BaseProcessFactory;
import com.android.fragmentbase.process.PageProcess;

public abstract class BaseProcessControl {

    private static BaseProcessControl instance = new BaseProcessControl() {
        @Override
        protected PageProcess homeProcess() {
            return null;
        }
    };

    protected BaseProcessControl() {

    }

    public static BaseProcessControl getInstance() {
        return instance;
    }

    private PageProcess process = homeProcess();

    protected abstract PageProcess homeProcess();

    public void initPageControl(BaseProcessFactory factory, String inputType) {
        process = factory.getProcess(inputType);
    }

    protected void initShapePageControl(BaseProcessFactory factory, String inputType) {
        process = factory.getShapeProcess(inputType, process);
    }

    PageProcess getProcess() {
        return process;
    }
}
