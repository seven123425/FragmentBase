package com.android.fragmentbase.factory;

import com.android.fragmentbase.fragment.page.BodyFragment;
import com.android.fragmentbase.fragment.page.ChildFragment;
import com.android.fragmentbase.fragment.page.MenuFragment;
import com.android.fragmentbase.fragment.page.OtherFragment;

public abstract class BaseFragmentFactory {

    public BodyFragment getBodyWidget(String inputType) {
        return initBodyFragment(inputType);
    }

    protected abstract BodyFragment initBodyFragment(String inputType);

    public ChildFragment getChildWidget(String inputType) {
        return initChildFragment(inputType);
    }

    protected abstract ChildFragment initChildFragment(String inputType);

    public MenuFragment getMenuWidget(String inputType) {
        return initMenuFragment(inputType);
    }

    protected abstract MenuFragment initMenuFragment(String inputType);

    public OtherFragment getOtherWidget(String inputType) {
        return initOtherFragment(inputType);
    }

    protected abstract OtherFragment initOtherFragment(String inputType);

}
