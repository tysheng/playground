package com.tysheng.playground

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import timber.log.Timber

/**
 * Created by tysheng
 * Date: 30/4/18 11:42 AM.
 * Email: tyshengsx@gmail.com
 */
class EllipsizeTextView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        TextView(context, attrs, defStyleAttr) {

    private var realWidth = 480
    private val ellipsize = "... "
    private var first: String = ""
    private var second: String = ""


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        realWidth = w
        setTextInternal()
        Timber.d("onSizeChanged width $width measure $measuredWidth real $realWidth")
    }

    fun setText(first: String, second: String) {
        this.first = first
        this.second = second
    }

    private fun setTextInternal() {
        if (first.isEmpty() || second.isEmpty()) {
            return
        }
        val ellipsizeLength = paint.measureText(ellipsize)
        val firstLength = paint.measureText(first)
        val secondLength = paint.measureText(second)
        Timber.d("setText first $firstLength second $secondLength elli $ellipsizeLength")
        if (firstLength + secondLength <= realWidth) {
            text = "$first$second"
        } else {
            var substring: String = first
            for (i in first.length downTo 1) {
                substring = first.substring(0, i)
                if (paint.measureText(substring) + secondLength + ellipsizeLength <= realWidth) {
                    break
                }
            }

            text = substring + ellipsize + second
        }
    }
}