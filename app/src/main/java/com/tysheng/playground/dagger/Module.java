package com.tysheng.playground.dagger;

import android.app.Application;
import android.content.Context;

import dagger.Binds;
import dagger.Provides;

/**
 * Created by tysheng
 * Date: 14/9/17 2:22 PM.
 * Email: tyshengsx@gmail.com
 */
@dagger.Module
public abstract class Module {

    @Binds
    abstract Context context(Application activity);

    @Provides
    public static Holder provideHolder() {
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
