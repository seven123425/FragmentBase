package com.android.fragmentbase.cache;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ImageSupView extends ImageView {

    public ImageSupView(Context context) {
        super(context);
    }

    public ImageSupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageSupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ImageSupView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setBackground(Drawable background) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            super.setBackground(background);
        } else {
            setBackgroundDrawable(background);
        }
    }
}
