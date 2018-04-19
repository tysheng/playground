package com.tysheng.playground

import android.animation.PropertyValuesHolder
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.app.IntentService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import dagger.Component
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import io.reactivex.Observable
import io.reactivex.plugins.RxJavaPlugins
import kotlinx.android.synthetic.main.activity_kt.*
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

//import kotlinx.android.synthetic.main.content_main.*
/**
 * Created by tysheng
 * Date: 8/2/18 7:17 PM.
 * Email: tyshengsx@gmail.com
 */
class KtActivity : AppCompatActivity() {
    @Inject
    lateinit var utils: Utils
    private val broadCast: MyBroadCast = MyBroadCast()
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kt)
        gameButton.text = "aaaa"
        LocalBroadcastManager.getInstance(this).registerReceiver(broadCast, IntentFilter("MyBroadCast"))
        gameButton.setOnClickListener({
            startService(Intent(this, MyService::class.java))
        })

        Timber.d("onCreate")
        utils.log()
        Observable.just(1)
                .subscribe { println(it) }

      
    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadCast)
    }
}

fun aha() {

}

class MyBroadCast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Timber.d("onReceive")
    }

}

class MyService : IntentService("MyService") {
    override fun onHandleIntent(intent: Intent?) {
        Timber.d("onHandleIntent")
        val intent1 = Intent(this, MyBroadCast::class.java)
        intent1.action = "MyBroadCast"
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent1)

        Timber.d("sendBroadcast")
    }
}

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, OracleApplicationModule::class, BuildersModule::class))
interface OracleAppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}

class AnimationParams {

    var floats: FloatArray? = null
    var values: Array<PropertyValuesHolder>? = null
    var duration: Long = 0
    var repeatCount: Int = 0
    var repeatMode = ValueAnimator.RESTART
    var interpolator: TimeInterpolator? = null
}

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
        RxJavaPlugins.setErrorHandler { throwable -> Timber.tag("App").d(throwable) }

    }

    override fun applicationInjector(): AndroidInjector<App> {
        return DaggerOracleAppComponent.builder().create(this)
    }

    companion object {

        var instance: App? = null
            private set
    }
}