package com.tysheng.playground

import android.app.Activity
import android.app.Application
import android.os.Bundle
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber

class App : DaggerApplication(), Application.ActivityLifecycleCallbacks {
    override fun onActivityResumed(activity: Activity?) {
        Timber.tag("lifecycle").d("onActivityResumed ${activity!!::class.java.simpleName}")
    }

    override fun onActivityStarted(activity: Activity?) {
        Timber.tag("lifecycle").d("onActivityStarted ${activity!!::class.java.simpleName}")
    }

    override fun onActivityDestroyed(activity: Activity?) {
        Timber.tag("lifecycle").d("onActivityDestroyed ${activity!!::class.java.simpleName}")
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

    }

    override fun onActivityStopped(activity: Activity?) {
        Timber.tag("lifecycle").d("onActivityStopped ${activity!!::class.java.simpleName}")
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        Timber.tag("lifecycle").d("onActivityCreated ${activity!!::class.java.simpleName}")
    }

    override fun onActivityPaused(activity: Activity?) {
        Timber.tag("lifecycle").d("onActivityPaused ${activity!!::class.java.simpleName}")
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
        RxJavaPlugins.setErrorHandler { throwable -> Timber.tag("App").e(throwable) }
        registerActivityLifecycleCallbacks(this)
    }

    override fun applicationInjector(): AndroidInjector<App>? {
        return DaggerOracleAppComponent.builder().create(this)
    }

    companion object {

        var instance: App? = null
            private set
    }
}