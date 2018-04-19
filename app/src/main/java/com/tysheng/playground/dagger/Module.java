package com.tysheng.playground.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Provides;

/**
 * Created by tysheng
 * Date: 14/9/17 2:22 PM.
 * Email: tyshengsx@gmail.com
 */
@dagger.Module
public class Module {
    private final Context mContext;

    public Module(Context context) {
        mContext = context;
    }


    @Provides
    public Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    public Holder provideHolder() {
        return new Holder();
    }

    /**
     * bar presenter also can be provided here.
     *
     @Provides BarPresenter provideBar(Context context) {
     return new BarPresenter(context);
     }
     */


}
