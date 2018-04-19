package com.tysheng.playground;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tysheng
 * Date: 8/2/18 8:20 PM.
 * Email: tyshengsx@gmail.com
 */

@Module
public class OracleApplicationModule {
    @Provides
    Application provideApplication(App application) {
        return application;
    }

    @Provides
    @Singleton
    Utils provideUtils() {
        return new Utils();
    }
}