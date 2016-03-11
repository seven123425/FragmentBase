package com.android.fragmentbase.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class LayoutFormat {

    public enum parentLayoutType {LinearLayout, RelativeLayout, FrameLayout}

    public enum paddingType {Top, Bot, Left, Right}

    private enum deviceSizeUseType {Width, Height}

    public static int dWidth;
    public static int dHeight;
    public static int vWidth;
    public static int vHeight;
    public static double screenInches;
    public static int defaultScreenSize = 7;

    private static int getActionBarHeight(Context context) {
        int actionBarHeight = 0;
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }

    public static void findDeviceSizeUseWidth(Context context, LayoutExampleSize exampleSize, boolean actionBar) {
        findDeviceSize(context, exampleSize, deviceSizeUseType.Width.toString(), actionBar);
    }

    public static void findDeviceSizeUseHeight(Context context, LayoutExampleSize exampleSize, boolean actionBar) {
        findDeviceSize(context, exampleSize, deviceSizeUseType.Height.toString(), actionBar);
    }

    private static void findDeviceSize(Context context,LayoutExampleSize exampleSize,  String deviceType, boolean actionBar) {
        float ratio = (float) (Math.round((exampleSize.layoutExampleHeight() * 100) / exampleSize.layoutExampleWidth())) / 100;
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        dWidth = dm.widthPixels;
        dHeight = dm.heightPixels;
        screenInches = Math.sqrt(Math.pow(dWidth / dm.xdpi, 2)
                + Math.pow(dHeight / dm.ydpi, 2));
        int useHeight;
        if (actionBar) {
            useHeight = dHeight - getActionBarHeight(context);
        } else {
            useHeight = dHeight;
        }

        if (deviceType.equals(deviceSizeUseType.Height.toString())) {
            vWidth = (int) (useHeight / ratio);
            vHeight = useHeight;
            if (dWidth < vWidth) {
                findDeviceSizeUseWidth(context, exampleSize, actionBar);
            }
        } else if (deviceType.equals(deviceSizeUseType.Width.toString())) {
            vWidth = dWidth;
            vHeight = (int) (dWidth * ratio);
            if (useHeight < vHeight) {
                findDeviceSizeUseHeight(context, exampleSize, actionBar);
            }
        }
    }

    public static void textviewTextSizeFormat(float defaultTextSize, TextView... textViews) {
        for (TextView textView : textViews) {
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) ((defaultTextSize / defaultScreenSize) * screenInches));
        }
    }

    public static void buttonTextSizeFormat(float defaultTextSize, Button... buttons) {
        for (Button button : buttons) {
            button.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) ((defaultTextSize / defaultScreenSize) * screenInches));
        }
    }

    public static void edittextTextSizeFormat(float defaultTextSize, EditText... editTexts) {
        for (EditText editText : editTexts) {
            editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, (float) ((defaultTextSize / defaultScreenSize) * screenInches));
        }
    }

    public static void linearFormat(float widthSize, float heightSize, String inputType, LinearLayout... linearLayouts) {
        if (inputType.equals(parentLayoutType.LinearLayout.toString())) {
            for (LinearLayout layout : linearLayouts) {
                layout.setLayoutParams(layParamsOfLinear(widthSize, heightSize, layout));
            }
        } else if (inputType.equals(parentLayoutType.RelativeLayout.toString())) {
            for (LinearLayout layout : linearLayouts) {
                layout.setLayoutParams(layParamsOfRelative(widthSize, heightSize, layout));
            }
        } else if (inputType.equals(parentLayoutType.FrameLayout.toString())) {
            for (LinearLayout layout : linearLayouts) {
                layout.setLayoutParams(layParamsOfFrame(widthSize, heightSize, layout));
            }
        }
    }

    public static void frameFormat(float widthSize, float heightSize, String inputType, FrameLayout... frameLayouts) {
        if (inputType.equals(parentLayoutType.LinearLayout.toString())) {
            for (FrameLayout layout : frameLayouts) {
                layout.setLayoutParams(layParamsOfLinear(widthSize, heightSize, layout));
            }
        } else if (inputType.equals(parentLayoutType.RelativeLayout.toString())) {
            for (FrameLayout layout : frameLayouts) {
                layout.setLayoutParams(layParamsOfRelative(widthSize, heightSize, layout));
            }
        } else if (inputType.equals(parentLayoutType.FrameLayout.toString())) {
            for (FrameLayout layout : frameLayouts) {
                layout.setLayoutParams(layParamsOfFrame(widthSize, heightSize, layout));
            }
        }
    }

    public static void gridviewFormat(float widthSize, float heightSize, String inputType, GridView... gridViews) {
        if (inputType.equals(parentLayoutType.LinearLayout.toString())) {
            for (GridView gridView : gridViews) {
                gridView.setLayoutParams(layParamsOfLinear(widthSize, heightSize, gridView));
            }
        } else if (inputType.equals(parentLayoutType.RelativeLayout.toString())) {
            for (GridView gridView : gridViews) {
                gridView.setLayoutParams(layParamsOfRelative(widthSize, heightSize, gridView));
            }
        } else if (inputType.equals(parentLayoutType.FrameLayout.toString())) {
            for (GridView gridView : gridViews) {
                gridView.setLayoutParams(layParamsOfFrame(widthSize, heightSize, gridView));
            }
        }
    }

    public static void relativeFormat(float widthSize, float heightSize, String inputType, RelativeLayout... relativeLayouts) {
        if (inputType.equals(parentLayoutType.LinearLayout.toString())) {
            for (RelativeLayout layout : relativeLayouts) {
                layout.setLayoutParams(layParamsOfLinear(widthSize, heightSize, layout));
            }
        } else if (inputType.equals(parentLayoutType.RelativeLayout.toString())) {
            for (RelativeLayout layout : relativeLayouts) {
                layout.setLayoutParams(layParamsOfRelative(widthSize, heightSize, layout));
            }
        } else if (inputType.equals(parentLayoutType.FrameLayout.toString())) {
            for (RelativeLayout layout : relativeLayouts) {
                layout.setLayoutParams(layParamsOfFrame(widthSize, heightSize, layout));
            }
        }
    }

    public static void imageFormat(float widthSize, float heightSize, String inputType, ImageView... imageViews) {
        if (inputType.equals(parentLayoutType.LinearLayout.toString())) {
            for (ImageView view : imageViews) {
                view.setLayoutParams(layParamsOfLinear(widthSize, heightSize, view));
            }
        } else if (inputType.equals(parentLayoutType.RelativeLayout.toString())) {
            for (ImageView view : imageViews) {
                view.setLayoutParams(layParamsOfRelative(widthSize, heightSize, view));
            }
        } else if (inputType.equals(parentLayoutType.FrameLayout.toString())) {
            for (ImageView view : imageViews) {
                view.setLayoutParams(layParamsOfFrame(widthSize, heightSize, view));
            }
        }
    }

    public static void imageBtnFormat(float widthSize, float heightSize, String inputType, ImageButton... imageButtons) {
        if (inputType.equals(parentLayoutType.LinearLayout.toString())) {
            for (ImageButton view : imageButtons) {
                view.setLayoutParams(layParamsOfLinear(widthSize, heightSize, view));
            }
        } else if (inputType.equals(parentLayoutType.RelativeLayout.toString())) {
            for (ImageButton view : imageButtons) {
                view.setLayoutParams(layParamsOfRelative(widthSize, heightSize, view));
            }
        } else if (inputType.equals(parentLayoutType.FrameLayout.toString())) {
            for (ImageButton view : imageButtons) {
                view.setLayoutParams(layParamsOfFrame(widthSize, heightSize, view));
            }
        }
    }

    public static void textFormat(float widthSize, float heightSize, String inputType, TextView... textViews) {
        if (inputType.equals(parentLayoutType.LinearLayout.toString())) {
            for (TextView view : textViews) {
                view.setLayoutParams(layParamsOfLinear(widthSize, heightSize, view));
            }
        } else if (inputType.equals(parentLayoutType.RelativeLayout.toString())) {
            for (TextView view : textViews) {
                view.setLayoutParams(layParamsOfRelative(widthSize, heightSize, view));
            }
        } else if (inputType.equals(parentLayoutType.FrameLayout.toString())) {
            for (TextView view : textViews) {
                view.setLayoutParams(layParamsOfFrame(widthSize, heightSize, view));
            }
        }
    }

    public static void editextFormat(float widthSize, float heightSize, String inputType, EditText... editTexts) {
        if (inputType.equals(parentLayoutType.LinearLayout.toString())) {
            for (EditText view : editTexts) {
                view.setLayoutParams(layParamsOfLinear(widthSize, heightSize, view));
            }
        } else if (inputType.equals(parentLayoutType.RelativeLayout.toString())) {
            for (EditText view : editTexts) {
                view.setLayoutParams(layParamsOfRelative(widthSize, heightSize, view));
            }
        } else if (inputType.equals(parentLayoutType.FrameLayout.toString())) {
            for (EditText view : editTexts) {
                view.setLayoutParams(layParamsOfFrame(widthSize, heightSize, view));
            }
        }
    }

    public static void listFormat(float widthSize, float heightSize, String inputType, ListView... listViews) {
        if (inputType.equals(parentLayoutType.LinearLayout.toString())) {
            for (ListView listView : listViews) {
                listView.setLayoutParams(layParamsOfLinear(widthSize, heightSize, listView));
            }
        } else if (inputType.equals(parentLayoutType.RelativeLayout.toString())) {
            for (ListView listView : listViews) {
                listView.setLayoutParams(layParamsOfRelative(widthSize, heightSize, listView));
            }
        } else if (inputType.equals(parentLayoutType.FrameLayout.toString())) {
            for (ListView listView : listViews) {
                listView.setLayoutParams(layParamsOfFrame(widthSize, heightSize, listView));
            }
        }
    }

    public static void spinnerFormat(float widthSize, float heightSize, String inputType, Spinner... Spinners) {
        if (inputType.equals(parentLayoutType.LinearLayout.toString())) {
            for (Spinner spinner : Spinners) {
                spinner.setLayoutParams(layParamsOfLinear(widthSize, heightSize, spinner));
            }
        } else if (inputType.equals(parentLayoutType.RelativeLayout.toString())) {
            for (Spinner spinner : Spinners) {
                spinner.setLayoutParams(layParamsOfRelative(widthSize, heightSize, spinner));
            }
        } else if (inputType.equals(parentLayoutType.FrameLayout.toString())) {
            for (Spinner spinner : Spinners) {
                spinner.setLayoutParams(layParamsOfFrame(widthSize, heightSize, spinner));
            }
        }
    }

    public static void buttonFormat(float widthSize, float heightSize, String inputType, Button... buttons) {
        if (inputType.equals(parentLayoutType.LinearLayout.toString())) {
            for (Button button : buttons) {
                button.setLayoutParams(layParamsOfLinear(widthSize, heightSize, button));
            }
        } else if (inputType.equals(parentLayoutType.RelativeLayout.toString())) {
            for (Button button : buttons) {
                button.setLayoutParams(layParamsOfRelative(widthSize, heightSize, button));
            }
        } else if (inputType.equals(parentLayoutType.FrameLayout.toString())) {
            for (Button button : buttons) {
                button.setLayoutParams(layParamsOfFrame(widthSize, heightSize, button));
            }
        }
    }

    public static void viewPagerFormat(float widthSize, float heightSize, String inputType, ViewPager... viewPagers) {
        if (inputType.equals(parentLayoutType.LinearLayout.toString())) {
            for (ViewPager viewPager : viewPagers) {
                viewPager.setLayoutParams(layParamsOfLinear(widthSize, heightSize, viewPager));
            }
        } else if (inputType.equals(parentLayoutType.RelativeLayout.toString())) {
            for (ViewPager viewPager : viewPagers) {
                viewPager.setLayoutParams(layParamsOfRelative(widthSize, heightSize, viewPager));
            }
        } else if (inputType.equals(parentLayoutType.FrameLayout.toString())) {
            for (ViewPager viewPager : viewPagers) {
                viewPager.setLayoutParams(layParamsOfFrame(widthSize, heightSize, viewPager));
            }
        }
    }

    private static LinearLayout.LayoutParams layParamsOfLinear(float widthSize, float heightSize, View view) {
        LinearLayout.LayoutParams Params = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (widthSize != 0) {
            Params.width = (int) (vWidth / widthSize);
        }
        if (heightSize != 0) {
            Params.height = (int) (vHeight / heightSize);
        }
        return Params;
    }

    private static RelativeLayout.LayoutParams layParamsOfRelative(float widthSize, float heightSize, View view) {
        RelativeLayout.LayoutParams Params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if (widthSize != 0) {
            Params.width = (int) (vWidth / widthSize);
        }
        if (heightSize != 0) {
            Params.height = (int) (vHeight / heightSize);
        }
        return Params;
    }

    private static FrameLayout.LayoutParams layParamsOfFrame(float widthSize, float heightSize, View view) {
        FrameLayout.LayoutParams Params = (FrameLayout.LayoutParams) view.getLayoutParams();
        if (widthSize != 0) {
            Params.width = (int) (vWidth / widthSize);
        }
        if (heightSize != 0) {
            Params.height = (int) (vHeight / heightSize);
        }
        return Params;
    }

    public static void paddingView(int inputSize, String inputType, View... views) {
        for (View view : views) {
            if (inputType.equals(paddingType.Left.toString())) {
                view.setPadding(vWidth / inputSize, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
            } else if (inputType.equals(paddingType.Top.toString())) {
                view.setPadding(view.getPaddingLeft(), vHeight / inputSize, view.getPaddingRight(), view.getPaddingBottom());
            } else if (inputType.equals(paddingType.Right.toString())) {
                view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), vWidth / inputSize, view.getPaddingBottom());
            } else if (inputType.equals(paddingType.Bot.toString())) {
                view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), vHeight / inputSize);
            }
        }
    }

    public static int getViewWidth(String inputType, View view) {
        int size = 0;
        if (inputType.equals(parentLayoutType.LinearLayout.toString())) {
            size = linearViewSize(deviceSizeUseType.Width.toString(), view);
        } else if (inputType.equals(parentLayoutType.RelativeLayout.toString())) {
            size = relativeViewSize(deviceSizeUseType.Width.toString(), view);
        }
        return size;
    }

    public static int getViewHeight(String inputType, View view) {
        int size = 0;
        if (inputType.equals(parentLayoutType.LinearLayout.toString())) {
            size = linearViewSize(deviceSizeUseType.Height.toString(), view);
        } else if (inputType.equals(parentLayoutType.RelativeLayout.toString())) {
            size = relativeViewSize(deviceSizeUseType.Height.toString(), view);
        }
        return size;
    }

    private static int relativeViewSize(String inputStatus, View view) {
        int Size = 0;
        RelativeLayout.LayoutParams Params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        if (inputStatus.equals(deviceSizeUseType.Width.toString())) {
            Size = Params.width;
        } else if (inputStatus.equals(deviceSizeUseType.Height.toString())) {
            Size = Params.height;
        }
        return Size;
    }

    private static int linearViewSize(String inputStatus, View view) {
        int Size = 0;
        LinearLayout.LayoutParams Params = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (inputStatus.equals(deviceSizeUseType.Width.toString())) {
            Size = Params.width;
        } else if (inputStatus.equals(deviceSizeUseType.Height.toString())) {
            Size = Params.height;
        }
        return Size;
    }

    public static float getBitItemWidth() {
        return vWidth / 4;
    }

    public static float getBitItemHeight() {
        return vWidth / 4;
    }
}
