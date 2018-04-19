package com.tysheng.playground.dagger;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by tysheng
 * Date: 14/9/17 2:20 PM.
 * Email: tyshengsx@gmail.com
 */

public class BarPresenter {
    private Context mContext;
    @Inject
    public BarPresenter(Context context) {
        mContext = context;
    }

    public void bar(){
        Log.d("dagger"," bar "+mContext);
    }
}
