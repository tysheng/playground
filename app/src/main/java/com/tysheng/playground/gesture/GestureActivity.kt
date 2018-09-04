package com.tysheng.playground.gesture

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.NotificationManagerCompat
import android.support.v7.app.AppCompatActivity
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.core.widget.toast
import com.tysheng.playground.R
import kotlinx.android.synthetic.main.activity_gesture.*
import org.jetbrains.anko.toast
import timber.log.Timber

/**
 * Created by tysheng
 * Date: 12/7/18 4:30 PM.
 * Email: tyshengsx@gmail.com
 */
class GestureActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_gesture)
//        frameLayout.setOnTouchListener(TouchListener(this))

        frameLayout.setOnClickListener {
            val autoStartIntent = Intent()
            autoStartIntent.component = ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity")
            try {
                startActivity(autoStartIntent)
            }
            catch (e: Exception) {
                toast(e.message ?: "")
            }
//            goToNotificationSettings(this)
        }
        val view = frameLayout.parent as View
        Timber.d("parent id ${view.id}")
        val enabled = NotificationManagerCompat.from(this).areNotificationsEnabled()
        Timber.d("enable $enabled")
        this.toast("enable $enabled", Toast.LENGTH_LONG)
        Timber.d(Uri.parse("package:" + packageName).path)
    }

    //com.android.settings/.Settings$AppNotificationSettingsActivity

    // oppo 5.0 act=com.coloros.notificationmanager.app.detail cmp=com.coloros.notificationmanager/.AppDetailPreferenceActivity
    // oppo 5.0 com.coloros.notificationmanager/.NotificationCenterActivity
    fun goToNotificationSettings(context: Context) {
        val intent = Intent()
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
            intent.putExtra("android.provider.extra.APP_PACKAGE", context.packageName)
        } else if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.action = "android.settings.APP_NOTIFICATION_SETTINGS"
            intent.putExtra("app_package", context.packageName)
            intent.putExtra("app_uid", context.applicationInfo.uid)
        } else {
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            intent.addCategory(Intent.CATEGORY_DEFAULT)
            intent.data = Uri.parse("package:" + context.packageName)
        }
        //android.settings.APP_NOTIFICATION_SETTINGS  Settings.ACTION_APP_NOTIFICATION_SETTINGS

        context.startActivity(intent)
    }

    class TouchListener(val context: Context) : View.OnTouchListener {
        val simpleOnGestureListener = object : GestureDetector.SimpleOnGestureListener() {
            override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
                Timber.d("fling ${e1?.action} ${e2?.action} velocityX $velocityX velocityY $velocityY")
                return true
            }
        }
        val gestureDetector = GestureDetector(context, simpleOnGestureListener)

        override fun onTouch(v: View?, event: MotionEvent?): Boolean {
            gestureDetector.onTouchEvent(event)
            return true
        }

    }
}