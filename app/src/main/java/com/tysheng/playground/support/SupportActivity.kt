package com.tysheng.playground.support

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.ScaleAnimation
import com.tysheng.playground.R
import com.tysheng.playground.dp2Px
import kotlinx.android.synthetic.main.activity_support.*

/**
 * Created by tysheng
 * Date: 9/4/18 4:02 PM.
 * Email: tyshengsx@gmail.com
 */
class SupportActivity : AppCompatActivity() {

    private var clickEnable = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support)
        ripple.startRipple()
        ripple.setOnClickListener {
            animator(500L)
        }
//        animator(0)
    }

    private fun animator(duration: Long) {
        if (!clickEnable) {
            return
        }
        clickEnable = false
        ripple.stopRipple(duration)
        thankForSupport.animate()
                .translationYBy(-40.dp2Px().toFloat())
                .setDuration(duration)
                .start()
        llShareTop.animate()
                .translationYBy(-20.dp2Px().toFloat())
                .setDuration(duration)
                .start()
        val scaleFactor = 1.5f
        val scale = ScaleAnimation(1f, scaleFactor, 1f, scaleFactor, 92.dp2Px().toFloat(), 34.dp2Px().toFloat())
        scale.duration = duration
        scale.fillAfter = true
        llShare.startAnimation(scale)
    }
}