package com.android.fragmentbase.process;

public abstract class ShapeProcess extends BaseProcess {

    protected PageProcess control;

    public ShapeProcess(PageProcess control) {
        this.control = control;
   }

    public PageProcess getLastControl() {
        return control;
    }
}
