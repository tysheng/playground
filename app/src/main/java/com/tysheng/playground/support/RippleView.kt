package com.tysheng.playground.support

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.tysheng.playground.dp2Px
import org.jetbrains.anko.childrenSequence

/**
 * Created by tysheng
 * Date: 9/4/18 4:06 PM.
 * Email: tyshengsx@gmail.com
 */
class RippleView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        FrameLayout(context, attrs, defStyleAttr) {

    private val biggestInt = 140
    private val outInt = 120
    private val midInt = 100
    private val innerInt = 100

    private val outerLength = outInt.dp2Px()
    private val midLength = midInt.dp2Px()
    private val innerLength = innerInt.dp2Px()

    private val lengthList = arrayListOf(outerLength, midLength, innerLength)
    private val colorList = arrayListOf(0x1923C325, 0x3323C325, 0xff23C325.toInt())

    internal var duration = 1000L
    var textView: TextView
    private val scaleList = arrayListOf(biggestInt * 1f / outInt, outInt * 1f / midInt, midInt * 1f / innerInt)

    private val animList = arrayListOf<Animator>()

    init {
        layoutParams = LayoutParams(outerLength, outerLength)
        val view1 = View(context)
        view1.apply {
            this.background = provideDrawable(0)
        }
        add(view1, 0)
        val view = View(context)
        view.apply {
            this.background = provideDrawable(1)
        }
        add(view, 1)
        textView = TextView(context)
        textView.apply {
            this.background = provideDrawable(2)
            text = "Support"
            setTextColor(Color.WHITE)
            typeface = Typeface.DEFAULT_BOLD
            setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22f)
            gravity = Gravity.CENTER
        }
        add(textView, 2)
        requestLayout()
    }

    internal fun setText(text: String) {
        textView.text = text
    }

    private fun add(view: View, index: Int) {
        val layoutParams = LayoutParams(lengthList[index], lengthList[index])
        layoutParams.gravity = Gravity.CENTER
        addView(view, layoutParams)
    }

    private fun provideDrawable(which: Int): ShapeDrawable {
        val ovalShape = OvalShape()
        val shapeDrawable = ShapeDrawable(ovalShape)
        shapeDrawable.paint.apply {
            color = colorList[which]
            isAntiAlias = true
        }
        return shapeDrawable
    }

    internal fun startRipple() {
        childrenSequence().forEachIndexed { index, view ->
            if (index == 2) {
                return
            }
            val animator = ValueAnimator.ofFloat(0f, 1f)
                    .apply {
                        duration = this@RippleView.duration
                        repeatCount = ValueAnimator.INFINITE
                        repeatMode = ValueAnimator.RESTART
                        addUpdateListener {
                            val value = it.animatedValue as Float
                            val scale = (scaleList[index] - 1f) * value + 1f
                            view.scaleY = scale
                            view.scaleX = scale
                        }
                        startDelay = 1000L
                    }
            animList.add(animator)
            animator.start()
        }
    }

    internal fun stopRipple(duration:Long) {
        animList.forEach {
            it.cancel()
        }
        textView.text=null
        getChildAt(0).visibility = View.GONE
        getChildAt(1).visibility = View.GONE
        textView.animate()
                .setDuration(duration)
                .scaleX(0.48f)
                .scaleY(0.48f)
                .start()
    }


}