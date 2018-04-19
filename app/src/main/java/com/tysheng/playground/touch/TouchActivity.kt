package com.tysheng.playground.touch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.tysheng.playground.R
import kotlinx.android.synthetic.main.content_touch.*
import timber.log.Timber

class TouchActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_touch)

        text1.setOnClickListener {
            Timber.d(it.javaClass.simpleName)
        }
        frame1.setOnClickListener {
            Timber.d(it.javaClass.simpleName)
        }


    }

}
