package com.tysheng.playground;

import com.tysheng.playground.dagger.Dagger2Activity;

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

    @ContributesAndroidInjector(modules = com.tysheng.playground.dagger.Module.class)
    abstract Dagger2Activity dagger2Activity();
}
