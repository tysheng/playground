package com.tysheng.playground;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by tysheng
 * Date: 8/2/18 8:20 PM.
 * Email: tyshengsx@gmail.com
 */

@Module
public abstract class BuildersModule {
    @ContributesAndroidInjector
    abstract MainActivity timeBroadcastReceiverInjector();

    @ContributesAndroidInjector
    abstract KtActivity timeadcastReceiverInjector();
}
