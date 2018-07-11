package com.tysheng.playground

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_kt.*
import timber.log.Timber
import javax.inject.Inject

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
        gameButton.text = "https://app.muslimummah.co/id/posts"
        LocalBroadcastManager.getInstance(this).registerReceiver(broadCast, IntentFilter("MyBroadCast"))
        gameButton.setOnClickListener({
            startService(Intent(this, MyService::class.java))
        })


        Timber.d("task id $taskId")
        utils.log()
        Observable.just(1)
                .subscribe { println(it) }


    }

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadCast)
    }
}

