package com.tysheng.playground.dagger;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by tysheng
 * Date: 14/9/17 2:14 PM.
 * Email: tyshengsx@gmail.com
 */

public class FooPresenter {
    @Inject
    public FooPresenter() {
    }
    public void foo(){
        Log.d("dagger"," foo ");
    }
}
