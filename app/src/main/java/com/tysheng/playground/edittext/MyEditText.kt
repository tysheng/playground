package com.tysheng.playground.edittext

import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.util.AttributeSet
import timber.log.Timber

/**
 * Created by tysheng
 * Date: 6/6/18 1:59 PM.
 * Email: tyshengsx@gmail.com
 */
class MyEditText @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
        AppCompatEditText(context, attrs, defStyleAttr) {

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)
        Timber.d("onSelectionChanged $selStart $selEnd")
    }

    fun set(){

//        addTextChangedListener()
    }
}