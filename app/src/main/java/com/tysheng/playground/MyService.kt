package com.tysheng.playground

import android.app.IntentService
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import timber.log.Timber

class MyService : IntentService("MyService") {
    override fun onHandleIntent(intent: Intent?) {
        Timber.d("onHandleIntent")
        val intent1 = Intent(this, MyBroadCast::class.java)
        intent1.action = "MyBroadCast"
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent1)

        Timber.d("sendBroadcast")
    }
}