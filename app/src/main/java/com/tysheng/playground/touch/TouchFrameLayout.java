package com.tysheng.playground.touch;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by tysheng
 * Date: 25/9/17 12:03 PM.
 * Email: tyshengsx@gmail.com
 */

public class TouchFrameLayout extends FrameLayout {
    public TouchFrameLayout(@NonNull Context context) {
        super(context);
    }

    public TouchFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private static final String TAG = "TouchFrameLayout";
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        text1.dispatchTouchEvent(ev);
        Log.d(TAG, "dispatchTouchEvent: "+ev.getAction());
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: "+event.getAction());
        text1.onTouchEvent(event);
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        Log.d(TAG, "onInterceptTouchEvent: "+ev.getAction());
        return true;
    }

    private TextView text1, text2;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        text1 = (TextView) getChildAt(0);
        text2 = (TextView) getChildAt(1);
    }
}
