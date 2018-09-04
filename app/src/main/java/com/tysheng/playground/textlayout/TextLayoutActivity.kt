package com.tysheng.playground.textlayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.widget.TextView
import com.tysheng.playground.R

class TextLayoutActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var webView: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_layout)
//        textView = findViewById(R.id.textView)

//        textView.post {
//            val text = textView.text
//            val layout = textView.layout
//            for (i in 0 until layout.lineCount) {
//                val end = layout.getLineEnd(i)
//                Timber.d("${text[end - 1]}")
//            }
//
//            Timber.d("${layout::class.java.simpleName}")
//            val field = layout::class.java.getDeclaredField("mLines")
//            field.isAccessible = true
//            val array: IntArray = field.get(layout) as IntArray
//            Timber.d(array.joinToString())
//
//        }


        webView = findViewById(R.id.webView)

        webView.loadUrl("https://www.google.com/")
    }
}
